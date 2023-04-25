package com.springboot.titlebot.controller;

import com.springboot.titlebot.dto.TitleDto;
import com.springboot.titlebot.entity.Title;
import com.springboot.titlebot.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/title")
public class TitleController {

    /**
     * TitleService
     */
    private TitleService titleService;

    /**
     * Save title.
     * @param titleDto the title dto.
     * @return saved title.
     */
    @PostMapping
    public ResponseEntity<TitleDto> saveTitle(@RequestBody final TitleDto titleDto) {
        TitleDto savedTitle = titleService.saveTitle(titleDto);

        return new ResponseEntity<>(savedTitle, HttpStatus.CREATED);
    }

    /** Get title.
     * @param url the url.
     * @return title.
     */
    @GetMapping
    public ResponseEntity<TitleDto> getTitle(@RequestParam final String url) {
        TitleDto title = titleService.getTitle(url);

        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    /** Get all titles. */
    @GetMapping("/api/titles")
    public ResponseEntity<List<TitleDto>> getAllTitles() {
        List<TitleDto> titles = titleService.getAllTitles();

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }
}
