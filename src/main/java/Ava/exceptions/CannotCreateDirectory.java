package Ava.exceptions;

public class CannotCreateDirectory extends AvaException {
    private final String ERROR_PROMPT = "Directory cannot be created !!";
    private String dirName;

    public CannotCreateDirectory(String dirName) {

        this.dirName = dirName;
    }

    @Override
    public String getMessage(){

        return super.SORRY + " " + this.dirName +  " " + ERROR_PROMPT;
    }
}
