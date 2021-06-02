package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidUtils;

import java.util.Set;

// Solves clues of the form:
//
// If you marked nonoverlapping chemical element symbols (atomic
// number 112 or below), you could mark at most: <Quantity>
//
// If you marked nonoverlapping occurrences of words in the word
// list that are 3 or fewer letters long, you could mark at
// most: <Quantity>
//
// If you marked nonoverlapping US state postal abbreviations,
// you could mark at most: <Quantity>
//
// If you marked nonoverlapping officially-assigned ISO 3166-1 alpha-2
// country codes, you could mark at most: <Quantity>
//
// We leverage a simple recursive algorithm in getMaxLength to do the heavy
// lifting.
public class NonoverlappingSolver extends ClueSolver {

    private static final Set<String> chemicalElements = Set.of(
            "h", "he", "li", "be", "b", "c", "n", "o", "f", "ne",
            "na", "mg", "al", "si", "p", "s", "cl", "ar", "k",
            "ca", "sc", "ti", "v", "cr", "mn", "fe", "co", "ni",
            "cu", "zn",  "ga", "ge", "as", "se", "br", "kr", "rb",
            "sr", "y", "zr", "nb", "mo", "tc", "ru", "rh", "pd",
            "ag", "cd", "in", "sn", "sb", "te", "i", "xe", "cs",
            "ba", "la", "ce", "pr", "nd", "pm", "sm", "eu", "gd",
            "Tb", "Dy", "Ho", "er", "tm", "yb", "lu", "hf", "ta",
            "w", "re", "os", "ir", "pt", "au", "hg", "tl", "pb",
            "bi", "po", "at", "rn", "fr", "ra", "ac", "th", "pa",
            "u", "np", "pu", "am", "cm", "bk", "cf", "es", "fm",
            "md", "no", "lr", "rf", "db", "sg", "bh", "hs", "mt",
            "ds", "rg", "cn");

    private static final Set<String> stateAbbreviations = Set.of(
            "al", "ak", "az", "ar", "ca", "co", "ct", "de", "fl",
            "ga", "hi", "id", "il", "in", "ia", "ks", "ky", "la",
            "me", "md", "ma", "mi", "mn", "ms", "mo", "mt", "ne",
            "nv", "nh", "nj", "nm", "ny", "nc", "nd", "oh", "ok",
            "or", "pa", "ri", "sc", "sd", "tn", "tx", "ut", "vt",
            "va", "wa", "wv", "wi", "wy");

    private static final Set<String> countryCodes = Set.of(
            "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq",
            "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb",
            "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm",
            "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by",
            "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck",
            "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx",
            "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ed",
            "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk",
            "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf",
            "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr",
            "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr",
            "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq",
            "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg",
            "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz",
            "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu",
            "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh",
            "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms",
            "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc",
            "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu",
            "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl",
            "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re",
            "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se",
            "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so",
            "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td",
            "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to",
            "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us",
            "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu",
            "wf", "ws", "ye", "yt", "za", "zm", "zw");

    private String suffix;
    private Set<String> wordsLength3dict;

    private boolean chemicalClue = false;
    private boolean stateClue = false;
    private boolean countryClue = false;
    private boolean threeLetterWordsClue = false;

    private int exactLetterCount = -1;
    private int lowerLetterBound = -1;
    private int upperLetterBound = -1;
    private double exactLetterPercentage = -1;
    private double lowerLetterPercentageBound = -1;
    private double upperLetterPercentageBound = -1;

    public NonoverlappingSolver(String clue) {
        wordsLength3dict = PyramidUtils.getWordsLength3Dictionary();
        int lowerBound = 0;
        String[] splitString = clue.split(" ");
        if (splitString[4].equals("chemical")) {
            chemicalClue = true;
            lowerBound = 17;
        } else if (splitString[4].equals("US")) {
            stateClue = true;
            lowerBound = 13;
        } else if (splitString[4].equals("officially-assigned")) {
            countryClue = true;
            lowerBound = 15;
        } else if (splitString[4].equals("occurrences")) {
            threeLetterWordsClue = true;
            lowerBound = 23;
        }

        if (splitString[lowerBound].equals("between")) {
            String lower = splitString[lowerBound + 1];
            String upper = splitString[lowerBound + 3];
            if (lower.charAt(lower.length() - 1) == '%') {
                lowerLetterPercentageBound = Double.parseDouble(lower.substring(0, lower.length() - 1)) / 100.0;
                upperLetterPercentageBound = Double.parseDouble(upper.substring(0, upper.length() - 1)) / 100.0;
            } else {
                lowerLetterBound = Integer.parseInt(lower);
                upperLetterBound = Integer.parseInt(upper);
            }
        } else if (splitString[lowerBound].equals("exactly")) {
            exactLetterPercentage = Double.parseDouble(splitString[lowerBound+1].substring(0, splitString[lowerBound+1].length() - 1)) / 100.0;
        } else {
            exactLetterCount = Integer.parseInt(splitString[lowerBound]);
        }
    }

    public boolean isValidWord(String word) {
        int maxLength = -1;
        if (chemicalClue) {
            maxLength = getMaxLength(word, chemicalElements);
        } else if (stateClue) {
            maxLength = getMaxLength(word, stateAbbreviations);
        } else if (countryClue) {
            maxLength = getMaxLength(word, countryCodes);
        } else if (threeLetterWordsClue) {
            maxLength = getMaxLength(word, wordsLength3dict);
        }

        if (exactLetterCount != -1) {
            if (maxLength == exactLetterCount)
                return true;
            else
                return false;
        } else if (lowerLetterBound != -1) {
            if (lowerLetterBound <= maxLength && upperLetterBound >= maxLength)
                return true;
            else
                return false;
        } else if (exactLetterPercentage != -1) {
            if (exactLetterPercentage == (double) maxLength / word.length())
                return true;
            else
                return false;
        } else if (upperLetterPercentageBound != -1 ) {
            if (lowerLetterPercentageBound <= (double) maxLength / word.length() && upperLetterPercentageBound >= (double) maxLength / word.length())
                return true;
            else
                return false;
        }

        return false;
    }

    // We assume, by inspection of the possible Sets of substrings, that the
    // length of strings in validSubstrings is no longer than 3.
    //
    // We implement a simple recursive algorithm to check all possible permutations of
    // the given substrings and return the maximum possible length of the given
    // word (fragment) that can be covered by the Set of valid substrings.
    private int getMaxLength(String wordFragment, Set<String> validSubstrings) {
        if (wordFragment.length() == 0)
            return 0;

        int maxLength = 0;
        if (wordFragment.length() >= 1 && validSubstrings.contains(wordFragment.substring(0, 1)))
            maxLength = Math.max(maxLength, 1 + getMaxLength(wordFragment.substring(1), validSubstrings));
        if (wordFragment.length() >= 2 && validSubstrings.contains(wordFragment.substring(0, 2)))
            maxLength = Math.max(maxLength, 2 + getMaxLength(wordFragment.substring(2), validSubstrings));
        if (wordFragment.length() >= 3 && validSubstrings.contains(wordFragment.substring(0, 3)))
            maxLength = Math.max(maxLength, 3 + getMaxLength(wordFragment.substring(3), validSubstrings));

        maxLength = Math.max(maxLength, getMaxLength(wordFragment.substring(1), validSubstrings));
        return maxLength;
    }
}
