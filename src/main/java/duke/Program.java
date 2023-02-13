package duke;

import java.io.IOException;

import javafx.application.Application;

public final class Program {
    public final static void main(String[] vargs) throws IOException {
        // JavaFX gets very sad if our application class is also our main class
        // so this is a workaround
        Application.launch(App.class);
    }
}
