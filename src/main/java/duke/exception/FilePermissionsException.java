package duke.exception;

public class FilePermissionsException extends DukeException {

    public FilePermissionsException(String path) {
        super(path);
    }

    @Override
    public String getExceptionName() {
        return "Unable to create or write to file";
    }

}
