package view;
import model.Task;
import interfaces.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskView implements View {
    private final Scanner sc;
    public TaskView() {
        this.sc = new Scanner(System.in);
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
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task: tasks) {
            System.out.println(index++ + ". " + task.toString());
        }
        System.out.println("____________________________________________________________");
    }
}
