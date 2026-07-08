package exceptions;

public class DefeatedCharacterException extends Exception {

    private final String characterName;

    public DefeatedCharacterException(String characterName) {
        super("The character '" + characterName + "' has already been defeated and cannot act.");
        this.characterName = characterName;
    }

    public String getCharacterName() {
        return characterName;
    }
}
