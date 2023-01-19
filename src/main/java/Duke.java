import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static class Task {
        public String title;
        private Boolean done;

        public Task(String title) {
            this.title = title;
            this.done = false;
        }

        public void mark() {
            this.done = true;
        }

        public void unmark() {
            this.done = false;
        }

        public Boolean isDone() {
            return this.done;
        }
    }

    private static ArrayList<Task> tasks = new ArrayList<>(100);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                printTasks();
            } else if (input.split(" ")[0].equals("mark")){
                markTask(Integer.parseInt(input.split(" ")[1]));
            } else if (input.split(" ")[0].equals("unmark")){
                unmarkTask(Integer.parseInt(input.split(" ")[1]));
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }

        printMsg("Bye. Hope to see you again soon!");

    }

    private static void addTask(String task) {
        printMsg("added: " + task);
        tasks.add((new Task(task)));
    }

    private static void printTasks() {
        int count = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (Task task : tasks) {
            String check = task.isDone() ? "[X]" : "[ ]";
            System.out.println("     " +  count + "." + check + " " + task.title);
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void markTask(int taskNum) {
        Task selectedTask = tasks.get(taskNum - 1);
        selectedTask.mark();

        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [X] " + selectedTask.title);
        System.out.println("    ____________________________________________________________");
    }

    private static void unmarkTask(int taskNum) {
        Task selectedTask = tasks.get(taskNum - 1);
        selectedTask.unmark();

        System.out.println("    ____________________________________________________________");
        System.out.println("     OK! I've marked this task as not done yet:");
        System.out.println("       [ ] " + selectedTask.title);
        System.out.println("    ____________________________________________________________");
    }

    private static void printMsg(String msg) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    ____________________________________________________________");
    }
}
