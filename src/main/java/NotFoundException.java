public class NotFoundException extends DukeException {
    public NotFoundException(String source, String reason, Throwable err) {
        super(String.format("Sorry, item does not exist in %s. %s", source, reason), err);
    }
}
