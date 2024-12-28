# Who wants to be a millionare game in Java

"Who Wants to Be a Millionaire" is a single-player Java console application that recreates the excitement of the famous game show. Players answer a series of multiple-choice questions to climb the prize ladder, testing their knowledge and decision-making skills. The game includes lifelines for assistance and offers a thrilling challenge to win the ultimate virtual jackpot.

## Game Logic Summary

Launch Screen:
Players choose from:

Start the Game
View Rules (includes an option to return to the main menu)
Exit
Start Game:

Player enters their name and selects difficulty:
Easy: 9 questions in 3 rounds.
Hard: 15 questions in 3 rounds.
Gameplay:

Questions are randomly selected (no repeats) with 4 answer choices.
Player confirms their final answer after selection.
Incorrect answers result in game loss with no winnings.
Lifelines:

50/50: Removes two wrong options.
Ask the Audience: Simulates audience vote.
Phone a Friend: Simulates advice from a friend.
Lifelines are usable once per game and vary by difficulty.
Easy Mode Rounds:

Round 1: 3 questions, prize up to $1,000 (option to walk away).
Round 2: 3 questions, prize up to $32,000 (option to walk away).
Round 3: 3 questions, prize up to $1,000,000 (must complete).
Hard Mode Rounds:

Round 1: 5 questions, prize up to $1,000 (option to walk away).
Round 2: 5 questions, prize up to $32,000 (option to walk away).
Round 3: 5 questions, prize up to $1,000,000 (must complete).
Validation and Error Handling:

Ensure input correctness at every stage.
Handle edge cases gracefully.
