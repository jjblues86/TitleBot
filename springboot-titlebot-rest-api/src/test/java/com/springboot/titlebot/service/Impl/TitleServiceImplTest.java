package com.springboot.titlebot.service.Impl;

import com.springboot.titlebot.dto.TitleDto;
import com.springboot.titlebot.entity.Title;
import com.springboot.titlebot.repository.TitleRepository;
import com.springboot.titlebot.service.TitleService;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.stubbing.OngoingStubbing.*;

class TitleServiceImplTest {

    @Mock
    private TitleRepository titleRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private WebClient webClient;

    @Mock
    private Document document;

    private TitleService titleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        titleService = new TitleServiceImpl(titleRepository, modelMapper);
    }

//    @Test
//    void testSaveTitleUrl() throws Exception {
//        String url = "http://example.com";
//        String title = "Example Domain";
//        String faviconUrl = "http://example.com/favicon.ico";
//        TitleDto titleDto = new TitleDto();
//        titleDto.setUrl(url);
//        titleDto.setTitle(title);
//        titleDto.setFaviconUrl(faviconUrl);
//        Title titleEntity = new Title();
//        titleEntity.setUrl(url);
//        titleEntity.setTitle(title);
//        titleEntity.setFaviconUrl(faviconUrl);
//
//        when(titleRepository.save(titleEntity)).thenReturn(titleEntity);
//        when(modelMapper.map(titleDto, Title.class)).thenReturn(titleEntity);
//
//        TitleDto actualTitle = titleService.saveTitleUrl(url);
//        System.out.println("What should be printed: " + actualTitle);
//
//
//        assertEquals(titleDto, actualTitle);
//        assertEquals(title, actualTitle.getTitle());
//        assertEquals(url, actualTitle.getUrl());
//        assertEquals(faviconUrl, actualTitle.getFaviconUrl());
//        assertEquals(titleEntity, actualTitle);
//    }

    @Test
    void testGetAllTitles() {
        Title title1 = new Title();
        title1.setUrl("http://example.com");
        title1.setTitle("Example Domain");
        title1.setFaviconUrl("http://example.com/favicon.ico");

        Title title2 = new Title();
        title2.setUrl("http://example.org");
        title2.setTitle("Example Website");
        title2.setFaviconUrl("http://example.org/favicon.ico");

        List<Title> titles = Arrays.asList(title1, title2);
        when(titleRepository.findAll()).thenReturn(titles);

        TitleDto titleDto1 = new TitleDto();
        titleDto1.setUrl("http://example.com");
        titleDto1.setTitle("Example Domain");
        titleDto1.setFaviconUrl("http://example.com/favicon.ico");

        TitleDto titleDto2 = new TitleDto();
        titleDto2.setUrl("http://example.org");
        titleDto2.setTitle("Example Website");
        titleDto2.setFaviconUrl("http://example.org/favicon.ico");

        List<TitleDto> expectedTitles = Arrays.asList(titleDto1, titleDto2);
        when(modelMapper.map(title1, TitleDto.class)).thenReturn(titleDto1);
        when(modelMapper.map(title2, TitleDto.class)).thenReturn(titleDto2);
        List<TitleDto> actualTitles = titleService.getAllTitles();
        assertEquals(expectedTitles, actualTitles);
    }

//    @Test
//    void testGetTitleByUrl() {
//        Title title = new Title();
//        title.setUrl("http://example.com");
//        title.setTitle("Example Domain");
//        title.setFaviconUrl("http://example.com/favicon.ico");
//
//        TitleDto titleDto = new TitleDto();
//        titleDto.setUrl("http://example.com");
//        titleDto.setTitle("Example Domain");
//        titleDto.setFaviconUrl("http://example.com/favicon.ico");
//
//        when(titleRepository.findByUrl(anyString())).thenReturn(title);
//        when(modelMapper.map(title, TitleDto.class)).thenReturn(titleDto);
//        TitleDto actualTitle = titleService.getTitleByUrl("http://example.com");
//        assertEquals(titleDto, actualTitle);
//    }
//
//    @Test
//    void testGetTitleByTitle() {
//
//        Title title = new Title();
//        title.setUrl("http://example.com");
//        title.setTitle("Example Domain");
//        title.setFaviconUrl("http://example.com/favicon.ico");
//
//        TitleDto titleDto = new TitleDto();
//        titleDto.setUrl("http://example.com");
//        titleDto.setTitle("Example Domain");
//        titleDto.setFaviconUrl("http://example.com/favicon.ico");
//
//        when(titleRepository.findByTitle(anyString())).thenReturn(title);
//        when(modelMapper.map(title, TitleDto.class)).thenReturn(titleDto);
//        TitleDto actualTitle = titleService.getTitleByTitle("Example Domain");
//        assertEquals(titleDto, actualTitle);
//    }
//
//    @Test
//    void testDeleteTitleByUrl() {
//
//        Title title = new Title();
//        title.setUrl("http://example.com");
//        title.setTitle("Example Domain");
//        title.setFaviconUrl("http://example.com/favicon.ico");
//
//        TitleDto titleDto = new TitleDto();
//        titleDto.setUrl("http://example.com");
//        titleDto.setTitle("Example Domain");
//        titleDto.setFaviconUrl("http://example.com/favicon.ico");
//
//        when(titleRepository.findByUrl(anyString())).thenReturn(title);
//        when(modelMapper.map(title, TitleDto.class)).thenReturn(titleDto);
//        TitleDto actualTitle = titleService.deleteTitleByUrl("http://example.com");
//        assertEquals(titleDto, actualTitle);
//    }
//
//    @Test
//    void testDeleteTitleByTitle() {
//        Title title = new Title();
//        title.setUrl("http://example.com");
//        title.setTitle("Example Domain");
//        title.setFaviconUrl("http://example.com/favicon.ico");
//
//        TitleDto titleDto = new TitleDto();
//        titleDto.setUrl("http://example.com");
//        titleDto.setTitle("Example Domain");
//        titleDto.setFaviconUrl("http://example.com/favicon.ico");
//
//        when(titleRepository.findByTitle(anyString())).thenReturn(title);
//        when(modelMapper.map(title, TitleDto.class)).thenReturn(titleDto);
//        TitleDto actualTitle = titleService.deleteTitleByTitle("Example Domain");
//        assertEquals(titleDto, actualTitle);
//    }
//
//    @Test
//    void testDeleteAllTitles() {
//        Title title = new Title();
//        title.setUrl("http://example.com");
//        title.setTitle("Example Domain");
//        title.setFaviconUrl("http://example.com/favicon.ico");
//
//        TitleDto titleDto = new TitleDto();
//        titleDto.setUrl("http://example.com");
//        titleDto.setTitle("Example Domain");
//        titleDto.setFaviconUrl("http://example.com/favicon.ico");
//
//        when(titleRepository.findAll()).thenReturn(Arrays.asList(title));
//        when(modelMapper.map(title, TitleDto.class)).thenReturn(titleDto);
//        List<TitleDto> actualTitles = titleService.deleteAllTitles();
//        assertEquals(Arrays.asList(titleDto), actualTitles);
//    }

}
