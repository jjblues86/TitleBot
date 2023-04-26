package com.springboot.titlebot.controller;

import com.springboot.titlebot.dto.TitleDto;
import com.springboot.titlebot.service.TitleService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TitleControllerTest {

    private TitleController titleController;

    @Mock
    private TitleService titleService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        titleController = new TitleController(titleService);
    }

    @Test
    void saveTitle_shouldReturnSavedTitle() {
        // given
        TitleDto titleDto = new TitleDto();
        titleDto.setUrl("https://example.com");
        titleDto.setUserId("user1");
        TitleDto savedTitle = new TitleDto();
        savedTitle.setId(1L);
        savedTitle.setTitle("Example Title");

        when(titleService.saveTitleUrl(any(String.class), any(String.class))).thenReturn(savedTitle);
        when(httpServletRequest.getHeader("user-id")).thenReturn("user1");

        // when
        ResponseEntity<TitleDto> responseEntity = titleController.saveTitle(titleDto);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedTitle, responseEntity.getBody());
    }

    @Test
    void getAllTitles_shouldReturnAllTitles() {
        // given
        List<TitleDto> titles = new ArrayList<>();
        TitleDto title1 = new TitleDto();
        title1.setId(1L);
        title1.setTitle("Title 1");
        TitleDto title2 = new TitleDto();
        title2.setId(2L);
        title2.setTitle("Title 2");
        titles.add(title1);
        titles.add(title2);

        when(titleService.getAllTitles()).thenReturn(titles);

        // when
        ResponseEntity<List<TitleDto>> responseEntity = titleController.getAllTitles();

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(titles, responseEntity.getBody());
    }

//    @Test
//    void getAllUrls_shouldReturnAllUrls() {
//        // given
//        List<String> urls = new ArrayList<>();
//        urls.add("https://example1.com");
//        urls.add("https://example2.com");
//
//        when(titleService.getUrlHistory()).thenReturn(urls);
//
//        // when
//        ResponseEntity<List<String>> responseEntity = titleController.getAllUrls();
//
//        // then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(urls, responseEntity.getBody());
//    }

    @Test
    void getTitlesByUser_shouldReturnTitlesByUser() {
        // given
        String userId = "user1";
        List<TitleDto> titles = new ArrayList<>();
        TitleDto title1 = new TitleDto();
        title1.setId(1L);
        title1.setTitle("Title 1");
        TitleDto title2 = new TitleDto();
        title2.setId(2L);
        title2.setTitle("Title 2");
        titles.add(title1);
        titles.add(title2);

        when(titleService.getTitlesByUser(userId)).thenReturn(titles);

        // when
        ResponseEntity<List<TitleDto>> responseEntity = titleController.getTitlesByUser(userId);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(titles, responseEntity.getBody());
    }
}
