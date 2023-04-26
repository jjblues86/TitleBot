package com.springboot.titlebot.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "titles")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "favicon_url")
    private String faviconUrl;
    @Column(name = "title")
    private String title;
    @NonNull
    @Column(name = "url")
    private String url;
    @NonNull
    @Column(name = "user_id")
    private String userId;
}
