package Ava.exceptions;

public class CannotReadFromFile extends AvaException {
    private final String ERROR_PROMPT = " file path not found or Error reading to this File !!";
    private String filePath;

    public CannotReadFromFile(String filePath) {

        this.filePath = filePath;
    }

    @Override
    public String getMessage(){

        return super.SORRY + " " + this.filePath +  " " + ERROR_PROMPT;
    }
}
