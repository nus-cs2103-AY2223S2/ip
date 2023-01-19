import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        while (true) {
            String data = scanner.nextLine();
            if (data.equals("bye")) {
                System.out.println("Goodbye! It's been a pleasure talking to you!");
                break;
            } else if (data.equals("list")) {
                System.out.println(taskList);
            } else if (data.startsWith("mark ")) {
                String marked_index = data.substring(5);
                System.out.println(taskList.markTask(marked_index));
            } else if (data.startsWith("unmark ")) {
                String unmarked_index = data.substring(7);
                System.out.println(taskList.unmarkTask(unmarked_index));
            } else if (data.startsWith("todo ") || data.startsWith("deadline ") || data.startsWith("event ")) {
                Task task = Task.makeTask(data);
                System.out.println(taskList.addTask(task));
            }
        }
    }
}
