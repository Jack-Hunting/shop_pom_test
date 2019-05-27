package com.qf.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @vervion 1.0
 * @date 2019/05/20 21:09
 * @user Jack-Hunting
 */
@Controller
@RequestMapping("/res")
public class ResController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 上传图片
     * @return
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public Map<String,String> uploadImg(MultipartFile file){
        //获取图片名
        String filename = file.getOriginalFilename();
        //获取图片大小
        long fileSize = file.getSize();
//      截取图片后缀
        int index = filename.lastIndexOf(".");
        String houzui = filename.substring(index + 1);

        Map<String,String> map = new HashMap<>();

        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(),fileSize,houzui,null);

            map.put("checkCode","1000");
            map.put("filepath", storePath.getFullPath());
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("checkCode","0000");
        return map;
    }
}
