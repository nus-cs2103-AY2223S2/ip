package aqua.manager;

/** Manager of all managers of the app. */
public class AppManager {
    private final TaskManager taskManager;


    public AppManager() {
        this.taskManager = new TaskManager();
    }


    public TaskManager getTaskManager() {
        return taskManager;
    }
}
