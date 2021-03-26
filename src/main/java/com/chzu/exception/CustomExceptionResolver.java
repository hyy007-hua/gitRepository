package com.chzu.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理器
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CustomException customException = null;
        String message = "";
        // 若该异常类型是系统自定义的异常，直接取出异常信息在错误页面展示即可。
        if(ex instanceof CustomException){
            customException = (CustomException)ex;
            customException.getThrowable().getClass().getName();
        }else{
            // 如果不是系统自定义异常，构造一个系统自定义的异常类型，信息为“未知错误”
            customException = new CustomException("未知错误");
            message = customException.getMessage();
        }
        //输出错误信息，便于在控制台查错
        System.out.println("发生错误了，错误信息如下：");
        System.out.println(ex);
        System.out.println();
        //错误信息
        ModelAndView modelAndView = new ModelAndView();
        //将错误信息传到页面
        modelAndView.addObject("message",message);
        //指向错误页面
        modelAndView.setViewName("404");
        return modelAndView;
    }
}
