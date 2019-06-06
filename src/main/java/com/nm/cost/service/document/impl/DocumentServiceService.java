package com.nm.cost.service.document.impl;

import com.nm.cost.converter.OfficePDFConverter;
import com.nm.cost.mapper.DocumentMapper;
import com.nm.cost.model.Document;
import com.nm.cost.model.User;
import com.nm.cost.service.document.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/4/5 0005 17:34
 */
@Slf4j
@Service
@Transactional
public class DocumentServiceService implements IDocumentService {
    //允许的文档和压缩类型
    private static final List<String> allowDocTypes = Arrays.asList("text/plain", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/msword", "application/pdf", "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel");


    @Value("${baseUploadPath}")
    private String baseUploadPath;
    @Value("${covertPdfPath}")
    private String covertPath;
    @Value("${waterMark}")
    private String waterMark;
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private OfficePDFConverter pdfConverter;

    @Override
    public int add(Document document) {

        return documentMapper.add(document);
    }

    @Override
    public List<Document> querys(Document document) {
        return documentMapper.querys(document);
    }

    @Override
    public int update(Document document) {
        return documentMapper.update(document);
    }

    @Override
    public void delete(Integer[] ids) {
        //先删除服务器源文件再删除记录
        Document document=new Document();
        for(Integer id:ids){
            System.out.println("循环删除id:"+id);
            document.setFile_id(id);

            String file_url=(this.querys(document)).get(0).getFile_url();
            System.out.println("file_url:"+file_url);
            Path path= Paths.get(file_url);
            try {
                //逐个从删除硬盘文件
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //最后直接删除多条记录
        documentMapper.delete(ids);
    }

    @Override
    public String uploadService(MultipartFile file, HttpServletRequest req) {
        String fileName = file.getOriginalFilename();

        String type = file.getContentType();

        System.out.println("上传文件类型：" + file.getContentType());

        String userAccount = ((User) req.getSession().getAttribute("userInfo")).getUserAccount();

        //文档格式允许
        if (allowDocTypes.contains(file.getContentType())) {
            try {
                //判断目录是否存在   不存在：创建目录

                File file2 = new File(baseUploadPath + userAccount);
                if (!file2.exists()) {
                    System.out.println("开始创建用户文件夹：" + file2.getAbsolutePath());
                    file2.mkdirs();
                }
               /* //TODO
                //通过文件名判断文件是否已经存在,不合适
                Document document=new Document();
                document.setFile_name(file.getOriginalFilename());
                document = this.querys(document).get(0);
                if(document!=null){
                    return document.getFile_url();
                }*/


                //主要带上/ 获取完整文件路径信息
                File fileMessage = new File(file2.getAbsolutePath() + File.separator + file.getOriginalFilename());
                //保存文件
                file.transferTo(fileMessage);
                //设置文件只读 只读文件删除还需该权限且win和linux环境不同设置方式不同,此处注释
//                fileMessage.setReadOnly();
                //返回图片url
                System.out.println("返回文件路径：" + fileMessage.getAbsolutePath());
//getAbsolutePath反斜杠这里不采用
                return baseUploadPath + userAccount + "/" + file.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void download(String url, HttpServletResponse resp) throws IOException {
        System.out.println("下载文件地址：" + url);
        String fileName = url.substring(url.lastIndexOf("/") + 1);

        long startTime = System.currentTimeMillis();

        //解决浏览器中文乱码问题
        //设置响应头，浏览器响应下载该文件弹出下载文件名
        resp.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));

        //nio
        File file = new File(url);
        ServletOutputStream op = resp.getOutputStream();
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel fileChannel = raf.getChannel();
        long size = file.length();
        //将文件所有数据映射到虚拟内存，并只读
        MappedByteBuffer buff = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        byte[] by = new byte[(int) size];

        for (int i = 0; i < size; i++) {
            byte b = buff.get();
            by[i] = b;
        }
        op.write(by);

        buff.clear();
        fileChannel.close();
        raf.close();
        op.close();

        //计算用时
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("下载时间：" + time);
    }

    @Override
    public String covertPdf(String file_id, Document document, HttpServletRequest req) {
        document.setFile_id(Integer.valueOf(file_id));
        document = this.querys(document).get(0);
        String file_name = document.getFile_name().substring(0, document.getFile_name().lastIndexOf("."));
        String file_url = document.getFile_url();
        String type = document.getFile_type();
        String account = ((User) req.getSession().getAttribute("userInfo")).getUserAccount();

        //老坑比 重复account：F:/upload/covert/123123 covertPath += account;
        //判断目录是否存在   不存在：创建目录

        File file = new File(covertPath + account);
        if (!file.exists()) {
            System.out.println("创建用户文件夹");
            file.mkdirs();
        }
        System.out.println("格式转换源文件与目标文件：" + file_url + "\t" + covertPath + account + "/" + file_name + ".pdf");

        //非pdf转pdf前端预览
        if("pdf".equals(type)){
            return file_url;
        }
        try {
            //1.office转pdf
            pdfConverter.officePDF(file_url, covertPath + account + "/" + file_name + ".pdf");
            String pdfPath=covertPath + account + "/" + file_name + ".pdf";
            //2.给pdf文件添加水印
            //定义水印文件保存路径(动态路径)
            String waterPath=covertPath + account + "/" + file_name+"(water)"+".pdf";
            pdfConverter.addFooterAndWater(pdfPath,waterPath,waterMark);

//            return pdfPath;
            return waterPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
