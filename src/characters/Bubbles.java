package characters;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import interfaces.Fighter;

public class Bubbles extends PowerpuffGirl {

    public Bubbles() {
        super("Bubbles");
    }

    @Override
    public void introduce() {
        System.out.println("BUBBLES");
        System.out.println("Attack 1: Wild Punch       (20 damage), " + basicAttackUses + " uses");
        System.out.println("Attack 2: Blue Burst       (30 damage), " + specialAttackUses + " uses");
        System.out.println("Super:    Wild Fury        (50 damage), " + superPowerUses + " use");
    }

    @Override
    public void basicAttack(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(basicAttackUses, "Wild Punch");
        basicAttackUses--;
        System.out.println("Bubbles uses WILD PUNCH.");
        target.takeDamage(20);
    }

    @Override
    public void specialAttack(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(specialAttackUses, "Blue Burst");
        specialAttackUses--;
        System.out.println("Bubbles uses BLUE BURST.");
        target.takeDamage(30);
    }

    @Override
    public void superPower(Fighter target)
            throws AttackDepletedException, DefeatedCharacterException {

        validateAttack(superPowerUses, "Wild Fury");
        superPowerUses--;
        System.out.println("WILD FURY.");
        target.takeDamage(50);
    }
}
