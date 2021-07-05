package com.turstpilot.rabbitchallenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WordListServiceTest {
    @Autowired
    WordListService wordListService;

    @Test
    void loadWordList() throws IOException {
        List<String> smallwordlist = wordListService.loadWordList("smallwordlist");
        assertEquals(6,smallwordlist.size());
    }

    @Test
    void filterWordList() throws IOException {
        List<String> smallwordlist = wordListService.loadWordList("smallwordlist");
        assertEquals(6,smallwordlist.size());
        List<String> filteredWordList = wordListService.filterWordList(smallwordlist, "randtestomg");
        assertEquals(3,filteredWordList.size());
    }
}