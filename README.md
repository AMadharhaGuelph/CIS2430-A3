# Project Title

This is a tic tac toe game with a GUI. You have options to play numerical tic tac toe and classic tic tac toe with a GUI. Also, you can play a text based classic tic tac toe game. 

## Description

For numerical tic tac toe, the player with the odd number goes first. You must get three number in a row that add up to 15, the player who places that last number to get the sum 15 is the winner.\
For classic tic tac toe, the player X goes first and the first to get three in a row wins.\
In both games, you can save the game or load a previous save file. 

## Getting Started

### Dependencies

* Java 11
* Gradle 7.3/7.4.2/7.5.1

### Executing program

1. Build the program using Gradle.
```
gradle build
```
```
BUILD SUCCESSFUL in 1s
3 actionable tasks: 3 up-to-date
```

2. Execute the gradle run command.
```
gradle run
```
```
> Task :run
To run the jar file:
 java -jar build/libs/A3.jar
To run from class files:
 java -cp build/classes/java/main game.GameUI
 java -cp build/classes/java/main game.TextUI
```
3. Copy and paste the command for what you want to play. If you want to play using the jar, then type the first command shown above. If you want to use the class files, then you can choose if you want the GUI or text UI.
```
java -jar build/libs/A3.jar
java -cp build/classes/java/main game.GameUI
java -cp build/classes/java/main game.TextUI
```

And you're done! You can now play tic tac toe games!.

## Limitations

When loading and saving the game, the pop up window may not be in the correct directory (assets within the A3 project folder). I found the reason for this is based on what Operating System you are using, if you are on windows then it should open in the correct directory. However, on MacOS it seems to open elsewhere. If a correct file is chosen, it does not really matter where the file is located.
## Author Information

* NAME: Ankush Madharha
* EMAIL: amadharh@uoguelph.ca
* ID: 1172859



