package com.web.twozqj.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: PhotoUtil
 * Author:   落叶尘纷
 * Date:     2019/9/15 16:23
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */

public class PhotoUtil {

    public static String upload(MultipartFile  file,HttpServletRequest request) throws IOException {
        //判断上传文件是否为空
        if(file.getSize()>0) {
            //获取服务器的绝对路径
            String path = request.getSession().getServletContext().getRealPath("");
            //通过UUID产生一个文件名称（新名称）
            String newFileName=UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //根据服务器的路经和新名称创建file对象
            File file2 = new File(path+"/upload/"+newFileName);
            //判断新文件对象是否存在
            if(!file2.exists()) {
                //创建新文件对象的父级目录（upload文件夹）
                file2.getParentFile().mkdirs();
                //创建新文件对象
                file2.createNewFile();
            }
            try {
                //将上传的文件对象赋值到新文件对象中
                file.transferTo(file2);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "/upload/"+newFileName;
        }else {
            return "";
        }

    }

    public static String download(String filepath, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;

        try {
            // 如果是从服务器上取就用这个获得系统的绝对路径方法。
            // String filepath = request.getRealPath(filepatha);//方法过时了
            String filepathall = request.getSession().getServletContext().getRealPath(filepath);

            File uploadFile = new File(filepathall);

            // 图片对象流
            fis = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);

            // 得到文件名
            String filename = filepath.substring(filepath.lastIndexOf("\\") + 1);

            // 这个就就是弹出下载对话框的关键代码
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));

            int bytesRead = 0;
            // 用输出流去写，缓冲输入输出流
            byte[] buffer = new byte[8192];
            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                }
                if (fis != null) {
                    fis.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
