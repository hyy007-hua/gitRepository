package com.chzu.util;

import com.chzu.enums.FileType;
import com.chzu.model.TbFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadFileUtil {

    /**
     * 将文件存到指定地址并返回文件存储的相关信息
     * @param multFile
     * @param request
     * @param upId
     * @param fileType
     * @return
     * @throws IOException
     */
    public static TbFile uploadFile(MultipartFile multFile, HttpServletRequest request,String upId,FileType fileType) throws IOException {
        TbFile file = new TbFile();
        file.setId(SmallUtil.getGeneratID());

        String fileName;
        // 1. 获取原始文件名
        String uploadFileName = multFile.getOriginalFilename();
        String picname = uploadFileName.substring(0,uploadFileName.indexOf("."));
        file.setFileName(picname);

        // 2. 截取文件扩展名
        String extendName = uploadFileName.substring(
                uploadFileName.lastIndexOf(".") + 1
        );

        String datePath = null;
        //文件分类
        datePath = fileType.value;
        file.setFileType(fileType.type);

        // 3. 把文件加上随机数，方止文件重复
        String uuid = SmallUtil.getId()
                .replace("-", "")
                .toUpperCase();

        // 4. 判断是否输入了文件名
        if(!StringUtils.isEmpty(picname)) {
            fileName = uuid + "_" + picname + "." + extendName;
        } else {
            fileName = uuid + "_" + uploadFileName;
        }

        // 获取文件路径
        //ServletContext context = request.getServletContext();
        //context.getRealPath("/uploads");
        String basePath = "E:\\design-manager-system\\uploads";

        // 解决同一文件夹中文件过多问题
        //String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        // 判断路径是否存在: 发布的路径，并不是工作空间的路径！
        String realPath = basePath + "\\" + datePath;

        //文件真实地址
        file.setFilePath(realPath + "\\" + fileName);

        File localFile = new File(realPath);
        if(!localFile.exists()) {
            localFile.mkdirs();
        }

        //文件上传时间
        file.setUpTime(new Date());

        // 使用MultipartFile接口中的方法，把上传的文件写到指定位置
        multFile.transferTo(new File(localFile, fileName));
        file.setUpId(upId);

        return file;
    }
}
