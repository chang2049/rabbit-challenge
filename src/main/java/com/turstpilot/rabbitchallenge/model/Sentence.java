package com.turstpilot.rabbitchallenge.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class describes a phrase contains a list of words(string)
 * The targetCharacterCounter describes how many letters are still missing
 * Each time a new word is added, a new sentence instance is initiated
 */
public class Sentence {
    //Instance fields and their setters and getters--------------------------------------------------------------------

    List<String> words = new LinkedList<>();
    int[] targetCharacterCounter = new int[26];

    /**
     * Set words in a sentence
     * @param words A list of string
     * @return This sentence instance
     */
    public Sentence setWords(List<String> words) {
        for(String word: words){
            this.words.add(word);
        }
        return this;
    }

    /**
     * Get words parameter in sentence
     * @return A list of string
     */
    public List<String> getWords() {
        return words;
    }

    public int[] getTagetCharacterCounter() {
        return targetCharacterCounter;
    }

    public Sentence setTargetCharacterCounter(String phrase){
        phrase.toLowerCase().chars().filter(c->c-'a'>=0&&c-'a'<26)
                .forEach(c->this.targetCharacterCounter[c-'a']+=1);
        return this;
    }

    public Sentence setTargetCharacterCounter(int[] characterCounter){
        this.targetCharacterCounter = characterCounter;
        return this;
    }

    //instance methods field--------------------------------------------------------------------------------------------

    /**
     * This method is to create a new instance of Sentence and add a new word
     * @param word is the word needed to be added
     * @return new sentence where word is added
     */
    public Sentence addWord(String word){
        Sentence newSent = new Sentence();
        newSent.setWords(this.words);
        newSent.words.add(word);
        newSent.setTargetCharacterCounter(this.targetCharacterCounter.clone());
        int[] targetCharacterCounter = newSent.targetCharacterCounter;
        word.chars().forEach(i->targetCharacterCounter[i-'a']-=1);
        return newSent;
    }

    /**
     * Check if the number of any letter is minus, which means current sentence isn't an anagram
     * @return boolean illustrating whether current sentence has an potential to be an anagram
     */
    public boolean isValid(){
        for(int i = 0; i<targetCharacterCounter.length; i++){
            if(targetCharacterCounter[i]<0){
                return false;
            }
        }
        return true;
    }

    /**
     * Check if current sentence is an anagram, which means all targetCharaterCounter is 0
     * @return boolean illustrating whether current sentence is an anagram
     */
    public boolean isCompleted(){
        for(int i = 0; i<targetCharacterCounter.length; i++){
            if(targetCharacterCounter[i]!=0){
                return false;
            }
        }
        return true;
    }

}
