package Ava.exceptions;


public class CommandNotFound extends AvaException {
    private final String ERROR_PROMPT = "Command Not Complete or Possible !!";
    private String input;

    public CommandNotFound(String input) {
        this.input = input;
    }
    @Override
    public String getMessage(){
        return super.SORRY + " " + this.input +  " " + ERROR_PROMPT;
    }
}
