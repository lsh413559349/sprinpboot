package com.baomidou.springboot.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.springboot.entity.UcenterUser;
import com.baomidou.springboot.mapper.UcenterUserMapper;
import com.baomidou.springboot.service.IUcenterUserService;
import org.springframework.stereotype.Service;

@Service
public class UcenterUserServiceImpl extends ServiceImpl<UcenterUserMapper,UcenterUser>
        implements IUcenterUserService {
}
