package com.rat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @tip: write_bug
 * @author: yaominc
 * @date: 11/4/2022 下午3:01
 */
@Data
@AllArgsConstructor
public class UserRoleBindModel {

    private int userId;

    private int roleId;

}
