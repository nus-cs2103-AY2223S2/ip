package Duke;

import Duke.Commands.Command;

public class Ui {
    private static final String WELCOME = "Hello! Welcome to Duke. Let's start task tracking!";

    private static final String GOODBYE = "Auf Wiedersehen!";

    public Ui() {
        // empty
    }

    public void printList(TaskList tasks) {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        System.out.println(res);
    }

    public void printWelcome() {
        System.out.println(WELCOME);
    }

    public void printGoodbye() {
        System.out.println(GOODBYE);
    }

    public void printCommandMessage(Command command) {
        System.out.println(command);
    }
}
