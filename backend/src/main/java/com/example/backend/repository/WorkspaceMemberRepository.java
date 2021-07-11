package com.example.backend.repository;

import com.example.backend.entity.WorkspaceMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long>, WorkspaceMemberRepositoryCustom {
}
