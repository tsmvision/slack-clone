package com.example.backend.repository;

import com.example.backend.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long>, ChannelRepositoryCustom {
    Optional<Channel> findByName(String name);
    Optional<Channel> findById(Long id);
}
