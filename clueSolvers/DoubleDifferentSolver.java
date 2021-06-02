package me.djsch.puzzlePyramid.clueSolvers;

import java.util.HashSet;

// Solves clues of the form:
//
// Contains at least two different doubled letters: {YES, NO}
public class DoubleDifferentSolver extends ClueSolver {
    private boolean isDouble;

    public DoubleDifferentSolver(String clue) {
        String[] splitString = clue.split(" ");
        isDouble = splitString[7].equals("YES");
    }

    public boolean isValidWord(String word) {
        char[] arr = word.toCharArray();
        HashSet<Character> doubledLetters = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i+1]) {
                doubledLetters.add(arr[i]);
            }
        }
        if (doubledLetters.size() >= 2)
            return isDouble;
        return !isDouble;
    }
}
