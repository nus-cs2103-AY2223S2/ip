package seedu.duke;

import seedu.duke.tasks.*;

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

    public TaskList addTask(Task newTask) {
        ArrayList<Task> updatedList = this.taskList;
        updatedList.add(newTask);
        return new TaskList(updatedList);
    }

    public TaskList deleteTask(int index) {
        ArrayList<Task> updatedList = this.taskList;
        updatedList.remove(index);
        return new TaskList(updatedList);
    }

    public TaskList markTask(int index) throws DukeException {
        ArrayList<Task> updatedList = this.taskList;
        Task unmarkedTask = updatedList.get(index);
        Task markedTask = unmarkedTask.markTask();
        updatedList.set(index, markedTask);
        return new TaskList(updatedList);
    }

    public TaskList unmarkTask(int index) throws DukeException {
        ArrayList<Task> updatedList = this.taskList;
        Task markedTask = updatedList.get(index);
        Task unmarkedTask = markedTask.unmarkTask();
        updatedList.set(index, unmarkedTask);
        return new TaskList(updatedList);
    }

    public String formatTask(int index) {
        return get(index).formatTask();
    }

    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Finds all Tasks in TaskList that contains the String of keyword(s), including sub-words
     *
     * @param keywords keywords provided by the user
     * @return TaskList containing all Tasks with matching keywords
     */
    public TaskList find(String keywords) throws DukeException {
        TaskList matchingTasks = new TaskList();
        for (int index = 0; index < getSize(); index++) {
            Task task = get(index);
            if (task.toString().contains(keywords)) {
                matchingTasks.addTask(task);
            }
        }
        if (matchingTasks.getSize() == 0) {
            throw new DukeException("There are no matching tasks!");
        }
        return matchingTasks;
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof TaskList) {
            TaskList taskList = (TaskList) o;
            if(taskList.taskList.equals(this.taskList)) {
                return true;
            }
            return true;
        }
        return  false;
    }
}
