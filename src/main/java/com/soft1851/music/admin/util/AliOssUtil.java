package com.soft1851.music.admin.util;

import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/5/1
 */
@Slf4j
public class AliOssUtil {
    public static String upload(MultipartFile sourceFile) {
        // 获取文件名
        String fileName = sourceFile.getOriginalFilename();
        //uuid生成主文件名
        String prefix = UUID.randomUUID().toString();
        assert fileName != null;
        //源文件的扩展名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //创建File类型的临时文件
        File tempFile = null;
        try {
            tempFile = File.createTempFile(prefix, suffix);
            // 将MultipartFile转换成File
            sourceFile.transferTo(tempFile);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        assert tempFile != null;
        return upload(tempFile);
    }


    public static String upload(File file) {
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI**********KBcVF";
        String accessKeySecret = "DBUG**************fmeI47IpcB";
        String bucketName = "student-manage99";
        String filePath = "avatar/";
        String fileName = file.getName();
        String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件到指定位置，并使用UUID更名
        ossClient.putObject(bucketName, filePath + newFileName, file);
        // 拼接URL
        String url = "https://student-manage99.oss-cn-hangzhou.aliyuncs.com/" + filePath + newFileName;
        ossClient.shutdown();
        return url;
    }

    public static void main(String[] args) {
        File file = new File("D:\\resources\\all_pictures\\avatar\\Naruto.png");
        upload(file);
    }
}
