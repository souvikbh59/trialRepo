package com.example.DemoController;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1")
public class DemoController {
    private static final String UPLOAD_PATH = "/Souvik APP/";

    @GetMapping("/data")
    public String getdata(){
        return "Hello Folks !!";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file")MultipartFile multipartFile){
        if (!multipartFile.isEmpty()){
            try{
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(UPLOAD_PATH+multipartFile.getOriginalFilename());
                Files.write(path,bytes);
                return "File upload Success !!" + multipartFile.getOriginalFilename();

            } catch (IOException e) {
                return  "Error" + e.getMessage();
            }
            catch (MaxUploadSizeExceededException e) {
                return  "Error Upload Size" + e.getMessage();
            }

        } else {
            return "no file found";
        }
    }
}
