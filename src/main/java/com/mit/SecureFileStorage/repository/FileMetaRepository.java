package com.mit.SecureFileStorage.repository;

import com.mit.SecureFileStorage.Model.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileMetaRepository extends JpaRepository<FileMeta, Long> {
    List<FileMeta> findByUsername(String username);
}
