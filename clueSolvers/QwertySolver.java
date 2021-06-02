package me.djsch.puzzlePyramid.clueSolvers;

import java.util.Set;

// Solves clues of the form:
//
// Letters located in the {top, middle, bottom} row on a QWERTY
// keyboard: <Quantity>
public class QwertySolver extends ClueSolver {
    public enum ROW {
        TOP,
        MIDDLE,
        BOTTOM
    }

    private static final Set<Character> topRowLetters = Set.of('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p');
    private static final Set<Character> middleRowLetters = Set.of('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l');
    private static final Set<Character> bottomRowLetters = Set.of('z', 'x', 'c', 'v', 'b', 'n', 'm');

    private int exactNumLetters = -1;
    private int lowerBoundNumLetters = -1;
    private int upperBoundNumLetters = -1;
    private double exactPercentageLetters = -1;
    private double lowerBoundPercentageLetters = -1;
    private double upperBoundPercentageLetters = -1;
    private ROW row;
    public QwertySolver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[4].equals("top"))
            row = ROW.TOP;
        else if (splitString[4].equals("middle"))
            row = ROW.MIDDLE;
        else if (splitString[4].equals("bottom"))
            row = ROW.BOTTOM;

        if (splitString[10].equals("between")) {
            String lower = splitString[11];
            String upper = splitString[13];
            if (lower.charAt(lower.length() - 1) == '%') {
                lowerBoundPercentageLetters = Double.valueOf(lower.substring(0, lower.length() - 1)) / 100;
                upperBoundPercentageLetters = Double.valueOf(upper.substring(0, upper.length() - 1)) / 100;
            } else {
                lowerBoundNumLetters = Integer.valueOf(lower);
                upperBoundNumLetters = Integer.valueOf(upper);
            }
        } else if (splitString[10].equals("exactly")) {
            exactPercentageLetters = Double.valueOf(splitString[11].substring(0, splitString[11].length() - 1)) / 100;
        } else {
            exactNumLetters = Integer.valueOf(splitString[10]);
        }
    }

    public boolean isValidWord(String word) {
        int count = 0;
        Set<Character> validLetters = null;
        if (row == ROW.TOP)
            validLetters = topRowLetters;
        else if (row == ROW.MIDDLE)
            validLetters = middleRowLetters;
        else
            validLetters = bottomRowLetters;

        for (Character c : word.toCharArray()) {
            if (validLetters.contains(c))
                count++;
        }

        if (exactNumLetters != -1) {
            if (exactNumLetters == count)
                return true;
            else
                return false;
        } else if (exactPercentageLetters != -1) {
            if (exactPercentageLetters == ((double) count / word.length()))
                return true;
            else
                return false;
        } else if (lowerBoundNumLetters != -1) {
            if (lowerBoundNumLetters <= count && upperBoundNumLetters >= count)
                return true;
            else
                return false;
        } else if (lowerBoundPercentageLetters != -1) {
            if (lowerBoundPercentageLetters <= ((double) count / word.length()) && upperBoundPercentageLetters >= ((double) count / word.length()))
                return true;
            else
                return false;
        }

        // We should never get here
        return false;
    }
}
