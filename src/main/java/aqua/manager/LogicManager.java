package aqua.manager;

import aqua.logic.parser.ArgumentParser;
import aqua.logic.parser.CommandLineInputParser;

/** Application logic manager */
public class LogicManager {
    private final CommandLineInputParser inputParser;;
    private final TaskManager taskManager;
    
    private boolean isClosed = false;


    public LogicManager() {
        inputParser = new CommandLineInputParser(new ArgumentParser());
        taskManager = new TaskManager();
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
