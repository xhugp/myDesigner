package com.xhu.entity;

import com.xhu.entity.LReq;

/**
 * Created by gp on 2018/3/30.
 */
public enum CodeEnum {
    //公共
    SUCCESS("200","请求成功"),
    ERROR("300","内部错误"),
    PARAMETER("301","参数错误"),
    //LReq错误40100
    REREQL("40102","重复提交请求"),
    TOOLLEASED("40103","该工具出租中"),
    TOOLSTATE("40104","更新工具状态错误"),
    LREQCOUNT("40105","获取记录总数失败"),
    LEASENULL("40106","未查询到该物品的租赁信息"),
    //Demand错误40200
    DEMANDCOUNT("40201","获取需求总数错误"),
    DEMANDDELETEBATCH("40202","批量删除失败"),
    DEMANDDELETE("40203","删除失败"),
    DEMANDADD("40204","添加失败"),
    DEMANDLIST("40205","查询需求列表失败"),
    REUPDATE("40206","重提需求失败"),
    DEMANDRELOADERROR("40207","需求无法重提"),
    //Tool错误40300
    //Commmon错误40500
    //Order错误40700
    //Sign错误40800
    //User错误40900
    EMPTYUSER("40901","用户名和密码为空"),
    NONUSER("40902","用户不存在"),
    PWDERROR("40903","密码错误"),
    USERCOUNT("40904","获取用户总数失败"),
    SELECTUSER("40905","查询用户错误"),
    //Message错误41000
    //Lease错误41100
    //Company错误41200
    //Manage错误41300
    //Type错误41400
    //Oreq错误41500
    REOREQ("41501","重复提交请求"),
    OREQCOUNT("41504","获取记录总数失败"),
    ORDERERROR("41505","订单状态不允许提交申请"),
    USERERROR("41506","这不是该用户的订单"),
    //Sreq错误41600
    SREQLISTPARAM("41601","列表请求参数错误"),
    SREQDELETEPARAM("41602","删除请求参数错误"),
    SREQGETPARAM("41603","查看参数错误"),
    SREQADDPARAM("41604","添加参数错误"),
    SREQREADD("41605","重复请求"),
    SREQADDUSERERROR("41606","用户已经签约"),
    SREQUPDATEPARAM("41607","更新参数错误"),
    SREQREUPDATE("41608","重复更新"),
    HANDLEERROR("41609","该请求您无法处理"),
    //Rreq错误
    RERREQ("41701","重复应聘");





    private String code;
    private String message;

    CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
