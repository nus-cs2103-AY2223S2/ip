package Exceptions;

public class CommandNotFoundException extends AvaException {
    private final String ERROR_PROMPT = "Command Not Complete or Possible !!";
    private String input;

    public CommandNotFoundException(String input) {
        this.input = input;
    }
    @Override
    public String getMessage(){
        return super.SORRY + " " + this.input +  " " + ERROR_PROMPT;
    }
}
