package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidConstants;

// Solves clues of the form:
//
// Vowels: <Quantity>
public class VowelsSolver extends ClueSolver {
    private boolean isLetters = false;
    private boolean isVowels = false;
    private boolean isConsonants = false;

    private int exactNumLetters = -1;
    private int lowerBoundNumLetters = -1;
    private int upperBoundNumLetters = -1;
    private double exactPercentageLetters = -1;
    private double lowerBoundPercentageLetters = -1;
    private double upperBoundPercentageLetters = -1;

    public VowelsSolver(String clue) {
        String[] splitString = clue.split(" ");

        if (splitString[1].equals("between")) {
            String lower = splitString[2];
            String upper = splitString[4];
            if (lower.substring(lower.length() - 1).equals("%")) {
                lowerBoundPercentageLetters = Double.parseDouble(lower.substring(0, lower.length() - 1)) / 100.0;
                upperBoundPercentageLetters = Double.parseDouble(upper.substring(0, upper.length() - 1)) / 100.0;
            } else {
                lowerBoundNumLetters = Integer.parseInt(splitString[2]);
                upperBoundNumLetters = Integer.parseInt(splitString[4]);
            }
        } else {
            if (splitString[1].equals("exactly")) {
                String tmp = splitString[2];
                exactPercentageLetters = Double.parseDouble(tmp.substring(0, tmp.length() - 1)) / 100.0;
            } else {
                exactNumLetters = Integer.parseInt(splitString[1]);
            }
        }
    }

    public boolean isValidWord(String word) {
        int count = 0;
        for (Character c : word.toCharArray()) {
            if (PyramidConstants.vowels.contains(c))
                count++;
        }

        if (exactNumLetters != -1) {
            if (exactNumLetters == count)
                return true;
            else
                return false;
        } else if (lowerBoundNumLetters != -1) {
            if (lowerBoundNumLetters <= count && upperBoundNumLetters >= count)
                return true;
            else
                return false;
        } else if (exactPercentageLetters != -1) {
            if (exactPercentageLetters == (double) count / word.length())
                return true;
            else
                return false;
        } else if (lowerBoundPercentageLetters != -1) {
            if (lowerBoundPercentageLetters <= (double) count / word.length() && upperBoundPercentageLetters >= (double) count / word.length())
                return true;
            else
                return false;
        }

        // We should never get here.
        return false;
    }
}
