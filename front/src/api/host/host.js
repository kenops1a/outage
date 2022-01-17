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
export function getHostListByLikeParam(params) {
    return request({
        url: '/host/getHostListByNameLike',
        method: 'get',
        params: params
    })
}
