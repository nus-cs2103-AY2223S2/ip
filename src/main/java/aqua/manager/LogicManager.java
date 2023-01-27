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

/** Application logic manager */
public class LogicManager implements Loadable {
    private final CommandLineInputParser inputParser;;
    private final TaskManager taskManager;
    
    private boolean isClosed = false;


    public LogicManager() {
        inputParser = new CommandLineInputParser(new ArgumentParser());
        taskManager = new TaskManager();
    }


    @Override
    public void load() throws LoadException {
        Path path = taskManager.getSavePath();
        try (Scanner scanner = new Scanner(path.toFile())) {
            CommandLineInputParser parser = new CommandLineInputParser(new ArgumentParser());
            while (scanner.hasNextLine()) {
                parser.parse(scanner.nextLine()).getDispatcher(this).dispatch();
            }
        } catch (IllegalSyntaxException | ProcedureExecutionException ex) {
            throw new LoadException(ex);
        } catch (FileNotFoundException fnfEx) {
            // no save data just return
            return;
        }
    }


    public CommandLineInputParser getInputParser() {
        return inputParser;
    }


    public TaskManager getTaskManager() {
        return taskManager;
    }


    public void setClose(boolean isClosed) {
        this.isClosed = isClosed;
    }


    public boolean isClosed() {
        return isClosed;
    }
}
