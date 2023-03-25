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
    private final Parser parser;

    public BrotherBot() {
        this.storage = new Storage("data.txt");
        this.tasks = storage.getTasks();
        this.gui = new Gui(this);
        this.parser = new Parser();
    }

    @Override
    public void start(Stage stage) {
        gui.initialise(stage);
    }

    public String getResponse(String input) {
        String output;
        try {
            Command c = this.parser.parse(input, this.tasks);
            output = c.execute(this.tasks);
            storage.save(c);
            return output;
        } catch (BroException e) {
            output = e.getMessage();
            return output;
        }
    }


    public String welcome() {
            return "Welcome to Brother Bot - your one-stop Personal Task Planner with a very 'bro' personality!\n" +
                    "Hello my brother, what can I do for you mi amigo...\n" + this.storage.load();
    }
}

