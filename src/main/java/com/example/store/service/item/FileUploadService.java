package com.example.store.service.item;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String saveFile(MultipartFile file);
    String saveFile(byte[] fileData, String filename); // 새로운 메서드 추가
}
