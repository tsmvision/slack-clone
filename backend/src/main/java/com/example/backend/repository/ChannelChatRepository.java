package com.example.backend.repository;

import com.example.backend.entity.ChannelChat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelChatRepository extends JpaRepository<ChannelChat, Long> {
}
