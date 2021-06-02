package me.djsch.puzzlePyramid;

import me.djsch.puzzlePyramid.clueSolvers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class PyramidSolver {
    private Set<String> dictionary;
    public PyramidSolver() {}

    // Solve the Pyramid!
    //
    // For each cell in the Pyramid, we call solveCell() and print the resulting
    // word, if it is correctly solved.
    public void solve() {
        dictionary = PyramidUtils.getWordsDictionary();

        File dir = new File(PyramidUtils.fileBasePath);
        File[] rows = dir.listFiles();
        for (File row : rows) {
            // Skip the examples and the words list.
            if (row.getName().equals("examples") || row.getName().equals("words.txt"))
                continue;
            if (row.isDirectory()) {
                File[] cols = row.listFiles();
                for (File col : cols) {
                    String word = solveCell(col.getAbsolutePath());
                    if (word.equals("")) {
                        System.out.println("FAILED");
                    } else {
                        System.out.println("file: " + col.getName() + " solved with word " + word);
                    }
                }
            }
        }
    }

    // Solve the given Cell.
    //
    // For each word in the dictionary, we check the word against each Clue in the
    // given file. Once we have found a word that satisfies all the clues, we can
    // just return that word.
    private String solveCell(String filepath) {
        ArrayList<String> clues = PyramidFileReaderWriter.readCellFile(filepath);
        for (String word : dictionary) {
            boolean flag = true;
            for (String clue : clues) {
                if (clue.equals(""))
                    continue;
                if (PyramidUtils.isPropertyClue(clue))
                    continue;

                ClueSolver solver = PyramidUtils.getClueSolver(clue);
                if (solver != null) {
                    if (solver.isValidWord(word)) {
                        continue;
                    } else {
                        flag = false;
                        break;
                    }
                } else {
                    System.out.println("failed to find solver for clue:");
                    System.out.println(clue);
                    System.out.println("and word " + word);
                    System.exit(1);
                }
            }
            if (flag)
                return word;
        }
        System.out.println("Couldn't find a valid word for clues:");
        for (String clue : clues) {
            System.out.println(clue);
        }
        return "";
    }
}
