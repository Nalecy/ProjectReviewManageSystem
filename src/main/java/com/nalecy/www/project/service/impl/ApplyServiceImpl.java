package com.nalecy.www.project.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.ApplyMapper;
import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.service.ApplyService;
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService{

}
