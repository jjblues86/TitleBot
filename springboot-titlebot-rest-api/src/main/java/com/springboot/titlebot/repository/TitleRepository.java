package com.springboot.titlebot.repository;

import com.springboot.titlebot.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {
    Title findByTitle(String urlTitle);

    List<Title> findByUserId(String userId);
}
