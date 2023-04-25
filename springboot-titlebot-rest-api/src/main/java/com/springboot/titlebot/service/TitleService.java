package com.springboot.titlebot.service;

import com.springboot.titlebot.dto.TitleDto;

import java.util.List;

/**
 * Interface for TitleService
 */
public interface TitleService {
    TitleDto saveTitle(TitleDto titleDto);
    TitleDto getTitle(String titleUrl);
    List<TitleDto> getAllTitles();
}
