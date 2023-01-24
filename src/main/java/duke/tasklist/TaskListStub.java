package duke.tasklist;

/** Represents a stub for TaskList to remove dependencies during unit testing. */
public class TaskListStub extends TaskList {

    public TaskListStub() {
    }

    @Override
    public int getSizeOfTaskList() {
        return 3;
    }

}
