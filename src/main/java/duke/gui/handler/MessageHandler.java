package duke.gui.handler;

/**
 * MessageHandler is a functional interface that is used to handle an incoming message from the GUI.
 */
@FunctionalInterface
public interface MessageHandler {
    String handle(String command);
}
