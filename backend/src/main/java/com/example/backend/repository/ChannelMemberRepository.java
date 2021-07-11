package com.example.backend.repository;

import com.example.backend.entity.ChannelMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelMemberRepository extends JpaRepository<ChannelMember, Long> {
}
