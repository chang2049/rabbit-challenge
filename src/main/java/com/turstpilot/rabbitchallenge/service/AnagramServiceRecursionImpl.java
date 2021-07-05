package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements AnagramService by recursion
 */
@Service
public class AnagramServiceRecursionImpl implements AnagramService{
    List<Sentence> anagrams = new LinkedList<>();
    List<String> wordlist;
    @Autowired
    SentenceService sentenceService;

    @Autowired
    WordListService wordListService;

    /**
     * NB: This method is able to find (n+1)-word-form anagrams
     * @param phrase The reference where all anagram can be found
     * @return
     * @throws IOException
     */
    @Override
    public List<Sentence> findAllValidAnagrams(String phrase) throws IOException {
        return findAllValidAnagrams("wordlist",phrase);
    }

    public List<Sentence> findAllValidAnagrams(String wordListFileName,String phrase) throws IOException {
        List<String> wordlist = wordListService.loadWordList(wordListFileName);
        this.wordlist = wordListService.filterWordList(wordlist,phrase);
        findAllValidAnagrams(0,sentenceService.createNewSentenceWithTarget(phrase),phrase.split("\\s+").length+1);
        return anagrams;
    }

    private void findAllValidAnagrams(int currentIdx, Sentence sentence, int leftWordNum){
//        System.out.println("running");
        if(currentIdx>=wordlist.size()||leftWordNum<=0) return;
        String currentWord = wordlist.get(currentIdx);
        Sentence newSentence = sentence.addWord(currentWord);
        if(newSentence.isCompleted()){
            anagrams.add(newSentence);
        }
        if(newSentence.isValid() && !newSentence.isCompleted()){
            findAllValidAnagrams(currentIdx+1,newSentence,leftWordNum-1);
        }
        findAllValidAnagrams(currentIdx+1,sentence,leftWordNum);
    }
}
