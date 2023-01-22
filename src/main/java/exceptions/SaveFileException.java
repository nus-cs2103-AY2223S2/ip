package exceptions;

public class SaveFileException  extends DukeException {
    public SaveFileException(String file,String msg) {
        super("☹ OOPS!!! Fail to save file: " + file + "\n"+msg);
    }
}