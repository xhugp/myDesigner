package com.xhu.web;

import com.xhu.dto.ResultBean;
import com.xhu.entity.Dtype;
import com.xhu.entity.Role;
import com.xhu.entity.Rtype;
import com.xhu.entity.Ttype;
import com.xhu.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by gp on 2018/3/10.
 */
@Controller
@RequestMapping("/api")
public class CommomController {
    @Autowired
    private CommonUtil commonUtil;

    @RequestMapping(value = "/get-code")
    private void getVerify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getSession().getId());
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        commonUtil.getRandcode(request, response);//输出验证码图片方法
    }


    @ResponseBody
    @RequestMapping(value = "/get-random", method = RequestMethod.GET)
    public ResultBean checkVerify() {
       String value = commonUtil.validateCode();
       return new ResultBean("200",value,"验证码");
    }

    @ResponseBody
    @RequestMapping(value = "/get-dtype",method = RequestMethod.GET)
    public ResultBean getDtype(){
        return new ResultBean("200",commonUtil.getDtype_list(),"需求类型");
    }

    @ResponseBody
    @RequestMapping(value = "/get-ttype",method = RequestMethod.GET)
    public ResultBean getTtype(){
        return new ResultBean("200",commonUtil.getTtype_list(),"需求类型");
    }

    @ResponseBody
    @RequestMapping(value = "/get-rtype",method = RequestMethod.GET)
    public ResultBean getRtype() {
        return new ResultBean("200", commonUtil.getRtype_list(), "招聘对象");
    }

    @ResponseBody
    @RequestMapping(value = "/get-role",method = RequestMethod.GET)
    public ResultBean getRole(){
        return new ResultBean("200",commonUtil.getRole_list(),"需求类型");
    }


    @ResponseBody
    @RequestMapping(value = "/get-all-count/{companyId}",method = RequestMethod.GET)
    public ResultBean getAllCount(@PathVariable Integer companyId){
        return new ResultBean("200",commonUtil.getAllCount(companyId),"需求类型");
    }

    @ResponseBody
    @RequestMapping(value = "/get-create-mes/{companyId}",method = RequestMethod.GET)
    public ResultBean getCreateMes(@PathVariable Integer companyId){
        return new ResultBean("200",commonUtil.getCreateMes(companyId),"最近七天创建情况");
    }

    @ResponseBody
    @RequestMapping(value = "/get-token",method = RequestMethod.GET)
    public ResultBean getToken(){
        return new ResultBean("200",commonUtil.getToken(),"贴图令牌");
    }

    @ResponseBody
    @RequestMapping(value = "/add-dtype",method = RequestMethod.POST)
    public ResultBean addDtype(@RequestBody Dtype dtype){
        commonUtil.addDtype(dtype);
        return new ResultBean("200","添加结果");
    }

    @ResponseBody
    @RequestMapping(value = "/add-rtype",method = RequestMethod.POST)
    public ResultBean rreqType(@RequestBody Rtype rtype){
        commonUtil.addRtype(rtype);
        return new ResultBean("200","添加结果");
    }
    @ResponseBody
    @RequestMapping(value = "/add-role-type",method = RequestMethod.POST)
    public ResultBean addRole(@RequestBody Role role){
        commonUtil.addRole(role);
        return new ResultBean("200","添加结果");
    }
    @ResponseBody
    @RequestMapping(value = "/add-ttype",method = RequestMethod.POST)
    public ResultBean addTtype(@RequestBody Ttype ttype){
        commonUtil.addTtype(ttype);
        return new ResultBean("200","添加结果");
    }

    @ResponseBody
    @RequestMapping(value = "/get-uptoken",method = RequestMethod.GET)
    public ResultBean getupToken(){
        return new ResultBean("200",commonUtil.getUpToken(),"贴图令牌");
    }

    @ResponseBody
    @RequestMapping(value = "/get-version",method = RequestMethod.GET)
    public ResultBean getVersion(){
        return new ResultBean("200",commonUtil.getVersion(),"系统版本");
    }
}
