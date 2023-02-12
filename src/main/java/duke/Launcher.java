package duke;

import duke.Duke;
import duke.DukeException;
import duke.Main;
import javafx.application.Application;

import java.io.IOException;

/**
 * A constructor to run the GUI
 */
public class Launcher{
    public static void main(String[] args) throws DukeException {
        Application.launch(Main.class,args);

    }
}
