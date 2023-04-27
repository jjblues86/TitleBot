package com.springboot.titlebot.controller;

import com.springboot.titlebot.dto.HistoryUrlDto;
import com.springboot.titlebot.dto.TitleDto;
import com.springboot.titlebot.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/title")
@CrossOrigin(origins = "http://localhost:3000")
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
        TitleDto savedTitle = titleService.saveTitleUrl(titleDto.getUrl(), titleDto.getUserId());

        return new ResponseEntity<>(savedTitle, HttpStatus.CREATED);
    }

    /** Get all titles. */
    @GetMapping("/titles")
    public ResponseEntity<List<TitleDto>> getAllTitles() {
        List<TitleDto> titles = titleService.getAllTitles();

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    /** Get all urls. */
    @GetMapping("/urls")
    public ResponseEntity<List<HistoryUrlDto>> getAllUrls() {

        List<HistoryUrlDto> urls = titleService.getUrlHistory();

        return new ResponseEntity<>(urls, HttpStatus.OK);
    }

    /** Get titles by user. */
    @GetMapping("/{userId}")
    public ResponseEntity<List<TitleDto>> getTitlesByUser(@PathVariable("userId") String userId) {
        List<TitleDto> titles = titleService.getTitlesByUser(userId);

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    /** Delete title by id. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTitleById(@PathVariable("id") Long userId) {
        titleService.deleteTitleById(userId);

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
