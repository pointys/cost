package com.nm.cost.mapper;

import com.nm.cost.model.Document;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/4/5 0005 17:54
 */
@Mapper
public interface DocumentMapper {
    @Insert("insert into t_file values(null,#{file.file_name},#{file.file_size},now(),#{file.file_type},#{file.file_url},#{file.file_mark},#{file.user_id},#{file.file_desc})")
    int add(@Param("file") Document document);

    List<Document> querys(Document document);

    int update(Document document);

    void delete(@Param("ids") Integer[] ids);
}
