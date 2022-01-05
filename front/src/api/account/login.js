import request from "@/utils/request";

export function login(params) {
    return request({
        url: '/account/loginByPasswd',
        method: 'post',
        data: params
    })
}