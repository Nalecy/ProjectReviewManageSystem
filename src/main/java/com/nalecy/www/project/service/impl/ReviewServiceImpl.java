package com.nalecy.www.project.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.ReviewMapper;
import com.nalecy.www.project.entity.po.Review;
import com.nalecy.www.project.service.ReviewService;
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService{

}
