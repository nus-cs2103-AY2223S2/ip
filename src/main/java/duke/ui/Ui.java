package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void respond(String... chunks) {
        System.out.println("    ------------------------------DUKE------------------------------");
        for(String lines : chunks) {
            for (String line: lines.split("\n")) {
                System.out.print("        ");
                System.out.println(line);
            }
        }
        System.out.println("    ------------------------------DUKE------------------------------");
    }

    public void error(Exception e) {
        System.out.println("    --------------------ERROR-----DUKE-----ERROR--------------------");
        System.out.print("        ");
        System.out.println(e.getMessage());
        System.out.println("    --------------------ERROR-----DUKE-----ERROR--------------------");
    }

    public void introduce(String logo) {
        respond(
            "Hello I am", 
            logo,
            "",
            "What can I do for you?"
        );
    }

    public void bye() {
        respond("Bye. Hope to see you again soon!");
        sc.close();
    }

    public void listTasks(TaskList tasks) {
        respond(
            "Here are the tasks in your list:", 
            tasks.toString()
        );
    }

    public void markTask(Task task) {
        respond(
            "Nice! I've marked this task as done:", 
            String.format("=> %s", task
        ));
    }

    public void unmarkTask(Task task) {
        respond(
            "OK, I've marked this task as not done yet:", 
            String.format("=> %s", task)
        );
    }
    
    public void addTask(Task task, int length) {
        respond(
            "Got it. I've added this task:",
            String.format("=> %s", task),
            "",
            String.format("Now you have %d tasks in the list", length)
        );
    }

    public void deleteTask(Task task, int length) {
        respond(
            "Noted. I've removed this task",
            String.format("=> %s", task),
            "",
            String.format("Now you have %d tasks in the list", length)
        );
    }

    public String ask() {
        System.out.print(">>> ");
        String cmd = sc.nextLine();
        System.out.println();
        return cmd;
    }

}
