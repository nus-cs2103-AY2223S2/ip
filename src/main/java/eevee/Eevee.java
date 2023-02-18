package eevee;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Eevee {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Eevee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EeveeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    protected String getResponse(String input) {
        try {
            return Parser.handleInput(input, ui, tasks, storage);
        } catch (EeveeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Eevee... Something went wrong while handling this task.";
        } catch (IOException e) {
            return "Eevee :( Something went wrong";
        } catch (DateTimeParseException e) {
            return "Format of date given is wrong. Please ensure format of date is yyyy-MM-dd HH:mm " +
                    "(e.g. 2022-03-30 14:30)";
        }
    }

}
