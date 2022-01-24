import request from "@/utils/request";

// 通过邮箱账号获取用户信息
export function getUserByEmail(params) {
    return request({
        url: '/user/getUserByEmail',
        method: 'post',
        data: params
    })
}

// 修改信息
export function updateUserInfo(params) {
    return request({
        url: '/user/updateUser',
        method: 'post',
        data: params
    })
}

// 获取用户角色
export function getUserRoleByEmail(params) {
    return request({
        url: '/user/getUserRoleByEmail',
        method: 'post',
        data: params
    })
}
