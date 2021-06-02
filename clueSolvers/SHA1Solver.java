package me.djsch.puzzlePyramid.clueSolvers;

import java.security.MessageDigest;

// Solves clues of the form:
//
// SHA-1 hash of lowercased word, expressed in hexadecimal,
// {starts with, ends with, contains}: <String>
public class SHA1Solver extends ClueSolver {
    private enum POSITION {
        START,
        MIDDLE,
        END
    }

    private POSITION position;
    private String value;
    public SHA1Solver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[8].equals("starts")) {
            position = POSITION.START;
            value = splitString[10].toLowerCase();
        } else if (splitString[8].equals("contains:")) {
            position = POSITION.MIDDLE;
            value = splitString[9].toLowerCase();
        } else if (splitString[8].equals("ends")) {
            position = POSITION.END;
            value = splitString[10].toLowerCase();
        }
    }

    public boolean isValidWord(String word) {
        String sha1 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(word.getBytes("utf8"));

            byte[] data = digest.digest();
            char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
            char[] chars = new char[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
                chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
            }
            sha1 = new String(chars);

        } catch (Exception e){
            e.printStackTrace();
        }

        sha1 = sha1.toLowerCase();


        if (position == POSITION.START) {
            if (sha1.startsWith(value))
                return true;
            else
                return false;
        } else if (position == POSITION.MIDDLE) {
            if (sha1.contains(value))
                return true;
            else
                return false;
        } else if (position == POSITION.END) {
            if (sha1.endsWith(value))
                return true;
            else
                return false;
        }

        // We should never get here.
        return false;
    }
}
