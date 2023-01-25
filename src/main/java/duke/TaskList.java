package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public TaskList addTask(Storage dataStorage, Task newTask) {
        ArrayList<Task> updatedList = this.taskList;
        updatedList.add(newTask);
        dataStorage.writeFile(updatedList);
        return new TaskList(updatedList);
    }

    public TaskList deleteTask(Storage dataStorage, int index) {
        ArrayList<Task> updatedList = this.taskList;
        updatedList.remove(index);
        dataStorage.writeFile(updatedList);
        return new TaskList(updatedList);
    }

    public TaskList markTask(Storage dataStorage, int index) throws DukeException {
        ArrayList<Task> updatedList = this.taskList;
        Task unmarkedTask = updatedList.get(index);
        Task markedTask = unmarkedTask.markTask();
        updatedList.set(index, markedTask);
        dataStorage.writeFile(updatedList);
        return new TaskList(updatedList);
    }

    public TaskList unmarkTask(Storage dataStorage, int index) throws DukeException {
        ArrayList<Task> updatedList = this.taskList;
        Task markedTask = updatedList.get(index);
        Task unmarkedTask = markedTask.unmarkTask();
        updatedList.set(index, unmarkedTask);
        dataStorage.writeFile(updatedList);
        return new TaskList(updatedList);
    }

    public int getSize() {
        return this.taskList.size();
    }
}
