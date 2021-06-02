package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidConstants;

// Solves clues of the form:
//
// Sum of letters (A=1, B=2, etc) is divisible by <Integer>: {YES, NO}
//
// Sum of letters (A=1, B=2, etc): <Integer>
public class LetterSumSolver extends ClueSolver {
    private int totalSum = -1;
    private int minSum = -1;
    private int maxSum = -1;
    private int divisor = -1;
    private boolean isDivisible;

    public LetterSumSolver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[5].equals("etc):")) {
            if (splitString[6].equals("between")) {
                minSum = Integer.parseInt(splitString[7]);
                maxSum = Integer.parseInt(splitString[9]);
            }
            else
                totalSum = Integer.parseInt(splitString[6]);
        } else {
            String div = splitString[9];
            divisor = Integer.parseInt(div.substring(0, div.length() - 1));
            if (splitString[10].equals("YES"))
                isDivisible = true;
            else
                isDivisible = false;
        }
    }

    public boolean isValidWord(String word) {
        int sum = 0;
        for (Character c : word.toCharArray()) {
            sum += PyramidConstants.charToInt1Indexed.get(c);
        }

        if (totalSum != -1) {
            if (sum == totalSum)
                return true;
            else
                return false;
        } else if (minSum != -1) {
            if (minSum <= sum && maxSum >= sum)
                return true;
            else
                return false;
        } else {
                if (sum % divisor == 0)
                    return isDivisible;
                else
                    return !isDivisible;
        }
    }
}
