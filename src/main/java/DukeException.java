public class DukeException extends Exception {
    public DukeException(String msg) {
        super( "!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!\n"
                + msg + "\n"
                +  "!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!\n");
    }
}
