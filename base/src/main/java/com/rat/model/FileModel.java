package com.rat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 文件接收类
 * @tip: write_bug
 * @author: yaominc
 * @date: 10/4/2022 下午5:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {

    /**
     * 文件id
     */
    private Integer id;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件
     */
    private MultipartFile file;

    /**
     * 网络路径
     */
    private String httpPath;

    /**
     * 文件名
     */
    private String fileName;
}
