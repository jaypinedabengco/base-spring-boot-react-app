package com.bengco.app.react.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bengco.app.react.model.SamplePaginationItem;

@Repository
public interface SamplePaginationItemRepository extends JpaRepository<SamplePaginationItem, Long> {

	Page<SamplePaginationItem> findAll(Pageable pageable);
}
