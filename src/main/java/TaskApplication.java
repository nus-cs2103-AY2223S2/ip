import java.util.ArrayList;
import java.util.List;

/**
 * TaskApplication is a model class that has the basic functionalities of a checklist.
 * It helps to keep track of everyday tasks or events.
 */
public class TaskApplication {
    private List<Task> userTasks;
    public TaskApplication() {
         this.userTasks = new ArrayList<>();
    }

    public void markTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("There are only %d tasks. There is no task" +
                            "with index %d", this.userTasks.size(), index)
            );
        }
        this.userTasks.get(index).setIsDone(true);
    }

    public void unmarkTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("There are only %d tasks. There is no task" +
                            "with index %d", this.userTasks.size(), index)
            );
        }
        this.userTasks.get(index).setIsDone(false);
    }

    public void addTask(Task t) {
        this.userTasks.add(t);
    }

    public void printAllTasks() {
        int i = 1;
        for (Task s: this.userTasks) {
            System.out.printf("\t%d.%s\n", i, s);
            i++;
        }
    }

    public Task getTask(int index) {
        return this.userTasks.get(index);
    }

    public int getNoOfTasks() {
        return this.userTasks.size();
    }

}
