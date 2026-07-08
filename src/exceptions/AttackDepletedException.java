package exceptions;

public class AttackDepletedException extends Exception {

    private final String attackName;
    private final String characterName;

    public AttackDepletedException(String characterName, String attackName) {
        super("The attack '" + attackName + "' from " + characterName + " has no uses left.");
        this.attackName = attackName;
        this.characterName = characterName;
    }

    public String getAttackName() {
        return attackName;
    }

    public String getCharacterName() {
        return characterName;
    }
}
