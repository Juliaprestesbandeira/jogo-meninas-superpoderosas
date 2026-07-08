package game;

import characters.Blossom;
import characters.Bubbles;
import characters.Buttercup;
import characters.PowerpuffGirl;
import exceptions.InvalidOptionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Game")
class GameTest {

    @Test
    @DisplayName("Option 1 should create Blossom")
    void createCharacterOption1ShouldCreateBlossom() throws InvalidOptionException {
        PowerpuffGirl character = Game.createCharacter(1);
        assertInstanceOf(Blossom.class, character, "Option 1 should create Blossom");
    }

    @Test
    @DisplayName("Option 2 should create Bubbles")
    void createCharacterOption2ShouldCreateBubbles() throws InvalidOptionException {
        PowerpuffGirl character = Game.createCharacter(2);
        assertInstanceOf(Bubbles.class, character, "Option 2 should create Bubbles");
    }

    @Test
    @DisplayName("Option 3 should create Buttercup")
    void createCharacterOption3ShouldCreateButtercup() throws InvalidOptionException {
        PowerpuffGirl character = Game.createCharacter(3);
        assertInstanceOf(Buttercup.class, character, "Option 3 should create Buttercup");
    }

    @Test
    @DisplayName("Option 0 should throw InvalidOptionException")
    void createCharacterOption0ShouldThrowInvalidOptionException() {
        assertThrows(InvalidOptionException.class, () -> Game.createCharacter(0));
    }

    @Test
    @DisplayName("Option 4 should throw InvalidOptionException")
    void createCharacterOption4ShouldThrowInvalidOptionException() {
        assertThrows(InvalidOptionException.class, () -> Game.createCharacter(4));
    }

    @Test
    @DisplayName("Negative option should throw InvalidOptionException")
    void createCharacterNegativeOptionShouldThrowInvalidOptionException() {
        assertThrows(InvalidOptionException.class, () -> Game.createCharacter(-99));
    }

    @Test
    @DisplayName("execute should not propagate a depleted attack")
    void executeWithDepletedAttackShouldNotPropagate() throws Exception {
        Buttercup attacker = new Buttercup();
        Blossom target = new Blossom();

        attacker.basicAttack(target);
        attacker.basicAttack(target);
        attacker.basicAttack(target);

        assertDoesNotThrow(() -> Game.execute(attacker, target, 1));
    }

    @Test
    @DisplayName("execute with special attack should reduce target health by 30")
    void executeSpecialAttackShouldReduceHealth() {
        Blossom attacker = new Blossom();
        Buttercup target = new Buttercup();

        Game.execute(attacker, target, 2);
        assertEquals(70, target.getHealth());
    }

    @Test
    @DisplayName("execute with super power should reduce target health by 50")
    void executeSuperPowerShouldReduceHealth() {
        Bubbles attacker = new Bubbles();
        Buttercup target = new Buttercup();

        Game.execute(attacker, target, 3);
        assertEquals(50, target.getHealth());
    }
}
