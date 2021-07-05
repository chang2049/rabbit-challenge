package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class enables to decode MD5 hashed text based on a hint text and a wordlist
 */
@Service
public class Md5Service {
    Logger logger = Logger.getLogger(Md5Service.class);
    Map<String,List<Sentence>> anagramsMap = new HashMap<>();

    @Qualifier("anagramServiceIterationImpl")
    @Autowired
    AnagramService anagramService;

    @Qualifier("anagramServiceRecursionImpl")
    @Autowired
    AnagramService anagramService2;

    @Autowired
    SentenceService sentenceService;

    /**
     * This method calculates all anagrams of hinttext and composed of words in wordlist
     * Then MD5 hash them and check if one of them is equal to hashedText
     * @param hintText used to find its anagrams
     * @param hashedText Target MD5 hashed text
     * @return a set of string equal to hashedText after being MD5 hashed
     * @throws IOException
     */
    public Set<String> decodeMD5Hashed(String hintText, String hashedText) throws IOException {
        return decodeMD5Hashed(hintText,hashedText,"wordlist");
    }
    public Set<String> decodeMD5Hashed(String hintText, String hashedText, String wordListFileName) throws IOException {
        logger.info("Started to decode: "+hashedText);
        List<Sentence> allValidAnagrams = anagramsMap.get(hintText);
        if (allValidAnagrams==null){
            logger.info("Anagram doesn't exist, started to find all anagrams");
            allValidAnagrams = anagramService2.findAllValidAnagrams(wordListFileName,hintText);
            anagramsMap.put(hintText,allValidAnagrams);
        }else{
            logger.info("Anagram exists and will be reused");
        }
        Set<String> anagrams = allValidAnagrams.stream().parallel()
                .flatMap(x -> sentenceService.permuteWords(x).stream().parallel().map(words -> String.join(" ", words)))
                .collect(Collectors.toSet());
        logger.info("Finished finding all anagrams, MD5 hasg validation started");
        Set<String> output = validateDecoding(anagrams, hashedText);
        logger.info("Phrase of "+hashedText+": "+output);
        return output;
    }

    private Set<String> validateDecoding(Set<String> candidates, String target){
        return candidates.stream()
                .parallel()
                .filter(s -> DigestUtils.md5Hex(s).toLowerCase().equals(target))
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("cannot find it"));;
    }

}
