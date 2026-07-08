package characters;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Bubbles")
class BubblesTest {

    private Bubbles bubbles;
    private Blossom target;

    @BeforeEach
    void setUp() {
        bubbles = new Bubbles();
        target = new Blossom();
    }

    @Test
    @DisplayName("Wild Punch should deal 20 damage")
    void basicAttackShouldDeal20Damage() throws Exception {
        bubbles.basicAttack(target);
        assertEquals(80, target.getHealth());
    }

    @Test
    @DisplayName("Wild Punch should throw AttackDepletedException with no uses left")
    void basicAttackWithoutUsesShouldThrowException() throws Exception {
        bubbles.basicAttack(target);
        bubbles.basicAttack(target);
        bubbles.basicAttack(target);

        assertThrows(AttackDepletedException.class, () -> bubbles.basicAttack(target));
    }

    @Test
    @DisplayName("Blue Burst should deal 30 damage")
    void specialAttackShouldDeal30Damage() throws Exception {
        bubbles.specialAttack(target);
        assertEquals(70, target.getHealth());
    }

    @Test
    @DisplayName("Blue Burst should throw AttackDepletedException with no uses left")
    void specialAttackWithoutUsesShouldThrowException() throws Exception {
        bubbles.specialAttack(target);
        bubbles.specialAttack(target);

        assertThrows(AttackDepletedException.class, () -> bubbles.specialAttack(target));
    }

    @Test
    @DisplayName("Wild Fury should deal 50 damage")
    void superPowerShouldDeal50Damage() throws Exception {
        bubbles.superPower(target);
        assertEquals(50, target.getHealth());
    }

    @Test
    @DisplayName("Wild Fury should throw AttackDepletedException after one use")
    void superPowerWithoutUsesShouldThrowException() throws Exception {
        bubbles.superPower(target);

        assertThrows(AttackDepletedException.class, () -> bubbles.superPower(target));
    }

    @Test
    @DisplayName("defeated Bubbles should throw DefeatedCharacterException")
    void defeatedCharacterShouldThrowException() {
        bubbles.takeDamage(100);

        assertThrows(DefeatedCharacterException.class, () -> bubbles.specialAttack(target));
    }
}
