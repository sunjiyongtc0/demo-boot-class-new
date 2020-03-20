package com.sunjy.secret.boot.Controller.File;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.ResultVO;
import com.sunjy.secret.boot.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Value("${FilePath}")
    String localPath;
    //final String localPath="E://JAVACODE/JetBrains/IdeaProjects/";
    final String workImgPath="demo-boot-class/src/main/resources/static/userData/img";
    final String workDocPath="demo-boot-class/src/main/resources/static/userData/doc";
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/fileload")
    public ModelAndView fileload(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("File/fileUpload");
        return mav;
    }

   /**
    *
    * 单文件上传的功能
    * */
    @RequestMapping(value = "/upload")
    public JSONObject upload(@RequestParam("file") MultipartFile file) {
        ResultVO rv=new ResultVO();
        rv.setCode(1);
        if (file.isEmpty()) {
            rv.setMsg("file is empty");
            return rv.toJSONObject();
        }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID()+suffixName;
            log.info("上传的文件名为：" + fileName+" 后缀名为" + suffixName);
            // 设置文件存储路径(G盘),你可以存放在你想要指定的路径里面。
            if (FileUtils.upload(file, localPath+workDocPath, fileName)) {
                rv.setMsg("upload success");
                rv.setCode(0);
                rv.setData(workDocPath+"\\" + fileName);
                return rv.toJSONObject();
            }else {
                rv.setMsg("upload failure");
                return rv.toJSONObject();
            }
    }
    /**
     *
     *多文件上传的功能
     * */
    @PostMapping("/batch")
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files =
                ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "E:\\TempFile\\20bak";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "the " + i + " file upload failure";
                }
            } else {
                return "the " + i + " file is empty";
            }
        }
        return "upload Multifile success";
    }
    /**
     *
     *下载文件
     *  */
    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request,
                               HttpServletResponse response) throws Exception{
        String fileName = "Koala.jpg";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("E:\\TempFile\\20bak\\"+fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;fileName="
                        + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "download success";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    bis.close();
                    fis.close();
                }
            }
        }
        return "failure";
    }


/**
 *
 * 图片(头像)上传
 * */
    @RequestMapping("/imageUpload")
    public JSONObject imageUpload(@RequestParam("file") MultipartFile file){
        ResultVO rv=new ResultVO();
        rv.setCode(1);
        if(file.isEmpty()){
            rv.setMsg("图片上传失败");
            return rv.toJSONObject();
        }
        if (file.getSize() / 1000 > 100){
            rv.setMsg("图片大小不能超过100KB");
            rv.setCode(1);
        }
        else{
            //判断上传文件格式
            String fileType = file.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                // 要上传的目标文件存放的绝对路径
                //用src为保存绝对路径不能改名只能用原名，不用原名会导致ajax上传图片后在前端显示时出现404错误-->原因未知

//                final String localPath=request.getServletContext().getRealPath("/static/userData/img");
                //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                //获取文件名
                String fileName = file.getOriginalFilename();
                //获取文件后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //重新生成文件名
                fileName = UUID.randomUUID()+suffixName;
                if (FileUtils.upload(file, localPath+workImgPath, fileName)) {
                    //文件存放的相对路径(一般存放在数据库用于img标签的src)
                    String realPath =  workImgPath+"\\" + fileName;
                    rv.setData(realPath);
                    rv.setMsg("图片上传成功");
                    rv.setCode(0);
                }
                else{
                    rv.setMsg("图片上传失败");
                }
            }
            else{
                rv.setMsg("图片格式不正确");
            }
        }
        return rv.toJSONObject();
    }

}

