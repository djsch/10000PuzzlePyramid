package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidUtils;

import java.util.Set;

// Solves clues of the form:
//
// Can be combined with {one, two} additional letters to produce an anagram
// of something in the word list: {YES, NO}
//
// Has at least one anagram that is also in the word list: {YES, NO}
//
// Leverages work done by PyramidAnagramWriter to precompute words which
// fit the above criteria, and simply checks for their presence in the
// associated file to determine if the clue is valid or not.
public class AnagramSolver extends ClueSolver {
    private Set<String> dictionary;
    private boolean isAnagram;

    public AnagramSolver(String clue) {
        String[] splitString = clue.split(" ");
        if (clue.startsWith("Has at least")) {
            dictionary = PyramidUtils.getAnagram0Dictionary();
            if (splitString[12].equals("YES"))
                isAnagram = true;
            else
                isAnagram = false;
        } else {
            if (splitString[4].equals("one"))
                dictionary = PyramidUtils.getAnagram1Dictionary();
            else
                dictionary = PyramidUtils.getAnagram2Dictionary();

            if (splitString[17].equals("YES"))
                isAnagram = true;
            else
                isAnagram = false;
        }
    }

    public boolean isValidWord(String word) {
        if (dictionary.contains(word))
            return isAnagram;
        return !isAnagram;
    }
}
