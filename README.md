# Phoenix Java Game Starter

This folder contains two tiny Java projects:

1. `JavaBasics`
   - A beginner console program.
   - Good for learning variables, printing, methods, loops, and simple logic.

2. `TileGame`
   - A simple Java Swing tile game foundation.
   - Includes a game window, tile map, player movement, collision, and a goal tile.

## Requirements

Java JDK installed.

Check with:

```bash
java -version
javac -version
```

## Run JavaBasics

```bash
cd JavaBasics
chmod +x run.sh
./run.sh
```

## Run TileGame

```bash
cd TileGame
chmod +x run.sh
./run.sh
```

## Open in VS Code

From inside this folder:

```bash
code .
```

## Good first edits for a 12-year-old

In `TileGame/src/Main.java`, try changing:

- `playerSpeed`
- tile colors
- the map numbers
- the window title
- the player color
- the message at the bottom

Map key:

- `0` = grass/floor
- `1` = wall
- `2` = goal tile
