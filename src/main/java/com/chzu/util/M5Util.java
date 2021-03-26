package com.chzu.util;

import org.springframework.util.DigestUtils;

import java.util.logging.Logger;

public class M5Util {

    public static String md5encode(String text) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5DigestAsHex(text.getBytes());
        //System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    public static boolean verify(String text, String md5) {
        //根据传入的密钥进行验证
        String md5Text = null;
        try{
             md5Text = md5encode(text);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }finally {
            if(md5Text.equals(md5))
            {
                System.out.println("MD5验证通过");
                return true;
            }
            return false;
        }

    }

}
