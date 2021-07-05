# Rabbit Challenge

## Problem

We have a message for you. But we hid it.
Unless you know the secret phrase, it will remain hidden.

Can you write the algorithm to find it?

Here is a couple of important hints to help you out:

- An anagram of the phrase is: "poultry outwits ants"
- There are three levels of difficulty to try your skills with
- The MD5 hash of the easiest secret phrase is "e4820b45d2277f3844eac66c903e84be"
- The MD5 hash of the more difficult secret phrase is "23170acc097c24edb98fc5488ab033fe"
- The MD5 hash of the hard secret phrase is "665e5bcb0c20062fe8abaaf4628bb154"



## Answers

| MD5 Hashed Text                  | Phrase                |
| -------------------------------- | --------------------- |
| e4820b45d2277f3844eac66c903e84be | printout stout yawls  |
| 23170acc097c24edb98fc5488ab033fe | ty outlaws printouts  |
| 665e5bcb0c20062fe8abaaf4628bb154 | wu lisp not statutory |



## Solution

The most intuitive to solve this problem is to find all the anagrams of the hint text. However, it's gonna be N! possibilities where N is the length of hint text, which makes it barely feasible to implement. Therefore, My first priority is to reduce the amount of possible anagrams.

1. Loop through the wordlist and filter out all invalid words ( a valid word should be a subset of the hint text)
2. Combine words from wordlist to see if it's an anagram of the hint text, I've implemented in both iteration and recursion
    - Recusion: recursively add word to sentence, and check if any character count of the sentence exceed that of hint text (stops) or is an anagram of hint text.
    - Iteration: A fix number of loops are implemented, in this case 4. That is to say it can only find all anagrams containing maximum 4 words.
3. Save the anagrams so that it can be reused
4. Loop through and MD5 hash the anagrams, and check if it euqals to target hashed code



## How to run the code

This a maven project using springboot framework.

Requirements:

- Maven 3.8.1
- Java 11

Three ways of running it

1. Please run RabbitChallengeApplication directly in IDE

2. ```bash
   # Execute the jar file directly
   java -jar rabbit-challenge-0.0.1-SNAPSHOT.jar 
   #Please see the jar file in project root
   ```

3. ```bash
   # Package via maven
   mvn package
   # Execute the jar file
   ```


