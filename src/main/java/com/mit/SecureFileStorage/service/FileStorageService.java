package com.mit.SecureFileStorage.service;

import com.mit.SecureFileStorage.Model.FileMeta;
import com.mit.SecureFileStorage.repository.FileMetaRepository;
import  com.mit.SecureFileStorage.util.FileEncryptionUtil;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("encrypted-files");

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(rootLocation);
    }


    @Autowired
    private FileMetaRepository fileMetaRepository;

    public void saveEncryptedFile(MultipartFile file, String username) throws Exception {
        SecretKey key = FileEncryptionUtil.generateKey();
        byte[] encryptedData = FileEncryptionUtil.encrypt(file.getBytes(), key);

        Path userDir = rootLocation.resolve(username);
        Files.createDirectories(userDir);

        // Save encrypted file and key
        Files.write(userDir.resolve(file.getOriginalFilename()), encryptedData);
        Files.write(userDir.resolve(file.getOriginalFilename() + ".key"), FileEncryptionUtil.saveKeyToBytes(key));

        // Save metadata to DB
        FileMeta meta = new FileMeta();
        meta.setFilename(file.getOriginalFilename());
        meta.setUsername(username);
        meta.setSize(file.getSize());
        meta.setUploadedAt(LocalDateTime.now());
        fileMetaRepository.save(meta);
    }

    public byte[] loadAndDecryptFile(String filename, String username) throws Exception {
        Path userDir = rootLocation.resolve(username);
        Path filePath = userDir.resolve(filename);
        Path keyPath = userDir.resolve(filename + ".key");

        byte[] encryptedData = Files.readAllBytes(filePath);
        byte[] keyBytes = Files.readAllBytes(keyPath);

        SecretKey key = FileEncryptionUtil.loadKey(keyBytes);
        return FileEncryptionUtil.decrypt(encryptedData, key);
    }
    
    
}
