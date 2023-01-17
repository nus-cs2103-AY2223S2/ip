public class NotFoundException extends DukeException {
    public NotFoundException(String source, String reason, Throwable err) {
        super(String.format("Haiya nothing here lah %s. %s", source, reason), err);
    }
}
