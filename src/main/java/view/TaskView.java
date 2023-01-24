package view;
import interfaces.View;

import java.util.Scanner;

public class TaskView implements View {
    private final Scanner sc;
    public TaskView() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public void showMessage(String string) {
        System.out.println(string);
    }

    @Override
    public String getUserInput() {
        return this.sc.nextLine();
    }
}
