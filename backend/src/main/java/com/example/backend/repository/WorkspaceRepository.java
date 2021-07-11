package com.example.backend.repository;

import com.example.backend.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long>, WorkspaceRepositoryCustom {
    Optional<Workspace> findById(Long id);
    List<Workspace> findAll();
}
