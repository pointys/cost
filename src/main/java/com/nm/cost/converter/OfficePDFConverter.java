package com.nm.cost.converter;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;

/**
 * @Author: 韩老魔
 * @Date: 2019/3/29 0029 15:51
 */
@Component
public class OfficePDFConverter {
    //支持文件类型
    public static final String[] OFFICE_POSTFIXS = {"doc", "docx", "xls",
            "xlsx", "ppt", "pptx"};

    @Value("${OpenOffice_HOME}")
    String OpenOffice_HOME;

//方式一：
    /**
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice
     * <pre>           xlsm转换不了
     * @param sourceFile 源文件, 绝对路径. 可以是Office全部格式的文档. 包括.doc,
     *                   .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc
     * @param targetFile 目标文件. 绝对路径.
     * @return
     */
    public boolean officePDF(String sourceFile, String targetFile) throws Exception {
        // 1.判断转换后文件是否存在
        File inputFile = new File(sourceFile);
        File outputFile = new File(targetFile);
        if (outputFile.exists()) {
            System.out.println("pdf文件已经存在");
            return true;
        }

        if (!inputFile.exists()) {
            return false;// 找不到源文件, 则返回
        }

        // 如果目标路径不存在, 则新建该路径
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

        // 2.启动OpenOffice的服务 , 注意端口不要冲突
        //windows环境命令
        String command = OpenOffice_HOME
                + "program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8300;urp;\" -nofirststartwizard";

//        linux环境openoffice服务进程一直开着 该命令省略
//        String command = OpenOffice_HOME
//                      + "program/soffice -headless -accept=\"socket,host=127.0.0.1,port=8300;urp;\" -nofirststartwizard";

                //进程
        Process pro = Runtime.getRuntime().exec(command);

        // 3.连接到OpenOffice ，注意端口要与上面一致
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                "127.0.0.1", 8300);
        connection.connect();

        // 4.转换pdf***********转换核心部分*****************************
        DocumentConverter converter = new OpenOfficeDocumentConverter(
                connection);

        converter.convert(inputFile, outputFile);

        // 5. 关闭连接和OpenOffice服务进程，避免占用CPU
        connection.disconnect();
        pro.destroy();
        return true;
    }

//    方式二：转换时间偏长
  /*  public boolean office2PDF(String sourceFile, String targetFile) throws Exception {
        // 1.判断转换后文件是否存在
        File inputFile = new File(sourceFile);
        File outputFile = new File(targetFile);
        if (outputFile.exists())
            return true;
        // 2.获取config对象
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        //2.1设置参数
        config.setOfficeHome(OpenOffice_HOME);
        //2.2config.setPortNumber(2002);默认值
        config.setTaskExecutionTimeout(1000 * 60 * 5L);//设置任务执行5分钟为超时
        config.setTaskQueueTimeout(1000 * 60 * 60 * 2L);//设置任务列队为2小时
        // 3.获取officeManager对象
        OfficeManager officeManager = config.buildOfficeManager();
        // 3.1启动服务
        officeManager.start();
        // 4.转换核心部分
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        DocumentFormat outputFormat = new DefaultDocumentFormatRegistry().getFormatByExtension("pdf");
        converter.convert(new File(sourceFile), new File(targetFile), outputFormat);
        // 5.关闭服务
        officeManager.stop();
        return true;
    }*/


    /**
     *	添加水印、页眉、页脚
     * @param pdfFile pdf文件路径
     * @param savepath 水印文件路径
     * @param waterMarkName 文字水印
     * @param pageHeade 页眉
     * @param foot 页脚
     * @return
     */
    public void addFooterAndWater(String pdfFile, String savepath,
                                        String waterMarkName)
    {
        // 文档总页数
        int num = 0;

        Document document = new Document();
        try
        {
            PdfReader reader = new PdfReader(pdfFile);
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.EMBEDDED);

            num = reader.getNumberOfPages();
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));
            document.open();
            for (int i = 0; i < num;)
            {
                PdfImportedPage page = copy.getImportedPage(reader, ++i);
                PdfCopy.PageStamp stamp = copy.createPageStamp(page);
                Font f = new Font(base);

//                // 添加页脚，左侧文字，右侧页码
//                ColumnText.showTextAligned(stamp.getUnderContent(),
//                        Element.ALIGN_RIGHT,
//                        new Phrase(String.format("第 %d 页/共 %d 页", i, num), f),
//                        550f, 28, 0);
//                ColumnText.showTextAligned(stamp.getUnderContent(),
//                        Element.ALIGN_LEFT, new Phrase(foot, f), 50f, 28, 0);
//
//                // 添加页眉 (文字页眉，居中)
//                ColumnText.showTextAligned(stamp.getUnderContent(),
//                        Element.ALIGN_CENTER, new Phrase(pageHeade, f), 150f,
//                        800, 0);
//
//                // 页眉添加logo （图片页眉，居右）
//                /*Image img = Image.getInstance("template/logo.png");// 选择图片
//                img.setAlignment(1);
//                img.scaleAbsolute(436 / 5, 96 / 5);// 控制图片大小
//                img.setAbsolutePosition(450f, 800);// 控制图片位置
//                stamp.getUnderContent().addImage(img);*/

                // 添加水印
                PdfContentByte under = stamp.getUnderContent();
                under.beginText();
                under.setColorFill(Color.LIGHT_GRAY);

                //设置字体大小初始值
                int fontSize = 80;
                //根据水印文字长度计算获取字体大小,字符越长，字体越小，设置字体
                if(null != waterMarkName && !"".equals(waterMarkName)){
                    int length = waterMarkName.length();
                    if(length <=26 && length >= 18){
                        fontSize = 26;
                    }else if(length <18 && length >= 8){
                        fontSize = 40;
                    }else if(length <8 && length >= 1){
                        fontSize = 80;
                    }else {
                        fontSize = 16;
                    }
                }

                under.setFontAndSize(base, fontSize);

                // 设置水印文字字体倾斜 开始
                float pageWidth = reader.getPageSize(i).getWidth();
                float pageHeight = reader.getPageSize(i).getHeight();

                under.showTextAligned(Element.ALIGN_CENTER, waterMarkName,
                        pageWidth / 2, pageHeight / 2, 15);// 水印文字成60度角倾斜,且页面居中展示

                // 字体设置结束
                under.endText();
                stamp.alterContents();
                copy.addPage(page);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != document)
            {
                document.close();
            }
        }
        System.out.println("pdf totalpages:" + num);

    }

}
