package me.djsch.puzzlePyramid.clueSolvers;

// Solves clues of the form:
//
// Contains: <String>
public class ContainsSolver extends ClueSolver {
    private String contains;
    public ContainsSolver(String clue) {
        String[] splitString = clue.split(" ");
        contains = splitString[1];
    }

    public boolean isValidWord(String word) {
        if (word.toLowerCase().contains(contains.toLowerCase()))
            return true;
        return false;
    }
}
