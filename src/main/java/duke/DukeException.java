package duke;

/**
 * This class handles exceptions
 */
public class DukeException extends Exception{

    private String msg;

    public DukeException(String m) {
        super(m);
        this.msg = m;
    }

    public String getMsg() {
        return msg;
    }

}
