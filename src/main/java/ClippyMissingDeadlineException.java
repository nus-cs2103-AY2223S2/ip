public class ClippyMissingDeadlineException extends ClippyException {
    public ClippyMissingDeadlineException() {
        super("deadline cannot be recognised - please specify the deadline as such: \n" +
                "deadline <description> /by <deadline>");
    }
}
