package characters;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;
import interfaces.Fighter;

public abstract class PowerpuffGirl implements Fighter {

    private final String name;
    private int health;
    private boolean defending;
    public int basicAttackUses = 3;
    public int specialAttackUses = 2;
    public int superPowerUses = 1;

    public PowerpuffGirl(String name) {
        this.name = name;
        this.health = 100;
        this.defending = false;
    }

    @Override
    public void takeDamage(int damage) {
        if (damage < 0) {
            System.out.println("Negative damage ignored for " + name);
            return;
        }

        if (defending) {
            damage = damage / 2;
            System.out.println("Damage reduced to " + damage + ".");
        }

        health -= damage;
        if (health < 0) {
            health = 0;
        }

        System.out.println(name + " now has " + health + "/100 health.");
    }

    @Override
    public void defend() {
        if (!isAlive()) {
            System.out.println(name + " has already been defeated and cannot defend.");
            return;
        }
        defending = true;
        System.out.println(name + " is defending against the next attack.");
    }

    public void resetDefense() {
        defending = false;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    protected void validateAttack(int uses, String attackName)
            throws DefeatedCharacterException, AttackDepletedException {

        if (!isAlive()) {
            throw new DefeatedCharacterException(name);
        }
        if (uses <= 0) {
            throw new AttackDepletedException(name, attackName);
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public abstract void introduce();
}
