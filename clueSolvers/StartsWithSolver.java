package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidConstants;
import me.djsch.puzzlePyramid.PyramidUtils;

import java.util.Set;

// Solves clues of the form:
//
// Starts with: <String>
//
// Starts with a vowel: {YES, NO}
public class StartsWithSolver extends ClueSolver {
    private String prefix = "";
    private boolean startsWithVowel;
    private Set<Character> vowels = PyramidConstants.vowels;

    public StartsWithSolver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[1].equals("with:"))
            prefix = splitString[2].toLowerCase();
        else if (splitString[3].equals("vowel:"))
            startsWithVowel = splitString[4].equals("YES");
    }

    public boolean isValidWord(String word) {
        if (!prefix.equals(""))
            return word.toLowerCase().startsWith(prefix.toLowerCase());

        // othewise, we are checking if this word starts with a vowel
        if (vowels.contains(word.charAt(0)))
            return startsWithVowel;
        else
            return !startsWithVowel;
    }
}
