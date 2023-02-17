package Ava.exceptions;

public class CannotWriteToFile extends AvaException {
    private final String ERROR_PROMPT = " file path not found or Error writing to this File !!";
    private String filePath;

    public CannotWriteToFile(String filePath) {

        this.filePath = filePath;
    }

    @Override
    public String getMessage(){

        return super.SORRY + " " + this.filePath +  " " + ERROR_PROMPT;
    }
}
