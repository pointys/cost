package com.nm.cost.controller.document;

import com.nm.cost.converter.OfficePDFConverter;
import com.nm.cost.model.Document;
import com.nm.cost.model.User;
import com.nm.cost.service.document.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/3/30 0030 15:56
 */
@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private IDocumentService documentService;


    @GetMapping("/documentUpload")
    public String upload() {
        return "document/document_add";
    }

    @PostMapping("/doUpload")
    public String doUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest req, Model model, String file_desc) throws Exception {
        //构造文档对象
        Document document = new Document();
        //循环添加保存文件记录返回url
        String url = null;
        try {
            for (MultipartFile file : files) {
                System.out.println("文件名：" + file.getOriginalFilename());
                //TODO
                // 判断文件是否存在
                //思路：
                //要判断文件是否重复，就需要对每次上传的文件计算MD5值，上传前通过查询文件库中是否存在该MD5值，来判断是选择上传还是直接复制已存在的文件路径。
                // 获取上传文件的MD5值


                if (file.isEmpty()) {
                    model.addAttribute("tip", "空文件");
                    return "document/document_add";
                } else {
                    //将文件上传到服务器

                    url = documentService.uploadService(file, req);
                    if (url != null) {
                        //将上传后的记录保存到数据库
                        document.setFile_name(file.getOriginalFilename());
                        document.setFile_size(file.getSize());
                        document.setFile_type(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length()));
                        document.setFile_url(url);
                        document.setFile_desc(file_desc);
                        User user = (User) req.getSession().getAttribute("userInfo");
                        document.setUser_id(user.getUserId());
                        document.setUserName(user.getUserName());
                        document.setFile_mark("1");
                        System.out.println("构造document:" + document.toString());
                        documentService.add(document);
                        System.out.println(document.toString());
                        model.addAttribute("tip", "上传成功");
                    } else {
                        model.addAttribute("tip", "上传失败");
                    }
                }
            }
        }catch (Exception e){
            model.addAttribute("tip", "上传出现异常，您ip已被记录@_@");
            return "document/document_add";
        }

        return "document/document_add";
    }

    @RequestMapping("/documentQuery")
    public String queryDocument(Document document, Model model,String tip) {
        System.out.println("文档查询参数：" + document.toString());
        List<Document> documentList = documentService.querys(document);
        model.addAttribute("documentList", documentList);
        model.addAttribute("tip",tip);
        System.out.println("重定向接收tip:"+tip);
        return "document/document_list";
    }

    @PostMapping("/documentDelete")
    public String documentDelete(Document document) {
        System.out.println(Arrays.toString(document.getIds()));
        //根据数组删除

            documentService.delete(document.getIds());

        //       重定向 注意路径
        return "redirect:/document/documentQuery";
    }

    @GetMapping("myDocument")
    public String myDocument(HttpServletRequest req, Document document, Model model) {
        document.setUser_id(((User) req.getSession().getAttribute("userInfo")).getUserId());
        List<Document> documentList = documentService.querys(document);
        model.addAttribute("documentList", documentList);

        return "document/document_mylist";
    }

    @GetMapping("documentWatch")
    public String coverPDF(String file_id, Model model, Document document, HttpServletRequest req, RedirectAttributes redirectAttr) {
        //调用service转pdf返回pdf路径

        String file_pdf_url=documentService.covertPdf(file_id, document, req);
       if(file_pdf_url!=null) {
           System.out.println("跳转插件jsp。。。");
           model.addAttribute("file_pdf_url", file_pdf_url);
           model.addAttribute("tip", "你好，我是(●—●)");
           return "document/document_pdf";
       }
       //重定向以问号形式传参不会有乱码,RedirectAttributes可以问号传参不乱吗
        redirectAttr.addAttribute("tip","文档转换失败，可以再试一次哦");

        System.out.println("文档转换失败");
        //       重定向 注意路径
        return "redirect:/document/documentQuery";
    }

    @GetMapping("/showPdf")
    public String showPdf(HttpServletRequest request, HttpServletResponse response, String file_pdf_url) throws Exception {
        System.out.println("pdf预览路径：" + file_pdf_url);
        File file = new File(file_pdf_url);
        byte[] data = null;
        try {
            FileInputStream input = new FileInputStream(file);
            data = new byte[input.available()];
            input.read(data);
            response.getOutputStream().write(data);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("documentDownload")
    public void documentDownload(String file_url, HttpServletResponse resp) {
        System.out.println("download:" + file_url);
        try {
            documentService.download(file_url, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
