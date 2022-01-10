import request from "@/utils/request";

// 通过邮箱获取验证码
export function getVc(email) {
    return request({
        url: '/mail/getVerifyCode',
        method: 'get',
        params: email
    })
}
