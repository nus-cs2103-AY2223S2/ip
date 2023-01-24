package duke;

import java.util.Scanner;

public class Ui {
    final private Scanner sc = new Scanner(System.in);

    String readCommand() {
        return sc.nextLine();
    }

    void showWelcome() {
        System.out.println("Hello, Duke here. How can I help you?");
    }

    void error(String err) {
        System.out.printf("Error: %s\n", err);
    }

    void message(String message) {
        System.out.println(message);
    }

    void removeItemMessage(int item) {
        System.out.printf("Removing item %d\n", item);
    }

    void listAllTasks(TaskList tl) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tl.getList().size(); i++) {
            str.append(String.format("%d: %s\n", i + 1, tl.getList().get(i)));
        }
        System.out.println(str);
    }
}
