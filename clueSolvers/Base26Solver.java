package me.djsch.puzzlePyramid.clueSolvers;

import me.djsch.puzzlePyramid.PyramidConstants;

import java.math.BigInteger;

// Solves clues of the form:
//
// Word interpreted as a base 26 number (A=0, B=1, etc) is divisible by <Integer>: {YES, NO}
//
// Word interpreted as a base 26 number (A=0, B=1, etc) is exactly representable
// in IEEE 754 {single, double}-precision floating point format: {YES, NO}
//
// Word interpreted as a base 26 number (A=0, B=1, etc) is representable as an
// unsigned {32, 64}-bit integer: {YES, NO}
//
// We use a BigInteger to represent these large values and check if the value fits
// the given parameters.
public class Base26Solver extends ClueSolver {
    private int divisor = -1;
    private boolean doublePrecisionClue = false;
    private boolean singlePrecisionClue = false;
    private boolean representable32Clue = false;
    private boolean representable64Clue = false;
    private boolean isClueValid;

    public Base26Solver(String clue) {
        String[] splitString = clue.split(" ");
        if (splitString[11].equals("divisible")) {
            String div = splitString[13];
            divisor = Integer.parseInt(div.substring(0, div.length() - 1));
            if (splitString[14].equals("YES"))
                isClueValid = true;
            else
                isClueValid = false;
        } else if (splitString[16].equals("double-precision")) {
            doublePrecisionClue = true;
            if (splitString[20].equals("YES"))
                isClueValid = true;
            else
                isClueValid = false;
        } else if (splitString[16].equals("single-precision")) {
            singlePrecisionClue = true;
            if (splitString[20].equals("YES"))
                isClueValid = true;
            else
                isClueValid = false;
        } else if (splitString[11].equals("representable")) {
            if (splitString[15].equals("64-bit"))
                representable64Clue = true;
            else
                representable32Clue = true;
            if (splitString[17].equals("YES"))
                isClueValid = true;
            else
                isClueValid = false;
        }
    }

    public boolean isValidWord(String word) {
        BigInteger value = new BigInteger(BigInteger.ZERO.toString());

        // Calculate the base 26 value of the word.
        for (int i = word.length() - 1; i >= 0; i--) {
            int exp = word.length() - 1 - i;
            int base = PyramidConstants.charToInt0Indexed.get(word.charAt(i));
            BigInteger add = new BigInteger("26");
            add = add.pow(exp).multiply(new BigInteger(base + ""));
            value = value.add(add);
        }

        if (divisor != -1) {
            if (value.mod(new BigInteger(divisor + "")).intValue() == 0)
                return isClueValid;
            else
                return !isClueValid;
        } else if (doublePrecisionClue) {
            // Remove any trailing 0's from the base 26 value, because their value can
            // be represented in the exponent.
            while (true) {
                if (value.mod(new BigInteger(2 + "")).intValue() == 0)
                    value = value.divide(new BigInteger(2 + ""));
                else
                    break;
            }
            BigInteger maxVal = new BigInteger(2 + "").pow(54);
            if (value.compareTo(maxVal) <= 0)
                return isClueValid;
            else
                return !isClueValid;
        } else if (singlePrecisionClue) {
            // Remove any trailing 0's from the base 26 value, because their value can
            // be represented in the exponent.
            while (true) {
                if (value.mod(new BigInteger(2 + "")).intValue() == 0)
                    value = value.divide(new BigInteger(2 + ""));
                else
                    break;
            }
            BigInteger maxVal = new BigInteger(2 + "").pow(24);
            if (value.compareTo(maxVal) <= 0)
                return isClueValid;
            else
                return !isClueValid;
        } else if (representable32Clue) {
            // Check if the base 26 value is less than 2^32 - 1
            BigInteger maxVal = new BigInteger(2 + "").pow(32).subtract(new BigInteger(1 + ""));
            if (value.compareTo(maxVal) <= 0)
                return isClueValid;
            else
                return !isClueValid;
        } else if (representable64Clue) {
            // Check if the base 26 value is less than 2^64 - 1
            BigInteger maxVal = new BigInteger(2 + "").pow(64).subtract(new BigInteger(1 + ""));
            if (value.compareTo(maxVal) <= 0)
                return isClueValid;
            else
                return !isClueValid;
        }

        return false;
    }
}
