package me.djsch.puzzlePyramid;

import me.djsch.puzzlePyramid.clueSolvers.*;

import java.util.Set;

public class PyramidUtils {
    public static final String fileBasePath = "/home/djsch/projects/10000puzzlePyramid/pyramid";

    private static final String wordsFilePath = fileBasePath + "/words.txt";
    private static final String anagram0FilePath = fileBasePath + "/anagram0.txt";
    private static final String anagram1FilePath = fileBasePath + "/anagram1.txt";
    private static final String anagram2FilePath = fileBasePath + "/anagram2.txt";
    private static final String wordsLength3FilePath = fileBasePath + "/words_len_3.txt";

    private static Set<String> wordsDictionary = null;
    private static Set<String> anagram0Dictionary = null;
    private static Set<String> anagram1Dictionary = null;
    private static Set<String> anagram2Dictionary = null;
    private static Set<String> wordsLength3Dictionary = null;

    static {
        wordsDictionary = PyramidFileReaderWriter.readWordsFile(wordsFilePath);
        anagram0Dictionary = PyramidFileReaderWriter.readWordsFile(anagram0FilePath);
        anagram1Dictionary = PyramidFileReaderWriter.readWordsFile(anagram1FilePath);
        anagram2Dictionary = PyramidFileReaderWriter.readWordsFile(anagram2FilePath);
        wordsLength3Dictionary = PyramidFileReaderWriter.readWordsFile(wordsLength3FilePath);
    }

    public static Set<String> getWordsDictionary() {
        return wordsDictionary;
    }

    public static Set<String> getAnagram0Dictionary() {
        return anagram0Dictionary;
    }

    public static Set<String> getAnagram1Dictionary() {
        return anagram1Dictionary;
    }

    public static Set<String> getAnagram2Dictionary() {
        return anagram2Dictionary;
    }

    public static Set<String> getWordsLength3Dictionary() {
        return wordsLength3Dictionary;
    }

    public static boolean isPropertyClue(String clue) {
        if (clue.startsWith("Has property"))
            return true;
        else if (clue.startsWith("This is a word with property"))
            return true;
        else if (clue.startsWith("This is NOT a word with property"))
            return true;
        else if (clue.startsWith("This word is associated with the color"))
            return true;
        else if (clue.startsWith("This word is associated with the concept"))
            return true;
        return false;
    }

    public static ClueSolver getClueSolver(String clue) {
        ClueSolver solver = null;
        if (clue.startsWith("Base Scrabble"))
            solver = new ScrabbleSolver(clue);
        else if (clue.startsWith("Can be Caesar shifted"))
            solver = new CaesarSolver(clue);
        else if (clue.startsWith("Starts with"))
            solver = new StartsWithSolver(clue);
        else if (clue.startsWith("Can be combined with one"))
            solver = new AnagramSolver(clue);
        else if (clue.startsWith("Can be combined with two"))
            solver = new AnagramSolver(clue);
        else if (clue.startsWith("Contains at least one doubled"))
            solver = new DoubleSolver(clue);
        else if (clue.startsWith("Contains at least two different"))
            solver = new DoubleDifferentSolver(clue);
        else if (clue.startsWith("Contains at least two nonoverlapping"))
            solver = new DoubleSameSolver(clue);
        else if (clue.startsWith("Contains:"))
            solver = new ContainsSolver(clue);
        else if (clue.startsWith("Distinct"))
            solver = new DistinctLettersSolver(clue);
        else if (clue.startsWith("Length:"))
            solver = new LengthSolver(clue);
        else if (clue.startsWith("Letters located in the"))
            solver = new QwertySolver(clue);
        else if (clue.startsWith("SHA-1 hash"))
            solver = new SHA1Solver(clue);
        else if (clue.startsWith("Has at least one anagram"))
            solver = new AnagramSolver(clue);
        else if (clue.startsWith("Sum of letters"))
            solver = new LetterSumSolver(clue);
        else if (clue.startsWith("Word interpreted"))
            solver = new Base26Solver(clue);
        else if (clue.startsWith("Ends with:"))
            solver = new EndsWithSolver(clue);
        else if (clue.startsWith("If you marked"))
            solver = new NonoverlappingSolver(clue);
        else if (clue.startsWith("Most common"))
            solver = new MostCommonSolver(clue);
        else if (clue.startsWith("Vowels:"))
            solver = new VowelsSolver(clue);
        return solver;
    }
}
