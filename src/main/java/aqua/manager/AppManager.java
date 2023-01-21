package aqua.manager;

import aqua.logic.parser.ArgumentParser;
import aqua.logic.parser.CommandLineInputParser;

/** Manager of all managers of the app. */
public class AppManager {
    private final CommandLineInputParser inputParser = new CommandLineInputParser(new ArgumentParser());
    private final TaskManager taskManager = new TaskManager();
    
    private boolean isClosed = false;


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
