package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidConstants;

import java.util.HashMap;
import java.util.Set;

// Solves clues of the form:
//
// Most common {letter(s),consonant(s),vowel(s)} each account(s) for: between
// <Percentage> and <Percentage> (inclusive) of the letters
//
// Most common {letter(s),consonant(s),vowel(s)} each account(s) for: exactly
// <Percentage> of the letters
//
// Most common {letter(s),consonant(s),vowel(s)} each appear(s):
// <Integer> times(s)
//
// Most common {letter(s),consonant(s),vowel(s)} each appear(s):
// between <Integer> and <Integer> (inclusive) times
public class MostCommonSolver extends ClueSolver {
    private boolean isLetters = false;
    private boolean isVowels = false;
    private boolean isConsonants = false;

    private int exactNumLetters = -1;
    private int lowerBoundNumLetters = -1;
    private int upperBoundNumLetters = -1;
    private double exactPercentageLetters = -1;
    private double lowerBoundPercentageLetters = -1;
    private double upperBoundPercentageLetters = -1;

    public MostCommonSolver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[2].equals("vowel(s)")) {
            isVowels = true;
        } else if (splitString[2].equals("letter(s)")) {
            isLetters = true;
        } else if (splitString[2].equals("consonant(s)")) {
            isConsonants = true;
        }

        if (splitString[4].equals("appear(s):")) {
            if (splitString[5].equals("between")) {
                lowerBoundNumLetters = Integer.parseInt(splitString[6]);
                upperBoundNumLetters = Integer.parseInt(splitString[8]);
            } else {
                exactNumLetters = Integer.parseInt(splitString[5]);
            }
        } else {
            if (splitString[6].equals("between")) {
                String lower = splitString[7];
                String upper = splitString[9];
                lowerBoundPercentageLetters = Double.parseDouble(lower.substring(0, lower.length() - 1)) / 100.0;
                upperBoundPercentageLetters = Double.parseDouble(upper.substring(0, upper.length() - 1)) / 100.0;
            } else {
                String exact = splitString[7];
                exactPercentageLetters = Double.parseDouble(exact.substring(0, exact.length() - 1)) / 100.0;
            }
        }
    }

    public boolean isValidWord(String word) {
        HashMap<Character, Integer> occurrences = new HashMap<>();
        for (Character c : word.toCharArray()) {
            if (occurrences.keySet().contains(c)) {
                occurrences.put(c, occurrences.get(c) + 1);
            } else {
                occurrences.put(c, 1);
            }
        }

        Set<Character> validLetters = null;
        if (isVowels)
            validLetters = PyramidConstants.vowels;
        else if (isConsonants)
            validLetters = PyramidConstants.consonants;

        int maxLetters = 0;
        for (Character c : occurrences.keySet()) {
            if (validLetters != null && !validLetters.contains(c))
                continue;
            maxLetters = Math.max(maxLetters, occurrences.get(c));
        }

        if (exactNumLetters != -1) {
            if (exactNumLetters == maxLetters)
                return true;
            else
                return false;
        } else if (lowerBoundNumLetters != -1) {
            if (lowerBoundNumLetters <= maxLetters && upperBoundNumLetters >= maxLetters)
                return true;
            else
                return false;
        } else if (exactPercentageLetters != -1) {
            if (exactPercentageLetters == (double) maxLetters / word.length())
                return true;
            else
                return false;
        } else if (lowerBoundPercentageLetters != -1) {
            if (lowerBoundPercentageLetters <= (double) maxLetters / word.length() && upperBoundPercentageLetters >= (double) maxLetters / word.length())
                return true;
            else
                return false;
        }

        // We should never get here.
        return false;
    }
}
