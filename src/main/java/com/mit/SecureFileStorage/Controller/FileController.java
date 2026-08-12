package com.mit.SecureFileStorage.Controller;

import com.mit.SecureFileStorage.repository.FileMetaRepository;
import com.mit.SecureFileStorage.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileMetaRepository fileMetaRepository;

    //  Get authenticated user's email
    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString(); // fallback
    }

    // Upload file (secured)
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String username = getCurrentUsername();
        try {
            fileStorageService.saveEncryptedFile(file, username);
            return ResponseEntity.ok("File uploaded and encrypted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    //  Download file (secured)
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        String username = getCurrentUsername();
        try {
            byte[] data = fileStorageService.loadAndDecryptFile(filename, username);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    //  List files for the current user (secured)
    @GetMapping("/list")
    public ResponseEntity<?> listFiles() {
        String username = getCurrentUsername();
        return ResponseEntity.ok(fileMetaRepository.findByUsername(username));
    }
}
