package connor.ui;

import connor.task.Task;

public class Ui {


    public String greet() {
        return ("Hello! I'm Connor, the android sent by Cyberlife.\n"
                + "Please type in your command below.");
    }

    public String greetings(String responseType) {
        switch (responseType) {
        case "HI":
            return "Hi, I hope that you are having a nice day.";

        case "BYE":
            return "It was a good session Hank, Bye.";

        default:
            return "";
        }
    }

    public String addTaskMessage(Task task, int size) {
        String message = "I have added " + task.getTaskName() + " to my memory\n";
        message = message + "  " + task.toString() + "\n";
        message = message + "You have " + size + " tasks in the list";
        return message;
    }

    public String deleteAllMessage() {
        return "All tasks on the list have been cleared";
    }

    public String deleteTaskMessage(Task task, int size) {
        String message = "I have removed " + task.getTaskName() + " from my memory\n";
        message = message + "  " + task.toString() + "\n";
        message = message + "You have " + size + " tasks in the list";
        return message;
    }

    public String markDoneMessage(String message) {
        return "Understood, I have marked the task as done:\n"
                + "  "
                + message;
    }

    public String markUndoneMessage(String message) {
        return "Understood, I have marked the task as undone:\n"
                + "  "
                + message;
    }

    public String sortMessage() {
        return "I have successfully sorted the tasks";
    }
}
