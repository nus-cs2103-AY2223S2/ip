package skittles.instruction;

public class ExitInstruction extends Instruction {

    public ExitInstruction() {}

    @Override
    public boolean canExit() {
        return true;
    }

    @Override
    public String getResponse(String input) {
        String exitText = "Bye man hope to see you again soon!";
        return exitText;
    }
}
