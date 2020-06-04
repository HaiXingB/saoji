package com.xlf.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlf.sys.common.*;
import com.xlf.sys.domain.Permission;
import com.xlf.sys.domain.User;
import com.xlf.sys.service.PermissionService;
import com.xlf.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuController {



    @Autowired
    private PermissionService permissionService;

    @RequestMapping("loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(PermissionVo permissionVo){
        //查询所有菜单
        QueryWrapper<Permission> querWrapper = new QueryWrapper<>();
        //设置只能查菜单
        querWrapper.eq("type", Constast.TYPE_MENU);
        querWrapper.eq("available",Constast.AVAILABLE_TRUE);

        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        if (user.getType() == Constast.USER_TYPE_SUPER){
            list = permissionService.list(querWrapper);
        }else {
            //根据用户ID加角色加权限去查询
            list = permissionService.list(querWrapper);
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for(Permission permission:list){
            Integer id = permission.getId();
            Integer pid = permission.getPid();
            String title = permission.getTitle();
            String icon = permission.getIcon();
            String href = permission.getHref();
            Boolean spread = permission.getOpen()==Constast.OPEN_TRUE?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));

        }
        //构造层级关系
        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes,1);

        return new DataGridView(list2);
    }



}
