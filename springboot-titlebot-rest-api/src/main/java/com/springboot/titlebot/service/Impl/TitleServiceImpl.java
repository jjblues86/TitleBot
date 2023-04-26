package com.springboot.titlebot.service.Impl;

import com.springboot.titlebot.dto.HistoryUrlDto;
import com.springboot.titlebot.dto.TitleDto;
import com.springboot.titlebot.entity.Title;
import com.springboot.titlebot.repository.TitleRepository;
import com.springboot.titlebot.service.TitleService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** The implementation of the {@link TitleService} interface, providing methods to manage Title entities. */

@Service
@AllArgsConstructor
public class TitleServiceImpl implements TitleService {

    /** The {@link TitleRepository} instance for database operations. */
    private final TitleRepository titleRepository;
    /** The {@link ModelMapper} instance for mapping DTOs to entities. */
    private ModelMapper mapper;

    /**
     * Gets a title from the database.
     *
     * @param titleUrl the title to be retrieved.
     * @param userId
     * @return the retrieved title.
     * @throws if the title does not exist.
     */
    @SneakyThrows
    @Override
    public TitleDto saveTitleUrl(String titleUrl, String userId) {
        // get the document from the url and parse it to a document object for further operations
        Document document = Jsoup.connect(titleUrl).get();
        final String urlTitle = document.title();
        final String faviconUrl = getFaviconUrl(document, titleUrl);

        // set the favicon and title to the DTO object
        TitleDto titleDto = new TitleDto();
        titleDto.setFaviconUrl(faviconUrl);
        titleDto.setUrl(titleUrl);
        titleDto.setTitle(urlTitle);
        titleDto.setUserId(userId);

        // check if the favicon url is valid
        if (!titleDto.getFaviconUrl().startsWith("http")) {
            titleDto.setFaviconUrl(titleUrl + "/favicon.ico");
        }

        // check if the title already exists in the database
        final Title titleExists = titleRepository.findByTitle(urlTitle);
        if (titleExists != null) {
            return getTitleDto(titleExists);
        }

        final Title savedTitle = titleRepository.save(getTitle(titleDto));

        return getTitleDto(savedTitle);
    }

    /**
     * Gets all titles from the database.
     * @return a list of all titles.
     */
    @Override
    public List<TitleDto> getAllTitles() {
        return titleRepository.findAll().stream().map(this::getTitleDto).toList();
    }

    /**
     * Gets all urls from the database.
     *
     * @return a list of all urls.
     */
    @Override
    public List<HistoryUrlDto> getUrlHistory() {

        List<Title> titles = titleRepository.findAll();

        return titles.stream().map(this::getHistoryUrlDto).collect(Collectors.toList());
    }

    /**
     * Gets all titles from the database.
     * @param userId the user id from the request.
     * @return a list of all titles. */
    @Override
    public List<TitleDto> getTitlesByUser(String userId) {
        List<Title> titles = titleRepository.findByUserId(userId);
        List<TitleDto> titleDtos = new ArrayList<>();
        for (Title title : titles) {
            titleDtos.add(getTitleDto(title));
        }
        return titleDtos;
    }

    /** Gets the favicon url from the document.
     * @param document the document to be parsed.
     * @param url the url of the document.
     * @return the favicon url.
     */
    private static String getFaviconUrl(final Document document, final String url) {
        String faviconUrl = "";
        Element link = document.select("link[href~=.*\\.(ico|png)]").first();
        if (link != null) {
            faviconUrl = link.attr("href");
        } else {
            faviconUrl = url + "/favicon.ico";
        }
        return faviconUrl;
    }

    /**
     * Gets a title from the database.
     * @param title the title to be retrieved.
     * @return the retrieved title.
     */
    private TitleDto getTitleDto(Title title) {
        return mapper.map(title, TitleDto.class);
    }

    /**
     * Gets a title from the database.
     * @param titleDto the title to be retrieved.
     * @return the retrieved title.
     */
    private Title getTitle(TitleDto titleDto) {
        return mapper.map(titleDto, Title.class);
    }

    private HistoryUrlDto getHistoryUrlDto(Title title) {
        return mapper.map(title, HistoryUrlDto.class);
    }
}
