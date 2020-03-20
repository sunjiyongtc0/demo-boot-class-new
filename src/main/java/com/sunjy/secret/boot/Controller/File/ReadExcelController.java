package com.sunjy.secret.boot.Controller.File;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/file")
public class ReadExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ReadExcelController.class);
    @Autowired
    HttpServletResponse   response;
    @Autowired
    HttpServletRequest    request;

    @PostMapping("/readExc")
    public  String ReadExc(@RequestParam("file") MultipartFile file) throws Exception{
        InputStream is = file.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        XSSFRow titleCell = xssfSheet.getRow(0);
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            XSSFRow xssfRow = xssfSheet.getRow(i);
            int minCell = xssfRow.getFirstCellNum();
            int maxCell = xssfRow.getLastCellNum();
            XSSFCell unnme = xssfRow.getCell(0);
            XSSFCell unurl = xssfRow.getCell(1);
            System.out.println("大学名："+unnme.toString()+"====大学地址："+unurl.getStringCellValue());
        }

        return "File/fileUpload";
    }

    @GetMapping("/readZip")
    public String ReadZip() throws Exception{
//        String fileName =request.getParameter("fName");
//        String reportTime = request.getParameter("reportTime");
        String fileName="链路拨测质量报表(日报)_20200401.xls,链路拨测质量报表(月报)_20200301.xls,链路拨测质量报表(日报)_20200331.xls";
        String reportTime="20200401,20200301,20200331";
//--------------------------------------------------------
        try {
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition","attachment; filename=\"" + System.currentTimeMillis() + ".zip\"");
            String[] fileNames=fileName.split(",");
            String[] reportTimes = reportTime.split(",");

            List<File> lf=new ArrayList<File>();
            for(int i=0;i<fileNames.length;i++){
                //File f=new File(PathKit.getWebRootPath()+ "/TempFile/"+reportTimes[i]+"/"+fileNames[i]);
//                File f=new File(ResourceUtils.getURL("classpath:").getPath()+ "/TempFile/"+reportTimes[i]+"/"+fileNames[i]);
                File f=new File("D:\\TOMCATS/apache-tomcat-7.0.55-yd/webapps/acsno-web/TempFile/"+reportTimes[i]+"/"+fileNames[i]);
                if(!f.exists()){
                    continue;
                }
                lf.add(f);
            }
            compressZip(lf,response.getOutputStream());
        } catch (Exception e) {
            logger.error("下载历史记录失败，请检验服务是否打开。",e);
            response.reset();
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.getOutputStream().write("下载历史记录失败，请检验服务是否打开。<a href='javascript:history.go(-1);'>返回</a>".getBytes("utf-8"));
        }
        return "File/fileUpload";
    }


    /**
     * 将多个文件压缩到指定输出流中
     *
     * @param files 需要压缩的文件列表
     * @param outputStream  压缩到指定的输出流
     */
    public static void compressZip(List<File> files, OutputStream outputStream) {
        ZipOutputStream zipOutStream = null;
        try {
            //-- 包装成ZIP格式输出流
            zipOutStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
            // -- 设置压缩方法
            zipOutStream.setMethod(ZipOutputStream.DEFLATED);
            //-- 将多文件循环写入压缩包
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                FileInputStream filenputStream = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                filenputStream.read(data);
                //-- 添加ZipEntry，并ZipEntry中写入文件流，这里，加上i是防止要下载的文件有重名的导致下载失败
                zipOutStream.putNextEntry(new ZipEntry(i + file.getName()));
                zipOutStream.write(data);
                filenputStream.close();
                zipOutStream.closeEntry();
            }
        } catch (IOException e) {
            logger.error("downloadallfiles", e);
        }  finally {
            try {
                if (Objects.nonNull(zipOutStream)) {
                    zipOutStream.flush();
                    zipOutStream.close();
                }
                if (Objects.nonNull(outputStream)) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("downloadallfiles", e);
            }
        }
    }
}
