package info.nguyenanhdung.spingapi.demo.controllers;

import info.nguyenanhdung.spingapi.demo.models.ResponseObject;
import info.nguyenanhdung.spingapi.demo.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
    // Inject Storage Service here - Su dung Autowired de inject
    @Autowired
    private IStorageService storageService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            //save files to a folder => use
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok","upload file successfully", generatedFileName)
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed",exception.getMessage(), "")
            );
        }
    }
    //Get image's url
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    // How to load all uploaded files
//    @GetMapping("")
//    public ResponseEntity<List<String>> getUploadedFile() {
//        try {
//            List<String> urls = storageService.loadAll()
//                    .map(path -> {
//                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
//                        return urlPath;
//                    })
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
//        } catch (Exception e) {
//            return ResponseEntity.ok(new ResponseObject("failed", "List files failed", new String[] {}));
//        }
//    }
}
