package com.springboot.titlebot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitleDto {
    private Long id;
    private String faviconUrl;
    private String title;
    private String url;
}
