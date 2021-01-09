package com.nalecy.www.project.entity.vo;

import com.nalecy.www.project.entity.po.Apply;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ApplyVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 申请理由
     */
    private String reason;

    /**
     * 状态 0-待评审 1-通过 2-不通过
     */
    private Integer status;

    /**
     * 评委id
     */
    private Integer userId;

    /**
     * 评委姓名
     */
    private String userName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public static ApplyVo convert(Apply apply){
        ApplyVo applyVo = new ApplyVo();
        BeanUtils.copyProperties(apply, applyVo);
        return applyVo;
    }
}
