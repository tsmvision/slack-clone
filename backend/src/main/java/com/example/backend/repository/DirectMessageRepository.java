package com.example.backend.repository;

import com.example.backend.entity.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long>, DirectMessageRepositoryCustom {
    Optional<DirectMessage> findById(Long id);
}
