import request from '@/utils/request'

export function getHostTest(params) {
    return request({
        url: '/host/getHostList',
        method: 'get',
        params: params
    })
}