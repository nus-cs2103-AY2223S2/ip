import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "    ————————————————————————————————";
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(divider);
        Scanner sc = new Scanner(System.in);
        TaskTracker tasks = new TaskTracker();
        while (true) {
            String command = sc.nextLine();
            System.out.println(divider);
            boolean addTask = true;
            if (command.equals("list")) {
                tasks.listTasks();
                addTask = false;
            } else if (command.matches("^mark \\d")) { // could replace with startsWith("mark")
                String[] input = command.split(" ");
                int taskNumber = Integer.parseInt(input[1]) - 1;
                tasks.markTaskCompletion(taskNumber, true);
                addTask = false;
            } else if (command.matches("^unmark \\d")) {
                String[] input = command.split(" ");
                int taskNumber = Integer.parseInt(input[1]) - 1;
                tasks.markTaskCompletion(taskNumber, false);
                addTask = false;
            } else if (command.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(divider);
                sc.close(); // close scanner
                return;
            }
            if (addTask) {
                if (command.startsWith("event")) {
                    Event e = Event.createEvent(command);
                    tasks.addTask(e);
                } else if (command.startsWith("deadline")) {
                    DeadlineTask d = DeadlineTask.createDeadlineTask(command);
                    tasks.addTask(d);
                } else if (command.startsWith("todo")) {
                    ToDo t = ToDo.createToDo(command);
                    tasks.addTask(t);
                }
            }
            System.out.println(divider);
        }
    }
}
