package com.springboot.titlebot.controller;

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
        TitleDto savedTitle = titleService.saveTitleUrl(titleDto.getUrl());

        return new ResponseEntity<>(savedTitle, HttpStatus.CREATED);
    }


    /** Get all titles. */
    @GetMapping("/titles")
    public ResponseEntity<List<TitleDto>> getAllTitles() {
        List<TitleDto> titles = titleService.getAllTitles();

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }
}
