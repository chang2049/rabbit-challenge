package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnagramServiceRecursionImplTest {
    private String phrase ="poultry outwits ants";

    @Autowired
    AnagramServiceRecursionImpl anagramServiceRecursion;

    @Autowired
    SentenceService sentenceService;

    @Test
    void findAllValidAnagrams() throws IOException {
        List<Sentence> allValidAnagrams1 = anagramServiceRecursion.findAllValidAnagrams("anagramwordlist","some random text");
        assertTrue(allValidAnagrams1.isEmpty());
        List<Sentence> allValidAnagrams2 = anagramServiceRecursion.findAllValidAnagrams("anagramwordlist",phrase);
        assertTrue(allValidAnagrams2.stream().anyMatch(x->x.getWords().contains("printout")));
        List<Sentence> allValidAnagrams3 = anagramServiceRecursion.findAllValidAnagrams("anagramwordlist","trust pilot");
        assertTrue(allValidAnagrams1.stream().anyMatch(x->x.getWords().contains("printout")));
    }
}