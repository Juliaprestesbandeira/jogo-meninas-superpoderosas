package characters;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Buttercup")
class ButtercupTest {

    private Buttercup buttercup;
    private Blossom target;

    @BeforeEach
    void setUp() {
        buttercup = new Buttercup();
        target = new Blossom();
    }

    @Test
    @DisplayName("Fireball should deal 20 damage")
    void basicAttackShouldDeal20Damage() throws Exception {
        buttercup.basicAttack(target);
        assertEquals(80, target.getHealth());
    }

    @Test
    @DisplayName("Fireball should decrement basicAttackUses")
    void basicAttackShouldDecrementUses() throws Exception {
        buttercup.basicAttack(target);
        assertEquals(2, buttercup.basicAttackUses);
    }

    @Test
    @DisplayName("Fireball should throw AttackDepletedException with no uses left")
    void basicAttackWithoutUsesShouldThrowAttackDepletedException() throws Exception {
        buttercup.basicAttack(target);
        buttercup.basicAttack(target);
        buttercup.basicAttack(target);

        assertThrows(AttackDepletedException.class, () -> buttercup.basicAttack(target));
    }

    @Test
    @DisplayName("Fire Spear should deal 30 damage")
    void specialAttackShouldDeal30Damage() throws Exception {
        buttercup.specialAttack(target);
        assertEquals(70, target.getHealth());
    }

    @Test
    @DisplayName("Fire Spear should throw AttackDepletedException with no uses left")
    void specialAttackWithoutUsesShouldThrowAttackDepletedException() throws Exception {
        buttercup.specialAttack(target);
        buttercup.specialAttack(target);

        assertThrows(AttackDepletedException.class, () -> buttercup.specialAttack(target));
    }

    @Test
    @DisplayName("Total Inferno should deal 50 damage")
    void superPowerShouldDeal50Damage() throws Exception {
        buttercup.superPower(target);
        assertEquals(50, target.getHealth());
    }

    @Test
    @DisplayName("Total Inferno should throw AttackDepletedException after one use")
    void superPowerWithoutUsesShouldThrowAttackDepletedException() throws Exception {
        buttercup.superPower(target);

        assertThrows(AttackDepletedException.class, () -> buttercup.superPower(target));
    }

    @Test
    @DisplayName("defeated character should throw DefeatedCharacterException when attacking")
    void defeatedCharacterShouldThrowDefeatedCharacterException() {
        buttercup.takeDamage(100);
        assertFalse(buttercup.isAlive());

        assertThrows(DefeatedCharacterException.class, () -> buttercup.basicAttack(target));
    }
}
