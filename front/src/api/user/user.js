import request from "@/utils/request";

// 通过邮箱账号获取用户信息
export function getUserByEmail(params) {
    return request({
        url: '/user/getUserByEmail',
        method: 'post',
        data: params
    })
}
