package skittles.instruction;

/**
 * This encapsulates all Commands that can be understood by Duke.
 */
public abstract class Instruction {

    public abstract String getResponse(String input);

    public boolean canExit() {
        return false;
    };
}