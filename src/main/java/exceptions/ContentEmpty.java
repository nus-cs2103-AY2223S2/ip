package exceptions;

public class ContentEmpty extends DukeException {
    public ContentEmpty(String content) {
        super("Try to type something in your "+ content +".");
    }
}