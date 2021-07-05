package com.turstpilot.rabbitchallenge.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SentenceTest {

    @Test
    void addWord() {
        Sentence sentence = new Sentence();
        Sentence sentence1 = sentence.addWord("firstword");
        assert sentence.getWords().size()<sentence1.getWords().size();
    }

    @Test
    void isValid() {
        Sentence hello = new Sentence().setTargetCharacterCounter("hello");
        Sentence otherword = hello.addWord("otherword");
        Sentence ell = hello.addWord("ell");
        assertFalse(otherword.isValid());
        assertTrue(ell.isValid());
    }

    @Test
    void isCompleted() {
        Sentence hello = new Sentence().setTargetCharacterCounter("hello");
        Sentence otherword = hello.addWord("otherword");
        Sentence llohe = hello.addWord("llohe");
        assertFalse(otherword.isValid());
        assertTrue(llohe.isValid());
    }
}