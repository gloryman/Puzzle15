# Puzzle15 

Java Implementation of classical game 4 x 4 with one empty cell.  https://en.wikipedia.org/wiki/15_puzzle


The project split into two main packages. 
* `Game` - puzzle15 logic
* `Console` - terminal version representation and user keyboard input

In a future as optional implementations could be added GUI implementation.

Requires Java 8 version or higher.
`Gradle` used as a project build tool.

As dependencies used Lombok library. You should be ready to install `Lombok` plugin and enable `Annotation Processing` option in case you import Project into Intellij Idea IDE. 

The current version contains only console implementation of the game. Entry point main class at `puzzle15.console.App` contains a static main method. 
Main method reads user command-line arguments or default values using `picocli` lib. 
After initial process `ConsoleLoop.run()` will be executed. At this point will be initialized all primary orchestration. And Pzzle15 `Game` logic will be called with input events.

`jansi` library used for board render to support various platforms. Game must be playable on windows as well.

# Control keys.
* Q / double ESC- quit
* w,a,s,d - cursor movements
* VI movement keys keys - alternative cursor movement

(For a multiplatform purpose, I had a problem to use arrow keys.)

# Build Project:

```
./gradlew clean build fatJar
```

You should be able to see ready to execute jar file `./build/libs/Puzzle15-all.jar`  on build success.

Execute Jar:
```
java -jar ./build/libs/Puzzle15-all.jar
```

Will start default 4x4 game on a cleared console.

Additional options could be viewed with cmd options -h or --help:
```
java -jar ./build/libs/Puzzle15-all.jar
Usage: Puzzle15 [-chV] [--board-preset=<boardPreset>]
                [--draw-column=<boardStartColumn>] [--draw-row=<boardStartRow>]
                [-s=<boardHeight>] [-w=<boardWidth>]
Puzzle15
      --board-preset=<boardPreset>
                          Default: RANDOM.  Options: RANDOM, REVERSE, SIMPLE.
  -c, --no-clear-screen   Do not clear screen on startup: false
      --draw-column=<boardStartColumn>
                          Draw board at column: 8
      --draw-row=<boardStartRow>
                          Draw board at row: 4
  -h, --help              Show this help message and exit.
  -s, --boardHeight=<boardHeight>
                          Puzzle15 board height: 4
  -V, --version           Print version information and exit.
  -w, --boardWidth=<boardWidth>
                          Puzzle15 board width: 4
``` 

Additional options allows start game with tweaks like:
* Different board sizes length/height
* Render board without clearing screen
* Predefined board cells settings. Like SIMPLE - when game is almost solved

