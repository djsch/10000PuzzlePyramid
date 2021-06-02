package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidConstants;

import java.util.HashSet;
import java.util.Set;

// Solves clues of the form:
//
// Distinct {letters, consonants, vowels}: <Integer>
//
// Distinct {letters, consonants, vowels}: between <Integer> and <Integer> (inclusive)
public class DistinctLettersSolver extends ClueSolver {

    private boolean isVowels = false;
    private boolean isConsonants = false;
    private boolean isLetters = false;

    private int exactCount = -1;
    private int lowerBound = -1;
    private int upperBound = -1;

    public DistinctLettersSolver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[1].equals("vowels:"))
            isVowels = true;
        else if (splitString[1].equals("consonants:"))
            isConsonants = true;
        else if (splitString[1].equals("letters:"))
            isLetters = true;

        if (splitString[2].equals("between")) {
            lowerBound = Integer.parseInt(splitString[3]);
            upperBound = Integer.parseInt(splitString[5]);
        } else {
            exactCount = Integer.parseInt(splitString[2]);
        }
    }

    public boolean isValidWord(String word) {
        Set<Character> letters = new HashSet<>();
        for (Character c : word.toCharArray()) {
            letters.add(c);
        }

        int count = 0;
        for (Character c : letters) {
            if (isVowels && PyramidConstants.vowels.contains(c))
                count++;
            else if (isConsonants && PyramidConstants.consonants.contains(c))
                count++;
            else if (isLetters)
                count++;
        }

        if (exactCount != -1) {
            if (exactCount == count)
                return true;
            else
                return false;
        } else if (lowerBound != -1) {
            if (lowerBound <= count && upperBound >= count)
                return true;
            else
                return false;
        }

        // We should never get here.
        return false;
    }
}