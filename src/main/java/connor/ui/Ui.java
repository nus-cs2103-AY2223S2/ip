package connor.ui;

import connor.task.Task;

public class Ui {
    public String LINE = "        ________________________________________________________\n";

    public void printMessage(String response) {
        System.out.println(LINE + "        " + response);
    }

    public void greet() {
        printMessage("Hello! I'm Connor, the android sent by Cyberlife.\n"
                + "        Please type in your command below.");
    }

    public void greetings(String responseType) {
        switch (responseType) {
            case "HI":
                printMessage("Hi, I hope that you are having a nice day.");
                break;

            case "BYE":
                printMessage("It was a good session Hank, Bye.");
                break;
        }
    }

    public void addTaskMessage(Task task, int size) {
        String message = "I have added " + task.getTaskName() + " to my memory\n";
        message = message + "          " + task.toString() + "\n";
        message = message + "        You have " + size + " tasks in the list";
        this.printMessage(message);
    }

    public void deleteAllMessage() {
        this.printMessage("All tasks on the list have been cleared");
    }

    public void deleteTaskMessage(Task task, int size) {
        String message = "I have removed " + task.getTaskName() + " from my memory\n";
        message = message + "          " + task.toString() + "\n";
        message = message + "        You have " + size + " tasks in the list";
        this.printMessage(message);
    }

    public void markDoneMessage(String message) {
        this.printMessage("Understood, I have marked the task as done:\n"
                + "        "
                + message);
    }

    public void markUndoneMessage(String message) {
        this.printMessage("Understood, I have marked the task as undone:\n"
                + "        "
                + message);
    }
}
