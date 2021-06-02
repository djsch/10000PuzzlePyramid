package me.djsch.puzzlePyramid.clueSolvers;

import java.util.HashSet;

// Solves clues of the form:
//
// Contains at least two nonoverlapping occurrences of the same doubled letter: {YES, NO}
public class DoubleSameSolver extends ClueSolver {
    private boolean isDouble;

    public DoubleSameSolver(String clue) {
        String[] splitString = clue.split(" ");
        isDouble = splitString[11].equals("YES");
    }

    public boolean isValidWord(String word) {
        char[] arr = word.toCharArray();
        HashSet<Character> doubledLetters = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i+1]) {
                if (doubledLetters.contains(arr[i]))
                    return isDouble;
                else {
                    doubledLetters.add(arr[i]);
                    i++;
                }
            }
        }
        return !isDouble;
    }
}
