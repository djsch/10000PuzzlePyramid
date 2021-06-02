package me.djsch.puzzlePyramid.clueSolvers;

import java.util.Map;

import static java.util.Map.entry;

// Solves clues of the form:
//
// Base Scrabble score: <Integer> points
//
// Base Scrabble score: between <Integer> and <Integer> (inclusive) points
public class ScrabbleSolver extends ClueSolver {
    private static final Map<Character, Integer> scrabbleValues = Map.ofEntries(
            entry(Character.valueOf('a'), 1),
            entry(Character.valueOf('b'), 3),
            entry(Character.valueOf('c'), 3),
            entry(Character.valueOf('d'), 2),
            entry(Character.valueOf('e'), 1),
            entry(Character.valueOf('f'), 4),
            entry(Character.valueOf('g'), 2),
            entry(Character.valueOf('h'), 4),
            entry(Character.valueOf('i'), 1),
            entry(Character.valueOf('j'), 8),
            entry(Character.valueOf('k'), 5),
            entry(Character.valueOf('l'), 1),
            entry(Character.valueOf('m'), 3),
            entry(Character.valueOf('n'), 1),
            entry(Character.valueOf('o'), 1),
            entry(Character.valueOf('p'), 3),
            entry(Character.valueOf('q'), 10),
            entry(Character.valueOf('r'), 1),
            entry(Character.valueOf('s'), 1),
            entry(Character.valueOf('t'), 1),
            entry(Character.valueOf('u'), 1),
            entry(Character.valueOf('v'), 4),
            entry(Character.valueOf('w'), 4),
            entry(Character.valueOf('x'), 8),
            entry(Character.valueOf('y'), 4),
            entry(Character.valueOf('z'), 10)
    );

    private int targetValue = -1;
    private int minTargetValue = -1;
    private int maxTargetValue = -1;

    public ScrabbleSolver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[3].equals("between")) {
            minTargetValue = Integer.parseInt(splitString[4]);
            maxTargetValue = Integer.parseInt(splitString[6]);
        } else {
            targetValue = Integer.parseInt(splitString[3]);
        }
    }

    public boolean isValidWord(String word) {
        int scrabbleValue = 0;
        for (char c : word.toCharArray()) {
            scrabbleValue += scrabbleValues.get(c);
        }

        if (targetValue == -1) {
            if (minTargetValue <= scrabbleValue && maxTargetValue >= scrabbleValue)
                return true;
            else
                return false;
        } else {
            if (scrabbleValue == targetValue)
                return true;
            else
                return false;
        }
        // We should never get here.
    }
}
