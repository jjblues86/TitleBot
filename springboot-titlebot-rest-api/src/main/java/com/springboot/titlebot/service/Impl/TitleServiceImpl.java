package com.springboot.titlebot.service.Impl;

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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/** The implementation of the {@link TitleService} interface, providing methods to manage Title entities. */

@Service
@AllArgsConstructor
public class TitleServiceImpl implements TitleService {

    /** The {@link TitleRepository} instance for database operations. */
    private final TitleRepository titleRepository;
    /** The {@link ModelMapper} instance for mapping DTOs to entities. */
    private ModelMapper mapper;
    /** The {@link WebClient} instance for HTTP requests. */
    private WebClient webClient;

    /**
     * Saves a title to the database.
     * @param titleDto the title to be saved.
     * @return the saved title.
     */
    @SneakyThrows
    @Override
    public TitleDto saveTitle(TitleDto titleDto) {
        //convert DTO to entity
        final Title title = getTitle(titleDto);

        // convert entity to DTO
        final Title saveTitle = titleRepository.save(title);

        return getTitleDto(saveTitle);
    }

    @SneakyThrows
    @Override
    public TitleDto getTitle(String titleUrl) {
        // get the document from the url and parse it to a document object for further operations
        Document document = Jsoup.connect(titleUrl).get();
        final String urlTitle = document.title();
        final String faviconUrl = getHref(document, titleUrl);

        // set the favicon and title to the DTO object
        TitleDto titleDto = webClient.get()
                .uri(faviconUrl)
                .retrieve().
                bodyToMono(TitleDto.class).block();

        titleDto.setFaviconUrl(faviconUrl);
        titleDto.setUrl(titleUrl);
        titleDto.setTitle(urlTitle);

        // check if the title already exists in the database
        final Title titleExists = titleRepository.findByTitle(urlTitle);
        if (titleExists != null) {
            return getTitleDto(titleExists);
        }
        return titleDto;
    }

    /** Gets the favicon url from the document.
     * @param document the document to be parsed.
     * @param url the url of the document.
     * @return the favicon url.
     */
    private static String getHref(final Document document, final String url) {
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
     * Gets all titles from the database.
     * @return a list of all titles.
     */
    @Override
    public List<TitleDto> getAllTitles() {
        return null;
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
}
