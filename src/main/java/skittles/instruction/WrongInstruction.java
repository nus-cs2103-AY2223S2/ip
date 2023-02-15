package skittles.instruction;

public class WrongInstruction extends Instruction {

    public WrongInstruction() {}

    @Override
    public String getResponse(String input) {
        return "OOPS!!! I'm sorry, but I don't know what that means man!";
    }
}
