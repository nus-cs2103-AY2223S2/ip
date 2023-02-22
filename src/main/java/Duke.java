import java.util.Scanner;
import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.DukeMarkOutOfBounds;
import exceptions.DukeTodoNoDescription;
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
                    try {
                        Command mark = new Mark(index, tasks);
                        System.out.println(line);
                        System.out.println(mark.execute());

                    } catch (DukeMarkOutOfBounds e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(line);
                    break;
                }
                // honestly creation of task objects here should be handled by the task objects
                // change later
                case "todo": {
                    System.out.println(line);
                    try {
                        Command addTask = new AddTask("todo", arguments, tasks);
                        System.out.println(addTask.execute());
                    } catch (DukeTodoNoDescription e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(line);
                    break;
                }
                case "deadline": {
                    System.out.println(line);
                    try {
                        Command addTask = new AddTask("deadline", arguments, tasks);
                        System.out.println(addTask.execute());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(line);
                    break;
                }
                case "event": {
                    System.out.println(line);
                    try {
                        Command addTask = new AddTask("event", arguments, tasks);
                        System.out.println(addTask.execute());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
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
                case "save": {
                    Command save = new Save(tasks);
                    System.out.println(line);
                    System.out.println(save.execute());
                    System.out.println(line);
                    break;
                }
                case "load": {
                    Command load = new Load(tasks);
                    System.out.println(line);
                    System.out.println(load.execute());
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
