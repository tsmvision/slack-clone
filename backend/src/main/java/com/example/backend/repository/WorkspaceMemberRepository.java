package com.example.backend.repository;

import com.example.backend.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceMemberRepository extends JpaRepository<Workspace, Long> {
}
