package com.system.controller;

import com.system.entity.AjaxDataManage;
import com.system.entity.AjaxGoodsParameter;
import com.system.entity.AjaxLogParameter;
import com.system.entity.json.DataJson;
import com.system.entity.json.GoodsJson;
import com.system.entity.json.LogJson;
import com.system.service.DataManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DataManageController {
    @Autowired
    DataManageService service;

    @Autowired
    HttpServletRequest request;

    // 登录
    @RequestMapping(value = "/manage/data", method = RequestMethod.POST)
    public @ResponseBody
    LogJson DataManage() {
        AjaxDataManage temp = new AjaxDataManage(request);
        return service.updateOrClean(temp);
    }

    // 获取日志列表
    @RequestMapping(value = "/getLogList", method = RequestMethod.POST)
    public @ResponseBody
    LogJson getLogList() {
        AjaxLogParameter temp = new AjaxLogParameter(request);
        return service.getLogList(temp);
    }

    // 获取商品列表
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public @ResponseBody
    GoodsJson getGoodsList() {
        AjaxGoodsParameter temp = new AjaxGoodsParameter(request);
        return service.getGoodsList(temp);
    }

    // 获取商品列表
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public @ResponseBody
    DataJson getData() {
        AjaxGoodsParameter temp = new AjaxGoodsParameter(request);
        return service.getData(temp);
    }


}
