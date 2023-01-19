package Exceptions;

public class NonExistentTask extends AvaException {
    private final String ERROR_PROMPT = "Cannot be done, you have not made a task like that yet!!";
    private String input;

    public NonExistentTask(String input) {
        this.input = input;
    }

    @Override
    public String getMessage(){
        return super.SORRY + " " + this.input +  " " + ERROR_PROMPT;
    }
}
