public class DukeException extends Exception {
    private static String horizontalLine = "________________________________________________________________\n";

    public DukeException(String errMsg) {
        super(horizontalLine + "☹ OOPS!!! " + errMsg + "\n" + horizontalLine);
    }
}