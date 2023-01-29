package aqua.manager;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.LoadException;
import aqua.exception.ProcedureExecutionException;
import aqua.logic.parser.ArgumentParser;
import aqua.logic.parser.CommandLineInputParser;
import aqua.storage.Loadable;

/** Manager of the application's logical processes. */
public class LogicManager implements Loadable {
    private final CommandLineInputParser inputParser;;
    private final TaskManager taskManager;

    private boolean isClosed = false;


    /**
     * Constructs a LogicManager.
     */
    public LogicManager() {
        inputParser = new CommandLineInputParser(new ArgumentParser());
        taskManager = new TaskManager();
    }


    /**
     * Loades the state of the previous run.
     *
     * @throws LoadException {@inheritDoc}
     */
    @Override
    public void load() throws LoadException {
        Path path = taskManager.getSavePath();
        try (Scanner scanner = new Scanner(path.toFile())) {
            CommandLineInputParser parser = new CommandLineInputParser(new ArgumentParser());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parser.parse(line).getService(this, true).execute();
            }
        } catch (IllegalSyntaxException | ProcedureExecutionException ex) {
            throw new LoadException(ex);
        } catch (FileNotFoundException fnfEx) {
            // no save data just return
            return;
        }
    }


    /**
     * Returns the input parser.
     *
     * @return the input parser.
     */
    public CommandLineInputParser getInputParser() {
        return inputParser;
    }


    /**
     * Returns the task manager.
     *
     * @return the task manager.
     */
    public TaskManager getTaskManager() {
        return taskManager;
    }


    /**
     * Sets the close state of the application as specified.
     *
     * @param isClosed - {@code true} to set the close state to close and
     *      {@code false} otherwise.
     */
    public void setClose(boolean isClosed) {
        this.isClosed = isClosed;
    }


    /**
     * Returns if the application is closed.
     *
     * @return {@code true} if the application is closed and {@code false}
     *      otherwise.
     */
    public boolean isClosed() {
        return isClosed;
    }
}
