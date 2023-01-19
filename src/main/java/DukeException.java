public class DukeException extends Exception {
    public static String LINE = "____________________________________________________________\n";
    public static String OOPS = "☹ OOPS!!! ";

    public DukeException() {
        super(LINE + OOPS + "I'm sorry, but I don't know what that means :-(\n" + LINE);
    }

    public DukeException(String errMessage) {
        super(LINE + OOPS + errMessage + "\n" + LINE);
    }
}
