import request from "@/utils/request";

const userApi = {
    loginByPasswd: '/account/loginByPasswd',
    loginByVerifyCode: '/account/loginByVerify'
}

// 使用账号密码登录
export function loginByPd(params) {
    return request({
        url: userApi.loginByPasswd,
        method: 'post',
        data: params
    })
}

// 使用邮箱验证码登录
export function loginByVc(params) {
    return request({
        url: userApi.loginByVerifyCode,
        method: 'post',
        data: params
    })
}