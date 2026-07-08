package characters;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import interfaces.Fighter;

public class Buttercup extends PowerpuffGirl {

    public Buttercup() {
        super("Buttercup");
    }

    @Override
    public void introduce() {
        System.out.println("BUTTERCUP");
        System.out.println("Attack 1: Fireball         (20 damage), " + basicAttackUses + " uses");
        System.out.println("Attack 2: Fire Spear       (30 damage), " + specialAttackUses + " uses");
        System.out.println("Super:    Total Inferno    (50 damage), " + superPowerUses + " use");
    }

    @Override
    public void basicAttack(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(basicAttackUses, "Fireball");
        basicAttackUses--;
        System.out.println("Buttercup uses FIREBALL.");
        target.takeDamage(20);
    }

    @Override
    public void specialAttack(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(specialAttackUses, "Fire Spear");
        specialAttackUses--;
        System.out.println("Buttercup uses FIRE SPEAR.");
        target.takeDamage(30);
    }

    @Override
    public void superPower(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(superPowerUses, "Total Inferno");
        superPowerUses--;
        System.out.println("TOTAL INFERNO.");
        target.takeDamage(50);
    }
}
