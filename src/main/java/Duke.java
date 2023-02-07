import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.chatDuke();
    }

    public void chatDuke() {

        List<Task> allTasks = new ArrayList<>();

        this.printGreetingMessage();

        boolean saidBye = false;
        while (!saidBye) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                this.printCommandList(allTasks);
            } else if (command.startsWith("mark")) {
                String[] str = command.split(" ");
                int taskIndex = Integer.parseInt(str[1]) - 1;
                Task task = new Task(taskIndex + 1, true, allTasks.get(taskIndex).getTask());
                allTasks.set(taskIndex, task);
                task.markAsDone();
            } else if (command.startsWith("unmark")) {
                String[] str = command.split(" ");
                int taskIndex = Integer.parseInt(str[1]) - 1;
                Task task = new Task(taskIndex + 1, false, allTasks.get(taskIndex).getTask());
                allTasks.set(taskIndex, task);
                task.unmarkAsUndone();
            } else if (!command.equals("bye")) {
                this.echoCommand(command);
                Task task = new Task(allTasks.size(), false, command);
                allTasks.add(task);
            } else {
                saidBye = true;
                this.printByeMessage();
            }
        }
    }

    public void printGreetingMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Hello! I'm Duke\n" +
                "\t What can I do for you?" +
                "\n\t____________________________________________________________");
    }

    public void echoCommand(String command) {
        System.out.println("\t____________________________________________________________" +
                "\n\t" + " added: " + command +
                "\n\t____________________________________________________________");
    }

    public void printCommandList(List<Task> allTasks) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            int numbering = i + 1;
            Task task = allTasks.get(i);
            System.out.println("\t " + numbering + "." + task.getTaskStatus() + " " + task.getTask());
        }

        System.out.println("\t____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Bye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }

}
