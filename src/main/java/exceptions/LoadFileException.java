package exceptions;

public class LoadFileException  extends DukeException {
    public LoadFileException(String file,String msg) {
        super("☹ OOPS!!! Fail to load file: " + file + "\n"+msg);
    }
}