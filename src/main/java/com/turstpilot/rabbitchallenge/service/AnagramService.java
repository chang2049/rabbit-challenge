package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;

import java.io.IOException;
import java.util.List;

/**
 * This interface has one method to retrieve all anagrams
 */
interface AnagramService {
    /**
     * Valid anagram contains two creteria
     * 1. Is an anagram of provided phrase
     * 2. The word in anagram can be found in provided word list
     * @param phrase The reference where all anagram can be found
     * @return All valid anagrams
     * @throws IOException
     */
    public List<Sentence> findAllValidAnagrams(String phrase) throws IOException;
    public List<Sentence> findAllValidAnagrams(String wordListFileName, String phrase) throws IOException;
}
