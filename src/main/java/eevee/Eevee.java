package eevee;

import eevee.exception.EeveeException;
import eevee.exception.TaskNoContentException;

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
        } catch (TaskNoContentException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Eevee... Something went wrong while handling this task.";
        } catch (IOException e) {
            return "Eevee :( Something went wrong when saving task to file.";
        } catch (DateTimeParseException e) {
            return "EEVEE >:( Format of date given is wrong. Format of date should be yyyy-MM-dd HH:mm " +
                    "(e.g. 2022-03-30 14:30)";
        }
    }

}
