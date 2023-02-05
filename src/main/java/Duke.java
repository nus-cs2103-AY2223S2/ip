import duke.Storage;
import duke.gui.Ui;
import duke.TaskList;
import duke.Parser;
import duke.gui.Start;

import javafx.application.Application;

public class Duke{
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public static void main(String[] args){
        Application.launch(Start.class);
    }

}
