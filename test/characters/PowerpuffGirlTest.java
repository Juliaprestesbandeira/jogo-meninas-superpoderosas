package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("PowerpuffGirl")
class PowerpuffGirlTest {

    private Buttercup character;

    @BeforeEach
    void setUp() {
        character = new Buttercup();
    }

    @Test
    @DisplayName("takeDamage should reduce health")
    void takeDamageShouldReduceHealth() {
        character.takeDamage(20);
        assertEquals(80, character.getHealth());
    }

    @Test
    @DisplayName("health should never go below zero")
    void healthShouldNeverGoBelowZero() {
        character.takeDamage(999);
        assertEquals(0, character.getHealth());
    }

    @Test
    @DisplayName("negative damage should be ignored")
    void negativeDamageShouldBeIgnored() {
        character.takeDamage(-10);
        assertEquals(100, character.getHealth());
    }

    @Test
    @DisplayName("zero damage should not change health")
    void zeroDamageShouldNotChangeHealth() {
        character.takeDamage(0);
        assertEquals(100, character.getHealth());
    }

    @Test
    @DisplayName("defense should cut damage in half")
    void defenseShouldCutDamageInHalf() {
        character.defend();
        character.takeDamage(40);
        assertEquals(80, character.getHealth());
    }

    @Test
    @DisplayName("resetDefense should remove defense")
    void resetDefenseShouldRemoveDefense() {
        character.defend();
        character.resetDefense();
        character.takeDamage(40);
        assertEquals(60, character.getHealth());
    }

    @Test
    @DisplayName("character with positive health should be alive")
    void characterWithPositiveHealthShouldBeAlive() {
        assertTrue(character.isAlive());
    }

    @Test
    @DisplayName("character with zero health should not be alive")
    void characterWithZeroHealthShouldNotBeAlive() {
        character.takeDamage(100);
        assertFalse(character.isAlive());
    }

    @Test
    @DisplayName("getName should return the character name")
    void getNameShouldReturnCharacterName() {
        assertEquals("Buttercup", character.getName());
    }

    @Test
    @DisplayName("initial health should be 100")
    void initialHealthShouldBe100() {
        assertEquals(100, character.getHealth());
    }

    @Test
    @DisplayName("initial attack counters should be correct")
    void initialAttackCountersShouldBeCorrect() {
        assertEquals(3, character.basicAttackUses);
        assertEquals(2, character.specialAttackUses);
        assertEquals(1, character.superPowerUses);
    }
}
