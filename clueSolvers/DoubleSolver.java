package me.djsch.puzzlePyramid.clueSolvers;

// Solves clues of the form:
//
// Contains at least one doubled letter: {YES, NO}
public class DoubleSolver extends ClueSolver {
    private boolean isDouble;

    public DoubleSolver(String clue) {
        String[] splitString = clue.split(" ");
        isDouble = splitString[6].equals("YES");
    }

    public boolean isValidWord(String word) {
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i+1])
                return isDouble;
        }
        return !isDouble;
    }
}
