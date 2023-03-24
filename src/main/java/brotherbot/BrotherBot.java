package brotherbot;

import brotherbot.commands.Command;
import brotherbot.exceptions.BroException;
import brotherbot.parser.Parser;
import brotherbot.storage.Storage;
import brotherbot.storage.TaskList;
import brotherbot.ui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

public class BrotherBot extends Application {

    private final Storage storage;
    private final TaskList tasks;
    private final Gui gui;

    public BrotherBot() {
        this.storage = new Storage("data.txt");
        this.tasks = storage.getTasks();
        this.gui = new Gui(this);
    }


    @Override
    public void start(Stage stage) {
        gui.start(stage);
    }

    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input, this.tasks);
            output = c.execute(this.tasks);
            storage.save(c);
            return output;
        } catch (BroException e) {
            output = e.getMessage();
            return output;
        }
    }

    public String loadHistory() {
        return this.storage.load();
    }
}

