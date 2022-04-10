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

// 主持人认证申请
export function hostAsset(data, config) {
    return request({
        url: '/user/hostAsset',
        method: 'post',
        data: data,
        config: config
    })
}

// 获取认证文件
export function getAssetFile(param) {
    return request({
        url: '/user/getAssetFile' + '?userId=' + param,
        method: 'get',
    })
}

// 获取认证记录
export function getAssetRecord(param) {
    return request({
        url: '/user/getAssetRecord' + '?userId=' + param,
        method: 'get',
    })
}

// 撤回认证申请
export function delAsset(param) {
    return request({
        url: '/user/delAsset' + '?userId=' + param,
        method: 'get'
    })
}
