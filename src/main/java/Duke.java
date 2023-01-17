import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");

        String input = "";
        ArrayList<Task> tasksList = new ArrayList<Task>();

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String command = input.split(" ")[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                int taskNumber = 1;
                for (Task t : tasksList) {
                    System.out.println(Integer.toString(taskNumber) + ". " + t);
                    taskNumber++;
                }
            } else if (command.equals("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasksList.get(taskNumber).markAsDone();
            } else if (command.equals("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasksList.get(taskNumber).markAsNotDone();
            } else {
                Task t = new Task(input);
                tasksList.add(t);
                System.out.println("added: " + input);
            }
        }
    }

    public static class Task {
        protected  String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        @Override
        public String toString() {
            return getStatusIcon() + " " + this.description;
        }

        public void markAsDone() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this);
        }

        public void markAsNotDone() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this);
        }
    }
}
