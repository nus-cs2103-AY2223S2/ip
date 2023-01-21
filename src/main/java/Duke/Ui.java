package Duke;

import Duke.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String userCommand() {
        String s = this.sc.nextLine();
        return s;
    }

    public void spawnBot() {
        line();
        greet();
        line();
    }

    public void displayExit() {
        line();
        bye();
        line();
    }

    public void displayTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            line();
            System.out.println("You have no tasks. Hooray!");
            line();
        } else {
            int index = 1;
            String list = "";
            while (index < (taskList.size() + 1)) {
                list = list + "\n   " + index + ". " + taskList.get(index - 1).toString();
                index++;
            }
            line();
            System.out.println("Here are your tasks:" + list);
            checkList(taskList);
            line();
        }
    }

    public static void displayMarked(TaskList taskList, int index) {
        if (taskList.get(index).isDone) {
            line();
            System.out.println("You've already marked this task as done.");
            line();
        } else {
            line();
            System.out.println("Alright, marking this task as done:");
            taskList.get(index).markAsDone();
            indent("" + taskList.get(index));
            line();
        }
    }

    public static void displayUnmarked(TaskList taskList, int index) {
        if (!taskList.get(index).isDone) {
            line();
            System.out.println("You've already marked this task as not done yet.");
            line();
        } else {
            line();
            System.out.println("Alright, marking this task not done yet:");
            taskList.get(index).markAsUndone();
            indent("" + taskList.get(index));
            line();
        }
    }

    public static void displayDelete(TaskList taskList, int index) {
        line();
        System.out.println("Noted, removing this task:");
        indent("" + taskList.get(index));
        taskList.remove(index);
        line();
    }

    // Greet format
    public static void greet() {
        System.out.println("HEY! I'm GRUMMY!\nHow can I help you?");
    }

    // Bye format
    public static void bye() {
        System.out.println("Goodbye! Hope to see you again :>");
    }

    // Create lines
    public static void line() {
        System.out.println("-------------------------");
    }

    // Create indents
    public static void indent(String output) {
        System.out.println("    " + output);
    }

    // Check for grammar task(s)
    public static void checkList(TaskList taskList) {
        if (taskList.size() == 1) {
            System.out.println("You have 1 task in the list.");
        } else if (taskList.size() == 0) {
            System.out.println("You have no tasks in the list.");
        } else {
            System.out.println("You have " + taskList.size() + " tasks in the list.");
        }
    }

    // Add task format
    public static void addedTask() {
        System.out.println("Got it. I've added this task:");
    }
}
