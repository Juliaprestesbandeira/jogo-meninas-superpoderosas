package characters;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import interfaces.Fighter;

public class Blossom extends PowerpuffGirl {

    public Blossom() {
        super("Blossom");
    }

    @Override
    public void introduce() {
        System.out.println("BLOSSOM");
        System.out.println("Attack 1: Floral Kick      (20 damage), " + basicAttackUses + " uses");
        System.out.println("Attack 2: Pink Beam        (30 damage), " + specialAttackUses + " uses");
        System.out.println("Super:    Pink Storm       (50 damage), " + superPowerUses + " use");
    }

    @Override
    public void basicAttack(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(basicAttackUses, "Floral Kick");
        basicAttackUses--;
        System.out.println("Blossom uses FLORAL KICK.");
        target.takeDamage(20);
    }

    @Override
    public void specialAttack(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(specialAttackUses, "Pink Beam");
        specialAttackUses--;
        System.out.println("Blossom uses PINK BEAM.");
        target.takeDamage(30);
    }

    @Override
    public void superPower(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(superPowerUses, "Pink Storm");
        superPowerUses--;
        System.out.println("PINK STORM.");
        target.takeDamage(50);
    }
}
