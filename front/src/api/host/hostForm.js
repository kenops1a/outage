import request from "@/utils/request";

// 新建表单
export function createForm(orderForm, hostId, userId) {
    return request({
        url: '/hostForm/addForm' + '?hostId=' + hostId + '&userId=' + userId,
        method: 'post',
        data: orderForm
    })
}

// 获取用户表单列表
export function getFormList(params, page, pageSize) {
    return request({
        url: '/hostForm/getListByUserId' + '?page=' + page + '&pageSize=' + pageSize,
        method: 'post',
        data: params
    })
}

// 删除对应id的订单
export function removeForm(params) {
    return request({
        url: '/hostForm/deleteFormById' + '?formId=' + params,
        method: 'post'
    })
}

// 按订单状态查询订单
export function getFormListByStatus(params, page, pageSize) {
    return request({
        url: '/hostForm/getListByItem' + '?page=' + page + '&pageSize=' + pageSize,
        method: 'post',
        data: params
    })
}

// 修改订单状态
export function updateForm(params) {
    return request({
        url: '/hostForm/updateForm',
        method: 'post',
        data: params
    })
}
