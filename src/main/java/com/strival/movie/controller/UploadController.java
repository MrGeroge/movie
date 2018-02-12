package com.strival.movie.controller;


import com.strival.movie.dao.AccountDao;
import com.strival.movie.dao.FormUploadRecordDao;
import com.strival.movie.po.Account;
import com.strival.movie.po.FormUploadRecord;
import com.strival.movie.util.FileClient;
import com.strival.movie.util.OssFileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hao on 2015/10/21.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private FormUploadRecordDao formUploadRecordDao;
    @Autowired
    private AccountDao accountDao;

    private final static Logger logger= LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/img")//,produces = "text/html")//method = RequestMethod.POST)
    public Map<String,Object> uploadImg(
            @RequestParam(value = "file",required = true) MultipartFile file)
            throws IOException{
        Map<String,Object> result = new HashMap<>();

        FileClient fileClient=new OssFileClient();

        if (!file.isEmpty()) {
            int dot =file.getOriginalFilename().lastIndexOf('.');
            if ((dot >-1) && (dot < (file.getOriginalFilename().length() - 1))) {
                String fileExt= file.getOriginalFilename().substring(dot + 1);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String fileName=sdf.format(new Date())+"_"+String.valueOf(new Random().nextInt(899) + 100)+"."+fileExt;
                //将字节流转换为输入流
                InputStream inputStream = new ByteArrayInputStream(file.getBytes());

                String url=fileClient.save(fileName,inputStream,file.getSize());

                result.put("link",url);

                logger.info("upload image file,info url="+url);
            }
        }

        result.put("success",true);
        return result;
        //return new ResponseEntity(String.format("{\"success\":true,\"link\":%s}",result.get("link")), HttpStatus.OK);
    }

    @RequestMapping(value = "/form")//,produces = "text/html")//method = RequestMethod.POST)
    public ResponseEntity<String> uploadForm(
            HttpServletRequest request,
            @RequestParam(value = "file",required = true) MultipartFile file)
            throws IOException{
        Map<String,Object> result = new HashMap<>();

        FileClient fileClient=new OssFileClient();

        if (!file.isEmpty()) {
            int dot =file.getOriginalFilename().lastIndexOf('.');
            if ((dot >-1) && (dot < (file.getOriginalFilename().length() - 1))) {
                String fileExt= file.getOriginalFilename().substring(dot + 1);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String fileName=sdf.format(new Date())+"_"+String.valueOf(new Random().nextInt(899) + 100)+"."+fileExt;
                //将字节流转换为输入流
                InputStream inputStream = new ByteArrayInputStream(file.getBytes());

                String url=fileClient.save(fileName,inputStream,file.getSize());
               long accountId=(long) request.getSession().getAttribute("accountId");
                Account account=accountDao.findOne(accountId);
                FormUploadRecord formUploadRecord=new FormUploadRecord();
                formUploadRecord.setAccount(account);
                formUploadRecord.setUploadTime(new Date());
                formUploadRecord.setUploadUrl(url);
                formUploadRecordDao.save(formUploadRecord);
            }
        }
        return new ResponseEntity<String>("success",null,HttpStatus.OK);
        //return new ResponseEntity(String.format("{\"success\":true,\"link\":%s}",result.get("link")), HttpStatus.OK);
    }
}
