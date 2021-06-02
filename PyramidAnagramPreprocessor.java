package me.djsch.puzzlePyramid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// PyramidAnagramPreprocessor does some inital work to search for possible
// anagrams in the word list. This pre-processing is necessary because searching
// for anagrams is rather time consuming, and checking them all when we solve the
// actual pyramid becomes infeasible. Hence, we do this work beforehand and simply
// refer to these files when solving anagram-related clues.
//
// This type pre-processing could also be applied to other clues in this project;
// probably beginning with NonoverlappingSolver, which performs a recursive search
// to find the maximum coverage of a word by a set of substrings. So far, this
// optimization has not proved necessary, but if it did we could create similar
// preprocessing classes.
public class PyramidAnagramPreprocessor {
    private Set<String> dictionary;
    public PyramidAnagramPreprocessor() {
        dictionary = PyramidUtils.getWordsDictionary();
    }

    // Preprocess all anagrams in the words list with 0, 1, and 2 extra letters.
    public void preprocess() {
        for (int i = 0; i < 3; i++) {
            findAndWriteAnagrams(i);
        }
    }

    // For each word in the dictionary, we check to see if there is a word
    // in the word list that could be formed by anagramming the initial word
    // with N extra letters. If such an anagram is possible, we write that word
    // into a file for future reference.
    public void findAndWriteAnagrams(int numExtraLetters) {
        // Create a HashMap for the given word
        ArrayList<String> validWords = new ArrayList<>();

        for (String word : dictionary) {
            HashMap<Character, Integer> wordLetters = new HashMap<>();
            for (Character c : word.toCharArray()) {
                if (wordLetters.keySet().contains(c))
                    wordLetters.put(c, wordLetters.get(c) + 1);
                else
                    wordLetters.put(c, 1);

            }

            for (String candidateWord : dictionary) {
                // Any word will obviously anagram to itself, but that's not what
                // we're looking for, so skip this case.
                if (word.equals(candidateWord))
                    continue;

                HashMap<Character, Integer> candidateLetters = new HashMap<>();
                for (Character c : candidateWord.toCharArray()) {
                    if (candidateLetters.keySet().contains(c))
                        candidateLetters.put(c, candidateLetters.get(c) + 1);
                    else
                        candidateLetters.put(c, 1);
                }
                if (isValidMapComparison(wordLetters, candidateLetters, numExtraLetters)) {
                    validWords.add(word);
                    break;
                }
            }
        }

        PyramidFileReaderWriter.writeWordsFile(validWords,
                PyramidUtils.fileBasePath + "/anagram" + numExtraLetters + ".txt");
    }

    // Check to see if the given HashMap for 'word' plus a given number of extra characters
    // is equivalent to the given HashMap for 'candidateWord'.
    public boolean isValidMapComparison(HashMap<Character, Integer> word, HashMap<Character, Integer> candidate, int numLetters) {
        for (Character c : word.keySet()) {
            if (!candidate.keySet().contains(c))
                return false;
            if (word.get(c) > candidate.get(c))
                return false;
            candidate.put(c, candidate.get(c) - word.get(c));
        }

        int numLeftoverLetters = 0;
        for (Character c : candidate.keySet()) {
            numLeftoverLetters += candidate.get(c);
        }
        if (numLeftoverLetters == numLetters)
            return true;

        return false;
    }
}
