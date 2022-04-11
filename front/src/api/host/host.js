import request from "@/utils/request";

// 获取默认用户列表
export function getHostList(params) {
    return request({
        url: '/host/getHostList',
        method: 'get',
        params: params
    })
}

// 通过主持人姓名模糊查询
export function getHostListByLikeParam(params, nameSearch) {
    return request({
        url: '/host/getHostListByNameLike' + '?nameLike=' + nameSearch,
        method: 'get',
        params: params
    })
}

// 通过类别查询主持人列表
export function getHostsByType(params, type) {
    return request({
        url: '/host/getHostListByType' + '?type=' + type,
        method: 'get',
        params: params
    })
}

// 通过注册时长查询你主持人列表
export function getHostsByTime(params, timeSearch) {
    return request({
        url: '/host/getHostListByTime' + '?timeMark=' + timeSearch,
        method: 'get',
        params: params
    })
}

// 通过id获取主持人信息
export function getHostById(params) {
    return request({
        url: '/host/getHostById' + '?hostId=' + params,
        method: 'get',
    })
}

// 更新主持人信息
export function updateHostInfo(data) {
    return request({
        url: '/host/updateHostInfo',
        method: 'post',
        data: data
    })
}

