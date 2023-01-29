package duke;

import duke.task.Task;

public class TaskList {
    private int index;
    private Task[] tasks;

    public TaskList(Task[] tasks) {
        this.index = 0;
        this.tasks = tasks;
        for (int i = 0; i < 100; i++) {
            if (tasks[i] == null) {
                this.index = i;
                break;
            }
            continue;
        }
    }

    public TaskList() {
        this.index = 0;
        this.tasks = new Task[100];
    }

    public String addTask(Task task) {
        this.tasks[this.index] = task;
        String str = "Got it! This task has been added:\n";
        str += this.printTask(index) + "\n";
        this.index++;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.";
        return str;
    }

    public String deleteTask(int index) {
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("duke.task.Task does not exist!");
        }
        String str = "Got it! This task has been removed:\n";
        str += this.printTask(index) + "\n";
        this.index--;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.";
        for (int i = index ; i < 99 ; i++) {
            this.tasks[i] = this.tasks[i + 1];
        }
        this.tasks[99] = null;
        return str;
    }

    public String markTask(int index) {
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("duke.task.Task does not exist!");
        }
        this.tasks[index].mark();
        String str = "Great job! This task has been marked as done:\n";
        str += this.printTask(index);
        return str;
    }

    public String unMarkTask(int index) {
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("duke.task.Task does not exist!");
        }
        this.tasks[index].unMark();
        String str = "Noted! This task has been marked as undone:\n";
        str += this.printTask(index);
        return str;
    }

    public String printTask(int index) {
        return tasks[index].toString();
    }

    public String taskListToSavedForm() {
        String str = "";
        for (int i = 0; i < this.index; i ++) {
            str += tasks[i].taskToSavedForm() + "\n";
        }
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.index; i++) {
            if (i == this.index - 1) {
                str += (i + 1) + ". " + this.printTask(i);
            }
            else {
                str += (i + 1) + ". " + this.printTask(i) + "\n";
            }
        }
        return str;
    }
}
