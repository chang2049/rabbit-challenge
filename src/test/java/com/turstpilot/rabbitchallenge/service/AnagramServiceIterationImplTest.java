package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnagramServiceIterationImplTest {
    private String phrase ="poultry outwits ants";

    @Autowired
    AnagramServiceIterationImpl anagramServiceIteration;

    @Test
    void findAllValidAnagrams() throws IOException {
        List<Sentence> allValidAnagrams1 = anagramServiceIteration.findAllValidAnagrams("anagramwordlist","some random text");
        assertTrue(allValidAnagrams1.isEmpty());
        List<Sentence> allValidAnagrams2 = anagramServiceIteration.findAllValidAnagrams("anagramwordlist",phrase);
        assertEquals(36,allValidAnagrams2.size());
    }
}