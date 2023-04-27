package com.springboot.titlebot.service.Impl;

import com.springboot.titlebot.dto.TitleDto;
import com.springboot.titlebot.entity.Title;
import com.springboot.titlebot.repository.TitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TitleServiceImplTest {

    private TitleServiceImpl titleService;

    @Mock
    private TitleRepository titleRepository;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        titleService = new TitleServiceImpl(titleRepository, mapper);
    }

    @Test
    void saveTitleUrl_validUrlAndTitleExists_titleRetrieved() {
        // Arrange
        String titleUrl = "https://example.com";
        String userId = "user1";
        TitleDto titleDto = new TitleDto();
        titleDto.setFaviconUrl(titleUrl + "/favicon.ico");
        titleDto.setUrl(titleUrl);
        titleDto.setTitle("Example Domain");
        titleDto.setUserId(userId);
        Title title = new Title();
        when(titleRepository.findByTitle(titleDto.getTitle())).thenReturn(title);
        when(mapper.map(title, TitleDto.class)).thenReturn(titleDto);

        // Act
        TitleDto savedTitle = titleService.saveTitleUrl(titleUrl, userId);

        // Assert
        assertNotNull(savedTitle);
        assertEquals(titleDto, savedTitle);
        verify(titleRepository).findByTitle(titleDto.getTitle());
        verify(mapper).map(title, TitleDto.class);
        verify(titleRepository, never()).save(any());
        verify(mapper, never()).map(titleDto, Title.class);
    }

    @Test
    void getAllTitles_hasTitles_returnAllTitles() {
        // Arrange
        Title title1 = new Title();
        Title title2 = new Title();
        List<Title> titles = new ArrayList<>();
        titles.add(title1);
        titles.add(title2);
        TitleDto titleDto1 = new TitleDto();
        TitleDto titleDto2 = new TitleDto();
        List<TitleDto> expectedTitles = new ArrayList<>();
        expectedTitles.add(titleDto1);
        expectedTitles.add(titleDto2);
        when(titleRepository.findAll()).thenReturn(titles);
        when(mapper.map(title1, TitleDto.class)).thenReturn(titleDto1);
        when(mapper.map(title2, TitleDto
                .class)).thenReturn(titleDto2);
        // Act
        List<TitleDto> actualTitles = titleService.getAllTitles();
        // Assert
        assertNotNull(actualTitles);
        assertEquals(expectedTitles, actualTitles);
        verify(titleRepository).findAll();
        verify(mapper).map(title1, TitleDto.class);
        verify(mapper).map(title2, TitleDto.class);
        verify(titleRepository, never()).save(any());
        verify(mapper, never()).map(titleDto1, Title.class);
        verify(mapper, never()).map(titleDto2, Title.class);
        verify(titleRepository, never()).findByTitle(anyString());
        verify(titleRepository, never()).findByUserId(anyString());
    }
}
