Daniel Schneider

This is a java implementation of a partial solution to the puzzle The 10,000 Puzzle Pyramid from the 2015 MIT Mystery Hunt. The puzzle can be found at this link:

http://web.mit.edu/puzzle/www/2015/puzzle/the_10_000_puzzle_pyramid/

with a more detailed explanation available at the same link by clicking on 'View Solution'.

I enjoy using this puzzle as a way to refresh my skills in a language, or to learn a new language, as it requires you to use many different features of the language (string manipulation, data structure management, reading/writing to/from files, etc). It is also a convenient representation of my coding ability.

I say that this is only a 'partial' solution because the actual puzzle requires additional manual work beyond an initial implementation. A complete solution would require the solver to work back and forth between the output from each attempt to solve the puzzle to further refine the solver. For instance, the nonsense word "AMENEMHAT" in this puzzle means 'starts with the letter U', but the solver would need to manually inspect the puzzle's output to determine this, and then add additional functionality to account for "AMENEMHAT" and continue solving in this way. Since the goal of this project is not to present a full solution, but instead to refresh my familiarity with and prove my competency in Java, I leave the solution as only 'partial'.

As this is not a long-term project, there are of course many improvements and optimizations that could be applied. For instance, I preprocess many of the anagram clues because determining whether a word has an anagram in the word list is quite time consuming. I could also preprocess other clue types to improve the runtime of the actual solve. In practice I did not find this necessary, but in a larger scope this could be useful. I could also save the solutions for each cell in a more accessible manner rather than simply printing the output for each file. However, for the scope of the project, the solution presented is sufficient.