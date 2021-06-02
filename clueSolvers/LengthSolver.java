package me.djsch.puzzlePyramid.clueSolvers;

// Solves clues of the form:
//
// Length: <Integer> letters
//
// Length: between <Integer> and <Integer> (inclusive) letters

public class LengthSolver extends ClueSolver {
    private int length = -1;
    private int minLength = -1;
    private int maxLength = -1;

    public LengthSolver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[1].equals("between")) {
            minLength = Integer.parseInt(splitString[2]);
            maxLength = Integer.parseInt(splitString[4]);
        } else {
            length = Integer.parseInt(splitString[1]);
        }
    }

    public boolean isValidWord(String word) {
        if (length != -1) {
            if (word.length() == length)
                return true;
            else
                return false;
        }
        else {
            if (minLength <= word.length() && maxLength >= word.length())
                return true;
            else
                return false;
        }
    }
}
