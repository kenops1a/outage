import request from "@/utils/request";

export function createForm(orderForm, userId, hostId) {
    return request({
        url: '/hostForm/addForm' + '?userId=' + userId + '&hostId' + hostId,
        method: 'post',
        data: orderForm
    })
}
