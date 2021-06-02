package me.djsch.puzzlePyramid.clueSolvers;

// Solves clues of the form:
//
// Ends with: <String>
public class EndsWithSolver extends ClueSolver {
    private String suffix;
    public EndsWithSolver(String clue) {
        String[] splitString = clue.split(" ");
        suffix = splitString[2];
    }

    public boolean isValidWord(String word) {
        if (word.length() == 0)
            return false;

        if (word.toLowerCase().endsWith(suffix.toLowerCase()))
            return true;
        else
            return false;
    }
}
