package duke.view;


import java.util.List;
import java.util.Scanner;

import duke.interfaces.View;
import duke.model.Task;

public class TaskView implements View {
    private final Scanner sc;
    private List<Task> displayedTaskList;
    public TaskView() {
        this.sc = new Scanner(System.in);
    }

    public Task getDisplayedTask(int index) {
        return displayedTaskList.get(index);
    }

    public int getNumDisplayedTasks() {
        return displayedTaskList.size();
    }
    @Override
    public void showMessage(String string) {
        System.out.println("____________________________________________________________");
        System.out.println(string);
        System.out.println("____________________________________________________________");
    }

    @Override
    public void showError(String string) {
        System.err.println(string);
    }

    @Override
    public String getUserInput() {
        return this.sc.nextLine();
    }

    @Override
    public void renderTasks(List<Task> tasks) {
        this.displayedTaskList = tasks;
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task: tasks) {
            System.out.println(index++ + ". " + task.toString());
        }
        System.out.println("____________________________________________________________");
    }
}
