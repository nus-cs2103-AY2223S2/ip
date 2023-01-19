public class DukeException extends Exception {
    public static String LINE = "____________________________________________________________\n";
    public static String OOPS = "☹ OOPS!!! ";

    public DukeException(String errMessage) {
        super(LINE + OOPS + errMessage + "\n" + LINE);
    }
}
