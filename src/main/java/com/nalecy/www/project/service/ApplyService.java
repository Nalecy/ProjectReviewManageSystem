package com.nalecy.www.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.entity.po.Apply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nalecy.www.project.entity.vo.ApplyQueryVo;
import com.nalecy.www.project.entity.vo.ApplyVo;
import lombok.experimental.Accessors;

public interface ApplyService extends IService<Apply> {

    /**
     * 提交评审申请
     *
     * @param apply 评审申请
     * @return boolean
     */
    boolean apply(Apply apply);

    /**
     * 搜索申请列表
     *
     * @param applyQueryVo 条件
     * @return Page<ApplyVo>
     */
    Page<ApplyVo> selectApplyList(ApplyQueryVo applyQueryVo);

    /**
     * 审核申请
     *
     * @param applyId 申请id
     * @param status  1-同意 2-不同意
     * @return boolean
     */
    boolean updateApply(Integer applyId, Integer status);
}
