package me.djsch.puzzlePyramid;

public class main {
    public static void main(String[] args) {
        PyramidSolver solver = new PyramidSolver();
        solver.solve();

        // This block of code tests the examples given in the /examples directory.
        /*
        PyramidTester tester = new PyramidTester();
        tester.testDetailed();
        tester.testNormal();
         */

        // This block of code preprocesses the necessary files for anagrams.
        PyramidAnagramPreprocessor preprocessor = new PyramidAnagramPreprocessor();
        preprocessor.preprocess();
    }
}
