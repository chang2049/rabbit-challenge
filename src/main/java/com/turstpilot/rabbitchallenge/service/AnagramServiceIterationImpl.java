package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements AnagramService by iteration
 */
@Service
public class AnagramServiceIterationImpl implements AnagramService{
    @Autowired
    SentenceService sentenceService;

    @Autowired
    WordListService wordListService;

    /**
     * NB: currently it only contains four loops, which means maximum four-word-formed anagram can be found
     * @param phrase The reference where all anagram can be found
     * @return
     * @throws IOException
     */
    @Override
    public List<Sentence> findAllValidAnagrams(String phrase) throws IOException {
        return findAllValidAnagrams("wordlist",phrase);
    }

    public List<Sentence> findAllValidAnagrams(String wordListFileName, String phrase) throws IOException {
        List<String> wordlist = wordListService.loadWordList(wordListFileName);
        wordlist = wordListService.filterWordList(wordlist,phrase);
        Sentence originalSentence = sentenceService.createNewSentenceWithTarget(phrase);
        List<Sentence> output = new LinkedList<>();
        for(String word1: wordlist){
            Sentence sentenceLevel1 = originalSentence.addWord(word1);
            if(sentenceLevel1.isCompleted()){
                output.add(sentenceLevel1);
                continue;
            }
            if(!sentenceLevel1.isValid()) continue;
            for(String word2: wordlist){
                Sentence sentenceLevel2 = sentenceLevel1.addWord(word2);
                if(sentenceLevel2.isCompleted()){
                    output.add(sentenceLevel2);
                    continue;
                }
                if(!sentenceLevel2.isValid()) continue;
                for(String word3: wordlist) {
                    Sentence sentenceLevel3 = sentenceLevel2.addWord(word3);
                    if (sentenceLevel3.isCompleted()) {
                        output.add(sentenceLevel3);
                        continue;
                    }
                    if (!sentenceLevel3.isValid()) continue;
                    for (String word4 : wordlist) {
                        Sentence sentenceLevel4 = sentenceLevel3.addWord(word4);
                        if (sentenceLevel4.isCompleted()) {
                            output.add(sentenceLevel4);
                            continue;
                        }
                        if (!sentenceLevel4.isValid()) continue;
                    }
                }
            }
        }
        return output;
    }

}
