package com.rat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @tip: write_bug
 * @author: yaominc
 * @date: 11/4/2022 下午10:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgModel {

    /**
     * 文件id
     */
    private Integer id;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 文件
     */
    private MultipartFile img;

    /**
     * 网络路径
     */
    private String httpPath;

    /**
     * 文件名
     */
    private String imgName;
}
