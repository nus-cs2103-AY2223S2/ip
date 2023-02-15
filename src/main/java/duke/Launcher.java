package duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
//@@author se-education.org
//Reused from https://se-education.org/guides/tutorials/javaFxPart3.html
//with minor modifications
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}