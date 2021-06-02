package me.djsch.puzzlePyramid.clueSolvers;

import java.util.Set;

import me.djsch.puzzlePyramid.PyramidConstants;
import me.djsch.puzzlePyramid.PyramidUtils;

// Solves clues of the form:
//
// Can be Caesar shifted to produce another word in the word list: {YES, NO}
public class CaesarSolver extends ClueSolver{

    Set<String> dictionary;
    boolean isCaesarShift;

    public CaesarSolver(String clue) {
        String[] splitString = clue.split(" ");
        isCaesarShift = splitString[12].equals("YES");
        dictionary = PyramidUtils.getWordsDictionary();
    }

    public boolean isValidWord(String word) {
        int[] charOffsets = new int[word.length()];
        for (int i = 0; i < charOffsets.length; i++) {
            charOffsets[i] = PyramidConstants.charToInt1Indexed.get(word.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        // Build the 25 possible caesar shifts of the given word, and check if any of them
        // are in the word dictionary.
        for (int i = 1; i < 26; i++) {
            builder.setLength(0);
            for (int j = 0; j < charOffsets.length; j++) {
                builder.append(PyramidConstants.intToChar.get(((charOffsets[j] + i - 1) % 26) + 1));
            }
            String potentialWord = builder.toString();
            if (dictionary.contains(potentialWord))
                return isCaesarShift;
        }
        return !isCaesarShift;
    }
}
