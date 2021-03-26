package com.chzu.controller;

import com.chzu.util.ImageCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author hyy
 * @Desc 验证码生成接口
 */
@Api(value = "验证码图片生成接口",tags = "验证码图片生成接口")
@RestController
public class CheckCodeImgController {

    @ApiOperation(value = "验证码图片生成接口",notes = "验证码图片生成接口",httpMethod = "GET")
    @GetMapping("/identifyCode/img")
    public void getIdentifyImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置不缓存图片
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","No-cache");
        response.setDateHeader("Expires",0);
        //指定生成的响应图片
        response.setContentType("image/jpeg");
        ImageCode imageCode = new ImageCode();
        BufferedImage image = imageCode.getBufferedImage();
        HttpSession session = request.getSession(true);
        //存储验证码数据到Session中
        session.setAttribute("IdentifyCode",imageCode.getCode());
        //将图形验证码IO流传输至前端
        imageCode.write(response.getOutputStream());
    }
}
