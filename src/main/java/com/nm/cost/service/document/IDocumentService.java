package com.nm.cost.service.document;

import com.nm.cost.model.Document;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/4/5 0005 17:33
 */
public interface IDocumentService {
    //添加文档
    int add(Document document);
    //查询文档
    List<Document> querys(Document document);
    //修改文档
    int update(Document document);
    //删除文档
    void delete(Integer[] ids);

    String uploadService(MultipartFile file, HttpServletRequest req);

     void download(String url, HttpServletResponse resp) throws IOException;

     String covertPdf(String file_id, Document document, HttpServletRequest req);
}
