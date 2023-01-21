package Duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int size;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.size = 0;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
        this.size = this.size + 1;
    }

    public static void markTask(TaskList taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.displayMarked(taskList, index);
            } else {
                Ui.line();
                System.out.println("Please enter a valid number!");
                Ui.checkList(taskList);
                Ui.line();
            }
        } catch (NumberFormatException e) {
            System.out.println("enter valid number");
        }
    }

    public static void unmarkTask(TaskList taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.displayUnmarked(taskList, index);
            } else {
                Ui.line();
                System.out.println("Please enter a valid number!");
                Ui.checkList(taskList);
                Ui.line();
            }
        } catch (NumberFormatException e) {
            System.out.println("enter valid number");
        }
    }

    public static void removeTask(TaskList taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.displayDelete(taskList, index);
            } else {
                Ui.line();
                System.out.println("Please enter a valid number!");
                Ui.checkList(taskList);
                Ui.line();
            }
        } catch (NumberFormatException e) {
            System.out.println("enter valid number");
        }
    }
}
