package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This service is able to load wordlist and filter out all invalid words
 */
@Service
public class WordListService {
    @Autowired
    SentenceService sentenceService;

    public List<String> loadWordList(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(WordListService.class.getClassLoader().getResource(fileName).getPath()), StandardCharsets.UTF_8);
    }

    /**
     * Filter out all invalid word in wordlist
     * @param wordList orginal wordlist
     * @param phrase
     * @return filtered wordlist
     */
    public List<String> filterWordList(List<String> wordList,String phrase){
        int[] charCount = getCharCount(phrase);
        Set<Character> charSet = phrase.toLowerCase().chars()
                .filter(x->x!=' ').mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
        return wordList.stream().parallel()
                .filter(word->isWordValid(word,charCount,charSet))
                .collect(Collectors.toList());
    }

    /**
     * A valid word is a subset of target sentence
     * @param word word to be validated
     * @param charCount char counts of target sentence
     * @param charSet char set of target sentence
     * @return if this word is valid
     */
    private static boolean isWordValid(String word, int[] charCount,Set<Character> charSet){
        for(char c: word.toLowerCase().toCharArray()){
            if(!charSet.contains(c)){
                return false;
            }
        }
        int[] charCountWord = getCharCount(word);
        for(char c: charSet){
            if(charCountWord[c-'a']>charCount[c-'a']){
                return false;
            }
        }
        return true;
    }

    private static int[] getCharCount(String word){
        int[] charCount = new int[26];
        word.toLowerCase().chars().filter(c->c-'a'>=0&&c-'a'<26).forEach(c->charCount[c-'a']+=1);
        return charCount;
    }
}
