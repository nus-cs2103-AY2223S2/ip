package aqua.manager;

import java.util.function.Consumer;
import java.util.function.Supplier;

import aqua.logic.parser.ArgumentParser;
import aqua.logic.parser.CommandLineInputParser;

/** Manager of all managers of the app. */
public class AppManager {
    private final CommandLineInputParser inputParser;;
    private final TaskManager taskManager;
    private final UiManager uiManager;
    
    private boolean isClosed = false;


    public AppManager(Supplier<String> inputSupplier, Consumer<String> outputConsumer) {
        inputParser = new CommandLineInputParser(new ArgumentParser());
        taskManager = new TaskManager();
        uiManager = new UiManager(inputSupplier, outputConsumer);
    }


    public CommandLineInputParser getInputParser() {
        return inputParser;
    }


    public TaskManager getTaskManager() {
        return taskManager;
    }


    public UiManager getUiManager() {
        return uiManager;
    }


    public void setClose(boolean isClosed) {
        this.isClosed = isClosed;
    }


    public boolean isClosed() {
        return isClosed;
    }
}
