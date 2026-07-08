package exceptions;

public class InvalidOptionException extends Exception {

    private final int enteredOption;
    private final int minimumValid;
    private final int maximumValid;

    public InvalidOptionException(int enteredOption, int minimumValid, int maximumValid) {
        super("Invalid option: " + enteredOption
                + ". Please choose between " + minimumValid + " and " + maximumValid + ".");
        this.enteredOption = enteredOption;
        this.minimumValid = minimumValid;
        this.maximumValid = maximumValid;
    }

    public int getEnteredOption() {
        return enteredOption;
    }

    public int getMinimumValid() {
        return minimumValid;
    }

    public int getMaximumValid() {
        return maximumValid;
    }
}
