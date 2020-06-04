package com.xlf.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xlf.sys.common.DataGridView;
import com.xlf.sys.domain.Loginfo;
import com.xlf.sys.service.LoginfoService;
import com.xlf.sys.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author saoji
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;

    /**
     * 查询全部
     * */
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo){
        System.out.println();
        IPage<Loginfo> page= new Page<>(loginfoVo.getPage(),loginfoVo.getLimit());
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname",loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isBlank(loginfoVo.getLoginip()),"loginip",loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getStartTime());
        queryWrapper.orderByDesc("logintime");
        this.loginfoService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
}

