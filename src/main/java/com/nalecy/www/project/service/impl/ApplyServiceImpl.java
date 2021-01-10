package com.nalecy.www.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.constant.ApplyStatusConstant;
import com.nalecy.www.project.constant.ProjectStatusConstant;
import com.nalecy.www.project.dao.ProjectMapper;
import com.nalecy.www.project.dao.UserMapper;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.vo.ApplyQueryVo;
import com.nalecy.www.project.entity.vo.ApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.ApplyMapper;
import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.service.ApplyService;
import org.springframework.util.Assert;

@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService{

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public boolean apply(Apply apply) {
        Assert.notNull(apply, "申请信息不能为空");
        Assert.notNull(apply.getProjectId(), "项目id不能为空");
        Assert.notNull(apply.getUserId(), "用户id不能为空");
//        Assert.notNull(apply.getReason(), "申请理由不能为空");

        Assert.notNull(userMapper.selectById(apply.getUserId()), "该用户不存在");

        Project project = projectMapper.selectById(apply.getProjectId());
        Assert.notNull(project, "该项目不存在");
        Assert.isTrue(ProjectStatusConstant.WAIT.equals(project.getStatus()), "该项目已评审");
        apply.setStatus(ApplyStatusConstant.WAIT);
        // 插入
        Assert.state(applyMapper.insert(apply) == 1, "系统错误");
        return true;
    }

    @Override
    public Page<ApplyVo> selectApplyList(ApplyQueryVo applyQueryVo) {
        // 评委名不为空，模糊搜索
        if(Objects.nonNull(applyQueryVo.getUserName())){
            List<Integer> userIds = userMapper.selectUserIdByName(applyQueryVo.getUserName());
            if(CollectionUtils.isEmpty(userIds)){
                return new Page<ApplyVo>(applyQueryVo.getCurrent(), applyQueryVo.getSize())
                        .setRecords(Collections.emptyList());
            }else{
                applyQueryVo.setUserIds(userIds);
            }
        }

        // 项目名不为空，模糊搜索
        if(Objects.nonNull(applyQueryVo.getProjectName())){
            List<Integer> projectIds = projectMapper.selectByName(applyQueryVo.getProjectName());
            if(CollectionUtils.isEmpty(projectIds)){
                return new Page<ApplyVo>(applyQueryVo.getCurrent(), applyQueryVo.getSize())
                        .setRecords(Collections.emptyList());
            }else{
                applyQueryVo.setProjectIds(projectIds);
            }
        }

        // 搜
        Page<Apply> applyPage = applyMapper.selectApply(applyQueryVo);

        // 转
        Page<ApplyVo> result = new Page<>(applyPage.getCurrent(), applyPage.getSize());
        result.setTotal(applyPage.getTotal());

        List<ApplyVo> resultList = new LinkedList<>();
        if(CollectionUtils.isNotEmpty(applyPage.getRecords())){
            applyPage.getRecords().forEach(apply -> {
                ApplyVo applyVo = ApplyVo.convert(apply);
                applyVo.setUserName(userMapper.selectById(apply.getUserId()).getName());
                applyVo.setProjectName(projectMapper.selectById(apply.getProjectId()).getProjectName());
                resultList.add(applyVo);
            });
        }

        return result.setRecords(resultList);
    }

    @Override
    public boolean updateApply(Integer applyId, Integer status) {
        Assert.notNull(applyId, "申请id不能为空");
        Assert.notNull(status, "操作状态不能为空");

        Assert.notNull(baseMapper.selectById(applyId), "该申请表不存在");

        Assert.state(applyMapper.updateById(new Apply().setId(applyId).setStatus(status)) == 1, "系统错误");
        return true;
    }
}
