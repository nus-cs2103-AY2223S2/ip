public class ClippyInvalidEventException {
    public ClippyInvalidEventException() {
        super("cannot recognise event start and end time - please specify it as such: \n"
                "event <description> /from <start> /to <end>");
    }
}
