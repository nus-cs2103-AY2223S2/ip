import java.util.Scanner;
import java.util.ArrayList;
import tasks.*;
import commands.*;
public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hewwo?!?1 I'm Duke\nWhat c-can I do fow you?!?!");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ArrayList<Task> tasks = new ArrayList<>();
        while (running) {
            String rawInput = scanner.nextLine();
            String[] input = rawInput.trim().split(" ", 2);
            String command = input[0].toLowerCase();
            String arguments;
            try {
                arguments = input[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                arguments = "";
            }
            switch (command) {
                case "echo": {
                    Command echo = new Echo(arguments);
                    System.out.println(line);
                    System.out.println(echo.execute());
                    System.out.println(line);
                    break;
                }
                case "list": {
                    Command list = new ListTasks(tasks);
                    System.out.println(line);
                    System.out.println(list.execute());
                    System.out.println(line);
                    break;
                }
                case "mark": {
                    int index = Integer.parseInt(arguments) - 1;
                    Command mark = new Mark(tasks.get(index));
                    System.out.println(line);
                    System.out.println(mark.execute());
                    System.out.println(line);
                    break;
                }
                // honestly creation of task objects here should be handled by the task objects
                // change later
                case "todo": {
                    Command addTask = new AddTask(new Todo(arguments), tasks);
                    System.out.println(line);
                    System.out.println(addTask.execute());
                    System.out.println(line);
                    break;
                }
                case "deadline": {
                    String[] argumentsSplit = input[1].split(" ", 2);
                    Command addTask = new AddTask(new Deadline(argumentsSplit), tasks);
                    System.out.println(line);
                    System.out.println(addTask.execute());
                    System.out.println(line);
                    break;
                }
                case "event": {
                    String[] argumentsSplit = input[1].split(" ", 3);
                    Command addTask = new AddTask(new Event(argumentsSplit), tasks);
                    System.out.println(line);
                    System.out.println(addTask.execute());
                    System.out.println(line);
                    break;
                }
                case "delete": {
                    Command deleteTask = new DeleteTask(Integer.parseInt(arguments), tasks);
                    System.out.println(line);
                    System.out.println(deleteTask.execute());
                    System.out.println(line);
                    break;
                }
                case "bye": {
                    System.out.println(line);
                    System.out.println("bye ;;w;; see you again soon :3");
                    System.out.println(line);
                    running = false;
                    break;
                }
                default: {
                    System.out.println(line);
                    System.out.println("bruh idk wtf that means");
                    System.out.println(line);
                }
            }
        }

    }
}
