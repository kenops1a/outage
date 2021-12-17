package com.rat.mapper;

import com.rat.info.JsonResult;
import com.rat.model.HostFormModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:36
 */
@Mapper
public interface HostFormMapper {

    /**
     * 新建订单
     * @return 0-失败，非0成功
     */
    int addForm(HostFormModel hostFormModel);

    /**
     * 更新订单信息
     * @param hostFormModel 更新订单
     * @return 0-失败，非0成功
     */
    int updateForm(HostFormModel hostFormModel);

    /**
     * 修改表单状态by表单id
     * @param formId 表单编号
     * @param status 表单状态
     * @return int
     */
    int updateStatusById(@Param("formId") int formId, @Param("status") String status);

    /**
     * 批量修改表单状态
     * @param idsList id数组
     * @return int
     */
    int updateStatusByIds(@Param("idsList") List<Integer> idsList, @Param("status") String status);

    /**
     * 通过前端传递的带有属性值的hostFormModel对象查询对应订单列表
     * @param hostFormModel 带有查询参数的对象
     * @return 返回订单对象集合
     */
    List<HostFormModel> getFormListByItem(HostFormModel hostFormModel);

    /**
     * 通过订单id查询
     * @param formId 订单id
     * @return 订单对象
     */
    HostFormModel getFormById(@Param("formId") int formId);
}
