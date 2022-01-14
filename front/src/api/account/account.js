import request from "@/utils/request";

const userApi = {
    loginByPasswd: '/account/loginByPasswd',
    loginByVerifyCode: '/account/loginByVerify',
    registerByItem: '/account/register'
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

// 账号注册
export function register(params) {
    return request({
        url: userApi.registerByItem + '?verifyCode=' + params.verifyCode,
        method: 'post',
        data: params
    })
}


