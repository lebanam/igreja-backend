package com.igreja360.backend.repository;

import com.igreja360.backend.model.Igreja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IgrejaRepository extends JpaRepository<Igreja, Long> {
}