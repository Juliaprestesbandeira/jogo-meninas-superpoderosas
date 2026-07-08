package characters;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Blossom")
class BlossomTest {

    private Blossom blossom;
    private Buttercup target;

    @BeforeEach
    void setUp() {
        blossom = new Blossom();
        target = new Buttercup();
    }

    @Test
    @DisplayName("Floral Kick should deal 20 damage")
    void basicAttackShouldDeal20Damage() throws Exception {
        blossom.basicAttack(target);
        assertEquals(80, target.getHealth());
    }

    @Test
    @DisplayName("Floral Kick should decrement basicAttackUses")
    void basicAttackShouldDecrementUses() throws Exception {
        blossom.basicAttack(target);
        assertEquals(2, blossom.basicAttackUses);
    }

    @Test
    @DisplayName("Floral Kick should throw AttackDepletedException with no uses left")
    void basicAttackWithoutUsesShouldThrowException() throws Exception {
        blossom.basicAttack(target);
        blossom.basicAttack(target);
        blossom.basicAttack(target);

        assertThrows(AttackDepletedException.class, () -> blossom.basicAttack(target));
    }

    @Test
    @DisplayName("Pink Beam should deal 30 damage")
    void specialAttackShouldDeal30Damage() throws Exception {
        blossom.specialAttack(target);
        assertEquals(70, target.getHealth());
    }

    @Test
    @DisplayName("Pink Beam should throw AttackDepletedException with no uses left")
    void specialAttackWithoutUsesShouldThrowException() throws Exception {
        blossom.specialAttack(target);
        blossom.specialAttack(target);

        assertThrows(AttackDepletedException.class, () -> blossom.specialAttack(target));
    }

    @Test
    @DisplayName("Pink Storm should deal 50 damage")
    void superPowerShouldDeal50Damage() throws Exception {
        blossom.superPower(target);
        assertEquals(50, target.getHealth());
    }

    @Test
    @DisplayName("Pink Storm should throw AttackDepletedException after one use")
    void superPowerWithoutUsesShouldThrowException() throws Exception {
        blossom.superPower(target);

        assertThrows(AttackDepletedException.class, () -> blossom.superPower(target));
    }

    @Test
    @DisplayName("defeated Blossom should throw DefeatedCharacterException")
    void defeatedCharacterShouldThrowException() {
        blossom.takeDamage(100);

        assertThrows(DefeatedCharacterException.class, () -> blossom.basicAttack(target));
    }
}
