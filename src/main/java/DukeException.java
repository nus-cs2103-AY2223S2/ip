public class DukeException extends Exception{
    private static String sep = "-----------------------------------------\n";

    public DukeException (String message) {
        super("\n" + sep + message + "\n" + sep);
    }
}
