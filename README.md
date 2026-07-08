# Powerpuff Girls Battle Game

A small Java terminal game where two players choose a Powerpuff Girl and fight in a 1v1 battle. Each match lasts up to three rounds. Players can attack, use a special move, spend a one-time super power, or defend to reduce incoming damage.

## Characters

- Blossom: uses Floral Kick, Pink Beam, and Pink Storm.
- Bubbles: uses Wild Punch, Blue Burst, and Wild Fury.
- Buttercup: uses Fireball, Fire Spear, and Total Inferno.

## Rules

- Each character starts with 100 health.
- A basic attack deals 20 damage and can be used 3 times.
- A special attack deals 30 damage and can be used 2 times.
- A super power deals 50 damage and can be used 1 time.
- Defending cuts the next received damage in half.
- The game ends after 3 rounds or when a character is defeated.
- If nobody is defeated, the character with more health wins.

## Project Structure

```text
src/
  characters/    Character classes and shared battle behavior
  exceptions/    Custom exceptions for invalid moves and game state
  game/          Main game loop and menu flow
  interfaces/    Fighter contract used by every character
test/
  characters/    Unit tests for character behavior
  game/          Unit tests for game helper methods
```

## How to Run

Compile the source files:

```bash
javac -d out $(find src -name "*.java")
```

Start the game:

```bash
java -cp out game.Game
```

On Windows PowerShell, compile with:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

Then run:

```powershell
java -cp out game.Game
```

## Notes

The code uses custom exceptions to handle invalid menu options, depleted attacks, and attempts to attack with a defeated character. The game keeps the terminal flow simple while still separating the main battle rules from the character classes.
