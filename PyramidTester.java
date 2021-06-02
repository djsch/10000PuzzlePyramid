package me.djsch.puzzlePyramid;

import me.djsch.puzzlePyramid.clueSolvers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class PyramidTester {

    // Test all the files named "detailed_*" in the /examples folder. Specifically,
    // for each file, get the given correct word, and test it against each given
    // clue to ensure the ClueSolver we fetch returns true.
    public void testDetailed() {
        File dir = new File(PyramidUtils.fileBasePath + "/examples");
        File[] files = dir.listFiles();
        for (File example : files) {
            if (example.getName().startsWith("normal"))
                continue;
            ArrayList<String> clues = PyramidFileReaderWriter.readCellFile(example.getAbsolutePath());

            String tmp = clues.get(0).split(" ")[3];
            String word = tmp.substring(0, tmp.length() - 1).toLowerCase();

            // Skip the first two irrelevant lines.
            clues.remove(0);
            clues.remove(0);

            for (String clue : clues) {
                ClueSolver solver = PyramidUtils.getClueSolver(clue);

                if (solver != null) {
                    if (!solver.isValidWord(word)) {
                        System.out.println("Solver failed for clue:");
                        System.out.println(clue);
                        System.out.println("and word " + word);
                        System.exit(1);
                    }
                } else {
                    System.out.println("Failed to find solver for clue:");
                    System.out.println(clue);
                    System.out.println("and word " + word);
                    System.exit(1);
                }
            }
        }
    }

    // Test all the files named "normal_*" in the /examples folder. Specifically,
    // for each file, get the given correct word, and iterate through all possible
    // words in the words file and test them against the given clues. Once we find
    // a word that satisfies all the clues, we check that it matches the given
    // correct word.
    public void testNormal() {
        File dir = new File(PyramidUtils.fileBasePath + "/examples");
        File[] files = dir.listFiles();
        for (File example : files) {
            if (example.getName().startsWith("detailed"))
                continue;
            ArrayList<String> clues = PyramidFileReaderWriter.readCellFile(example.getAbsolutePath());

            String tmp = clues.get(0).split(" ")[5];
            String correctWord = tmp.substring(0, tmp.length() - 1).toLowerCase();

            clues.remove(0);
            clues.remove(0);

            Set<String> dictionary = PyramidUtils.getWordsDictionary();
            for (String candidateWord : dictionary) {
                boolean flag = true;
                for (String clue : clues) {
                    ClueSolver solver = PyramidUtils.getClueSolver(clue);

                    if (solver != null) {
                        if (!solver.isValidWord(candidateWord)) {
                            flag = false;
                            break;
                        }
                    } else {
                        System.out.println("failed to find solver for clue:");
                        System.out.println(clue);
                        System.exit(1);
                    }
                }
                if (!flag)
                    continue;

                // candidate word should fulfill all the clues
                if (!correctWord.equals(candidateWord)) {
                    System.out.println("Failed to find the correct word: " + correctWord);
                    System.out.println("Thought the correct word was: " + candidateWord);
                    System.out.println("for file: " + example.getAbsolutePath());
                    System.exit(1);
                } else {
                    System.out.println("Correctly solved " + example.getAbsolutePath());
                    System.out.println("The answer was " + candidateWord);
                    break;
                }
            }
        }
    }
}
