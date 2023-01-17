package manager;


/** Manager of all managers of the app. */
public class MainManager {
    private final TaskManager taskManager;


    public MainManager() {
        this.taskManager = new TaskManager();
    }


    public TaskManager getTaskManager() {
        return taskManager;
    }
}
