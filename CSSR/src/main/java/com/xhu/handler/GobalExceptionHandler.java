package com.xhu.handler;

import com.xhu.dto.ResultBean;
import com.xhu.entity.CodeEnum;
import com.xhu.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gp on 2018/2/25.
 */
@ControllerAdvice
public class GobalExceptionHandler {
    /**
     * 处理Student异常
     * @param req
     * @param e
     * @return
             */
    @ExceptionHandler(value = StudentException.class)
    @ResponseBody
    private ResultBean studentExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 处理Manage异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ManageException.class)
    @ResponseBody
    private ResultBean manageExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 处理Demand异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = DemandException.class)
    @ResponseBody
    private ResultBean demandExceptionHandler(HttpServletRequest req, DemandException e){
        return new ResultBean(e.getCode(),e.getMes());
    }

    /**
     * 处理Company异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CompanyException.class)
    @ResponseBody
    private ResultBean companyExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 处理Type异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = TypeException.class)
    @ResponseBody
    private ResultBean typeExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 处理User异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    private ResultBean userExceptionHandler(HttpServletRequest req, UserException e){
        return new ResultBean(e.getCode(),e.getMes());
    }

    /**
     * 处理Lease异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = LeaseException.class)
    @ResponseBody
    private ResultBean leaseExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 处理Message异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MessageException.class)
    @ResponseBody
    private ResultBean messageExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 处理Order异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = OrderException.class)
    @ResponseBody
    private ResultBean orderExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 处理Sign异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = SignException.class)
    @ResponseBody
    private ResultBean signExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }
    /**
     * 处理LReq异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = LReqException.class)
    @ResponseBody
    private ResultBean lReqExceptionHandler(HttpServletRequest req, LReqException e){
        return new ResultBean(e.getCode(),e.getMes());
    }

    /**
     * 处理Rreq异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = RreqException.class)
    @ResponseBody
    private ResultBean RreqExceptionHandler(HttpServletRequest req, RreqException e){
        return new ResultBean(e.getCode(),e.getMes());
    }

    /**
     * 处理Sreq异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = SreqException.class)
    @ResponseBody
    private ResultBean SreqExceptionHandler(HttpServletRequest req, SreqException e){
        return new ResultBean(e.getCode(),e.getMes());
    }



    /**
     * 处理Oreq异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = OreqException.class)
    @ResponseBody
    private ResultBean oreqExceptionHandler(HttpServletRequest req, OreqException e){
        return new ResultBean(e.getCode(),e.getMes());
    }

    /**
     * 处理Tool异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ToolException.class)
    @ResponseBody
    private ResultBean toolExceptionHandler(HttpServletRequest req, Exception e){
        return new ResultBean("300",e.getMessage());
    }

    /**
     * 运行时异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    private ResultBean CommonHandler(HttpServletRequest req,Exception e){
        return new ResultBean("201",e.getMessage());
    }

    /**
     * 其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private ResultBean ExceptionHandler(HttpServletRequest req,Exception e){
        return new ResultBean(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage()+"内容："+e.getMessage());
    }
}
