package com.kads.demo.repo;

import com.kads.demo.model.DocumentStorageProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDBRepository extends JpaRepository<DocumentStorageProps, String> {

}