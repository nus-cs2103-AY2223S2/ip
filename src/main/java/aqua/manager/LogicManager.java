package aqua.manager;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

import aqua.exception.LoadException;
import aqua.exception.ProcedureException;
import aqua.exception.SyntaxException;
import aqua.logic.CommandLineInput;
import aqua.logic.parser.ArgumentParser;
import aqua.logic.parser.CommandLineInputParser;
import aqua.logic.parser.Parser;
import aqua.storage.Loadable;


/** Manager of the application's logical processes. */
public class LogicManager implements Loadable {
    private final Parser<CommandLineInput> inputParser;
    private final TaskManager taskManager;


    /**
     * Constructs a {@code LogicManager} that uses the default
     * {@code CommandLineInputParser}.
     */
    public LogicManager() {
        this(new CommandLineInputParser(new ArgumentParser()));
    }


    /**
     * Construcs a {@code LogicManager}.
     *
     * @param inputParser - the {@code CommandLineInputParser} to use.
     */
    public LogicManager(Parser<CommandLineInput> inputParser) {
        this.inputParser = inputParser;
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
                parser.parse(line).getService(this).process();
            }
        } catch (SyntaxException | ProcedureException ex) {
            throw new LoadException(ex.getMessage(), ex);
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
    public Parser<CommandLineInput> getInputParser() {
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
}
