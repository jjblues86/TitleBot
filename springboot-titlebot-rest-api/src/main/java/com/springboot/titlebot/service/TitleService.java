package com.springboot.titlebot.service;

import com.springboot.titlebot.dto.HistoryUrlDto;
import com.springboot.titlebot.dto.TitleDto;

import java.util.List;

/**
 * Interface for TitleService
 */
public interface TitleService {
    TitleDto saveTitleUrl(String titleUrl, String userId);
    List<TitleDto> getAllTitles();
    List<HistoryUrlDto> getUrlHistory();
    List<TitleDto> getTitlesByUser(String userId);
}
