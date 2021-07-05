package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SentenceServiceTest {
    @Autowired
    SentenceService sentenceService;

    @Test
    void createNewSentenceWithTarget() {
        Sentence hello = sentenceService.createNewSentenceWithTarget("aaa");
        assertTrue(hello.getTagetCharacterCounter()[0]==3);
    }

    @Test
    void permuteWords() {
        Sentence sentence = new Sentence().addWord("a").addWord("b").addWord("c");
        List<List<String>> lists = sentenceService.permuteWords(sentence);
        assertEquals(6, lists.size());
    }
}