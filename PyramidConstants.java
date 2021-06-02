package me.djsch.puzzlePyramid;

import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

// Store some common sets and mappings here for ease of access.
public class PyramidConstants {
    public static final Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    public static final Set<Character> consonants = Set.of('b', 'c', 'd', 'f', 'g', 'h',
            'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z');

    public static final Map<Character, Integer> charToInt1Indexed = Map.ofEntries(
            entry(Character.valueOf('a'), 1),
            entry(Character.valueOf('b'), 2),
            entry(Character.valueOf('c'), 3),
            entry(Character.valueOf('d'), 4),
            entry(Character.valueOf('e'), 5),
            entry(Character.valueOf('f'), 6),
            entry(Character.valueOf('g'), 7),
            entry(Character.valueOf('h'), 8),
            entry(Character.valueOf('i'), 9),
            entry(Character.valueOf('j'), 10),
            entry(Character.valueOf('k'), 11),
            entry(Character.valueOf('l'), 12),
            entry(Character.valueOf('m'), 13),
            entry(Character.valueOf('n'), 14),
            entry(Character.valueOf('o'), 15),
            entry(Character.valueOf('p'), 16),
            entry(Character.valueOf('q'), 17),
            entry(Character.valueOf('r'), 18),
            entry(Character.valueOf('s'), 19),
            entry(Character.valueOf('t'), 20),
            entry(Character.valueOf('u'), 21),
            entry(Character.valueOf('v'), 22),
            entry(Character.valueOf('w'), 23),
            entry(Character.valueOf('x'), 24),
            entry(Character.valueOf('y'), 25),
            entry(Character.valueOf('z'), 26)
    );

    public static final Map<Character, Integer> charToInt0Indexed = Map.ofEntries(
            entry(Character.valueOf('a'), 0),
            entry(Character.valueOf('b'), 1),
            entry(Character.valueOf('c'), 2),
            entry(Character.valueOf('d'), 3),
            entry(Character.valueOf('e'), 4),
            entry(Character.valueOf('f'), 5),
            entry(Character.valueOf('g'), 6),
            entry(Character.valueOf('h'), 7),
            entry(Character.valueOf('i'), 8),
            entry(Character.valueOf('j'), 9),
            entry(Character.valueOf('k'), 10),
            entry(Character.valueOf('l'), 11),
            entry(Character.valueOf('m'), 12),
            entry(Character.valueOf('n'), 13),
            entry(Character.valueOf('o'), 14),
            entry(Character.valueOf('p'), 15),
            entry(Character.valueOf('q'), 16),
            entry(Character.valueOf('r'), 17),
            entry(Character.valueOf('s'), 18),
            entry(Character.valueOf('t'), 19),
            entry(Character.valueOf('u'), 20),
            entry(Character.valueOf('v'), 21),
            entry(Character.valueOf('w'), 22),
            entry(Character.valueOf('x'), 23),
            entry(Character.valueOf('y'), 24),
            entry(Character.valueOf('z'), 25)
    );

    public static final Map<Integer, Character> intToChar = Map.ofEntries(
            entry(1, Character.valueOf('a')),
            entry(2, Character.valueOf('b')),
            entry(3, Character.valueOf('c')),
            entry(4, Character.valueOf('d')),
            entry(5, Character.valueOf('e')),
            entry(6, Character.valueOf('f')),
            entry(7, Character.valueOf('g')),
            entry(8, Character.valueOf('h')),
            entry(9, Character.valueOf('i')),
            entry(10, Character.valueOf('j')),
            entry(11, Character.valueOf('k')),
            entry(12, Character.valueOf('l')),
            entry(13, Character.valueOf('m')),
            entry(14, Character.valueOf('n')),
            entry(15, Character.valueOf('o')),
            entry(16, Character.valueOf('p')),
            entry(17, Character.valueOf('q')),
            entry(18, Character.valueOf('r')),
            entry(19, Character.valueOf('s')),
            entry(20, Character.valueOf('t')),
            entry(21, Character.valueOf('u')),
            entry(22, Character.valueOf('v')),
            entry(23, Character.valueOf('w')),
            entry(24, Character.valueOf('x')),
            entry(25, Character.valueOf('y')),
            entry(26, Character.valueOf('z'))
    );
}
