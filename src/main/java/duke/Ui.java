package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.enums.Views;

public class Ui {
    Scanner sc = new Scanner(System.in);

    Ui() {

    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ / _   _| | _____ \n"
                + "| | | | | | | |/ / _ /\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ /__,_|_|/_/___|\n";
        System.out.println("Hello from\n" + logo);
        printer(Views.WELCOME_STRING.eng());
    }

    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            printer(Views.EMPTY_LIST_STRING.eng());
        } else {
            String toPrint = "";
            for (int i = 0; i < tasks.size(); i++) {
                toPrint += ((i + 1) + "." + tasks.get(i)) + "\n      ";
            }
            printer(toPrint.substring(0, toPrint.length() - 7));
        }
    }

    public void showMarkDone(TaskList tasks, int taskNo) {
        printer(Views.MARK_DONE_STRING.eng() + tasks.get(taskNo));
    }

    public void showUnmarkDone(TaskList tasks, int taskNo) {
        printer(Views.UNMARK_DONE_STRING.eng() + tasks.get(taskNo));
    }

    public void showAdd(Task newTask) {
        printer("added: " + newTask);
    }

    public void showDel(Task delTask, TaskList task) {
        String returnString = Views.DELETE_DONE_STRING.eng();
        returnString += delTask.toString();
        returnString += "\n      ";
        returnString += Views.TASK_COUNT_1_STRING.eng();
        returnString += task.size();
        returnString += Views.TASK_COUNT_2_STRING.eng();
        printer(returnString);
    }

    public void showClear() {
        printer(Views.CLEAR_LIST_STRING.eng());
    }

    public void showEnd() {
        printer(Views.END_STRING.eng());
    }

    public void showError(String err) {
        printer(err);
    }

    public void showLoadingError() {
        printer("File load has error");
    }

    private static void printer(String toPrint) {
        System.out.println("    " + Views.LINE_STRING.eng());
        System.out.println("      " + toPrint);
        System.out.println("    " + Views.LINE_STRING.eng());
        System.out.println();
    }
}
