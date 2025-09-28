package com.lcwd.todo.todocontroller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class Filecontroller {
    Logger logger = LoggerFactory.getLogger(Filecontroller.class);
    @PostMapping("/single")
    public String uploadingsingle(@RequestParam("image")MultipartFile file){
        logger.info("Name: {}",file.getName());
        logger.info("Contenttype: {}",file.getContentType());
        logger.info("Original file name: {}",file.getOriginalFilename());
        logger.info("File size: {}",file.getSize());

      return "File Test";
    }
    @PostMapping("/multiple")
    public String uploadingmultiple(@RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file->{
            logger.info("file type: {}",file.getContentType());
            logger.info("file name: {}",file.getOriginalFilename());
            System.out.println("++++++++++++++++++++++++");
        }) ;
        return "handling multiple files";
    }
    // serving image file response
    @GetMapping("/serve-img")
    public void serveImageHandler(HttpServletResponse response){
      try{
          InputStream fileInputStream=new FileInputStream("images/IMG-20");
          response.setContentType(MediaType.IMAGE_JPEG_VALUE);
          StreamUtils.copy(fileInputStream,response.getOutputStream());

      } catch (Exception e){
          e.printStackTrace();
      }
    }
}
