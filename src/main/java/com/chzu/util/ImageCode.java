package com.chzu.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ImageCode {
    private int width = 200;
    private int height = 50;
    private int codeCount = 4;
    private int lineCount = 50;
    private String code = null;
    private BufferedImage bufferedImage = null;
    private char[] codeSequence = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',
        's','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
    private Random random = new Random();

    public ImageCode(){
        this.createCode();
    }

    public ImageCode(int width,int height){
        this.width = width;
        this.height = height;
        this.createCode();
    }

    public void createCode(){
        int codeX = 0;
        int fontHeight = 0;
        fontHeight = height -5;
        codeX = width / (codeCount+3);
        bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0,width,height);
        Font font = new Font("",Font.PLAIN,20);
        graphics2D.setFont(font);

        StringBuffer randomCode = new StringBuffer();
        for (int i=0;i<codeCount;i++){
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            graphics2D.setColor(getRandomColor());
            graphics2D.drawString(strRand,(i+1)*codeX,getRandomNumber(height/2)+20);
            randomCode.append(strRand);
        }
        code = randomCode.toString();
    }

    private Color getRandomColor(){
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r,g,b);
    }

    private int getRandomNumber(int number){
        return random.nextInt(number);
    }

    public void write(String path) throws IOException{
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }
    public void write(OutputStream sos) throws IOException{
        ImageIO.write(bufferedImage,"png",sos);
        sos.close();
    }
    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
    public String getCode(){
        return code;
    }
}
