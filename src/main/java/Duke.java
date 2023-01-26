import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Duke {
    static ArrayList<Task> todo = new ArrayList<>();
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    static String line = "---------------------------------";

    static void add(Task cur) {
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + cur.symbol + "] " + "[" + cur.getStatusIcon() + "] " + cur.description);
        todo.add(cur);
        System.out.println("Now you have " + todo.size() + " tasks in the list.");
    }

    static void list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        if (todo.size() == 0) {
            System.out.println("There are no tasks due!");
        } else {
            for (Task cur : todo) {
                if (cur instanceof Todo) {
                    System.out.println(todo.indexOf(cur) + 1 + ". [" + cur.symbol + "] " + "[" + cur.getStatusIcon() + "] " + cur.description);
                } else {
                    System.out.println(todo.indexOf(cur) + 1 + ". [" + cur.symbol + "] " + "[" + cur.getStatusIcon() + "] " + cur.description + " (" + cur.due + ")");

                }
            }
        }
    }

    static void markTask(int index) {
        Task cur = todo.get(index);
        cur.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);
    }

    static void unmarkTask(int index) {
        Task cur = todo.get(index);
        cur.markUndone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);
    }

    static void deleteTask(int index) {
        Task cur = todo.get(index);
        todo.remove(index);
        System.out.println("Noted, I've removed this task: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);
        System.out.println("Now you have " + todo.size() + " tasks in the list.");
    }

    static void loadTask(String input) throws MissingDescriptionException {
        String[] cur = input.split(",",3);
        if (cur[0].equals("T")) {
            todo.add(new Todo(cur[2], Boolean.parseBoolean(cur[1])));
        } else if (cur[0].equals("D")) {
            todo.add(new Deadline(cur[2], Boolean.parseBoolean(cur[1])));
        } else {
            todo.add(new Event(cur[2], Boolean.parseBoolean(cur[1])));
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File("../../../data/duke.txt");
            if (!file.exists()) {
                Files.createDirectories(Paths.get("../../../data"));
                Files.createFile(Path.of("../../../data/duke.txt"));
            }
            Scanner scFile = new Scanner(file);
            while (scFile.hasNextLine()) {
                String input = scFile.nextLine();
//                loadTask(scFile.nextLine());
                System.out.println(input);
                loadTask(input);

            }
        } catch (IOException | MissingDescriptionException err) {
            err.printStackTrace();
            System.out.println("Error, the directory of tasks either cannot be found or it has been corrupted. Your new task list will not be saved.");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + "  managed by Wesley Teo.\n\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                System.out.println(line);
                QueryType inputType = Query.queryType(input); //todo read book
                String[] inputArr = input.split(" ");
                int index;

                switch (inputType) {
                case list:
                    list(todo);
                    break;
                case todo:
                    Todo todo = new Todo(input);
                    add(todo);
                    break;
                case deadline:
                    Deadline deadline = new Deadline(input);
                    add(deadline);
                    break;
                case event:
                    Event event = new Event(input);
                    add(event);
                    break;
                case mark:
                    index = Integer.parseInt(inputArr[1]) - 1;
                    markTask(index);
                    break;
                case unmark:
                    index = Integer.parseInt(inputArr[1]) - 1;
                    unmarkTask(index);
                    break;
                case delete:
                    index = Integer.parseInt(inputArr[1]) - 1;
                    deleteTask(index);
                    break;
                default:
                    throw new InvalidCommandDukeException("Invalid command, please try again");
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
            System.out.println(line);
            input = sc.nextLine();
        }
        try{
            File file = new File("../../../data/duke.txt");
            FileWriter fileWriter = new FileWriter(file);
            for (Task cur : todo) {
                fileWriter.write(cur.saveTask());
                fileWriter.write('\n');
            }
            fileWriter.close();
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
        } catch(IOException err) {
            System.out.println("Tasks cannot be saved");
        }
    }
}