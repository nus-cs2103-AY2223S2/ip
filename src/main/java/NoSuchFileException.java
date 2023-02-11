public class NoSuchFileException extends DukeException {

    public NoSuchFileException(String input) {
        super("No such file name" + input +  "exists! Sorry!");
    }

    @Override
    public String getExceptionType() {
        return "No such file";
    }
}
