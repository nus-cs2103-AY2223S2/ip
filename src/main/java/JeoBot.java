import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import jeo.command.Command;
import jeo.database.Storage;
import jeo.database.TaskList;
import jeo.exception.JeoException;
import jeo.parser.JeoParser;
import jeo.ui.Ui;

/**
 * Represents the bot which the user may run.
 * @author Goh Jun How
 * @version 0.3
 */
public class JeoBot {
    protected Ui ui;
    protected Storage store;
    protected TaskList taskList;

    /**
     * Creates the bot with the specified path to load tasks.
     * @param path String representing the file path.
     */
    public JeoBot(String path) {
        ui = new Ui();
        store = new Storage(path);
        try {
            taskList = new TaskList(store.load());
        } catch (FileNotFoundException | IllegalStateException e) {
            taskList = new TaskList();
            ui.showLoadingErrorMessage();
        }
    }

    /**
     * Parses and executes commands which the user inputs before returning the output message accordingly.
     * @param input String representing the input message.
     * @return The output message.
     */
    public String run(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            Command command = JeoParser.parseString(input);
            sb.append(command.execute(ui, taskList));
            store.save(taskList.getTaskList());
        } catch (IOException e) {
            sb.append(ui.savingErrorMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            sb.append(ui.invalidCommandMessage());
        } catch (IndexOutOfBoundsException e) {
            sb.append(ui.indexingErrorMessage());
        } catch (DateTimeParseException e) {
            sb.append(ui.dateTimeParsingErrorMessage());
        } catch (JeoException e) {
            sb.append(ui.jeoErrorMessage(e.getMessage()));
        }
        return sb.toString();
    }

    /**
     * Returns the response from JeoBot.
     *
     * @param input The input from the user.
     * @return The response message of JeoBot.
     */
    public String getResponse(String input) {
        return run(input);
    }
}
