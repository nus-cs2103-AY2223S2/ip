package aqua.manager;

/** Manager of all managers of the app. */
public class AppManager {
    private final TaskManager taskManager;
    
    private boolean isClosed = false;


    public AppManager() {
        this.taskManager = new TaskManager();
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
