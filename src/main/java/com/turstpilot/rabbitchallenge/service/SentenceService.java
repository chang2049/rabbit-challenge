package com.turstpilot.rabbitchallenge.service;

import com.turstpilot.rabbitchallenge.model.Sentence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This service class is able to create a Sentence instance and calculate all its permutations
 */
@Service
public class SentenceService {
    /**
     * Create a Sentence instance with targetCharacterCounter
     * @param sentence is the target phrase
     * @return sentence
     */
    public Sentence createNewSentenceWithTarget(String sentence){
        return new Sentence()
                .setTargetCharacterCounter(sentence);
    }

    /**
     * Find all permutations of sentence( a list of words)
     * @param sentence
     * @return a list of lists of strings
     */
    public List<List<String>> permuteWords(Sentence sentence){
        return permute(sentence.getWords());
    }

    public static <T> List<List<T>> permute(Collection<T> input) {
        List<List<T>> output = new ArrayList<List<T>>();
        if (input.isEmpty()) {
            output.add(new ArrayList<T>());
            return output;
        }
        List<T> list = new ArrayList<T>(input);
        T head = list.get(0);
        List<T> rest = list.subList(1, list.size());
        for (List<T> permutations : permute(rest)) {
            List<List<T>> subLists = new ArrayList<List<T>>();
            for (int i = 0; i <= permutations.size(); i++) {
                List<T> subList = new ArrayList<T>();
                subList.addAll(permutations);
                subList.add(i, head);
                subLists.add(subList);
            }
            output.addAll(subLists);
        }
        return output;
    }
}
