package com.chzu.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class SmallUtil {
    public static String getId(){
        return UUID.randomUUID().toString();
    }
    public static String getDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    public static Timestamp getTimestamp( String str) throws ParseException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time.valueOf(str);
    }

    public static String getRandomNum(int num){
        String numStr = "";
        for(int i = 0; i < num; i++){
            numStr += (int)(10*(Math.random()));
        }
        return numStr;
    }

    /**
     * 创造一定范围内的随机数
     * @param max
     * @param min
     * @return
     */
    public static int createRandomNumber(int max,int min){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 得到一定范围内不重复的随机数组
     * @param max
     * @param min
     * @return
     */
    public static int[] createRandomList(int max,int min){
        int length = max - min + 1;
        int[] result = new int[length];

        //1.生成随机数
        //2.检查该随机数是否重复
        //3.重复则跳回第一步，否则将该随机数添加到随机数组
        //4.判断随机数组是否满了，满了则结束返回该随机数组
        for(int i=0;i < length-1;i++){
            boolean flag = true;
            int num = 0;
            while(flag){
                num = createRandomNumber(max,min);
                int j = 0;
                //检查随机数
                for(;j <= i;j ++){
                    if(result[j] == num)
                        break;
                }
                //该随机数通过检查
                if(j == i + 1)
                    flag = false;
            }
            result[i] = num;
        }

        return result;
    }

    /**
     * 根据时间、随机数生成唯一ID
     * @return
     */
    public static String getGeneratID(){
        String sformat = "MMddhhmmssS";
        int num = 5;
        String idStr = getDate(sformat) + getRandomNum(num);
        return idStr;
    }

    public static void main(String[] args) {
        int[] re = createRandomList(20,0);
        for(int i = 0;i<re.length;i ++){
            System.out.print(re[i] + "    ");
        }
    }

}
