package interfaces;

import exceptions.AttackDepletedException;
import exceptions.DefeatedCharacterException;

public interface Fighter {
    void basicAttack(Fighter target) throws AttackDepletedException, DefeatedCharacterException;
    void specialAttack(Fighter target) throws AttackDepletedException, DefeatedCharacterException;
    void superPower(Fighter target) throws AttackDepletedException, DefeatedCharacterException;
    void defend();
    void takeDamage(int damage);
    boolean isAlive();
    void introduce();
}
