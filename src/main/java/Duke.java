import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;
import tasktypes.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.PrintWriter;
public class Duke {
    public static void main(String[] args) {

        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?";
        System.out.println(greeting);

        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File dukeFile = new File("./data/duke.txt");
        try {
            if (!dukeFile.exists()) {
                dukeFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String[] singleCommands = {"bye", "list"};
        String[] valueCommands = {"unmark ", "mark ", "delete "};
        String[] taskCommands = {"deadline ", "todo ", "event "};

        ArrayList<Task> taskStorage = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(Paths.get("./data/duke.txt"));
            boolean emptyFile = true;

            for (String line : allLines) {
                emptyFile = false;
                String[] taskSplit = line.split(",,");
                String type = taskSplit[0];
                Task task = null;
                switch (type) {
                    case "T": {
                        task = new ToDo(taskSplit[2]);
                        break;
                    }
                    case "D": {
                        task = new Deadline(taskSplit[2], taskSplit[3]);
                        break;
                    }
                    case "E": {
                        task = new Event(taskSplit[2], taskSplit[3], taskSplit[4]);
                        break;
                    }
                }

                String done = taskSplit[1];
                if (done.equals("1")) {
                    task.markDone();
                }

                taskStorage.add(task);
            }

            if (!emptyFile) {
                printTasks(taskStorage);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                printTasks(taskStorage);
                input = sc.nextLine();
                continue;
            }

            String command = returnCommand(input, valueCommands);
            switch (command) {
                case "unmark ": {
                    String[] inputArr = input.split(" ");
                    int toUnmark = Integer.parseInt(inputArr[1]);
                    Task unmarkTask = taskStorage.get(toUnmark - 1);
                    unmarkTask.markUndone();
                    System.out.println("OK, I've marked this task as undone:\n" + unmarkTask);
                    input = sc.nextLine();
                    continue;
                }

                case "mark ": {
                    String[] inputArr = input.split(" ");
                    int toMark = Integer.parseInt(inputArr[1]);
                    Task markTask = taskStorage.get(toMark - 1);
                    markTask.markDone();
                    System.out.println("Nice! I've marked this task as done:\n" + markTask);
                    input = sc.nextLine();
                    continue;
                }

                case "delete ": {
                    String[] inputArr = input.split(" ");
                    int toDelete = Integer.parseInt(inputArr[1]);
                    Task deleteTask = taskStorage.remove(toDelete - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deleteTask.toString());
                    System.out.println("Now you have " + --Task.numTask + " tasks in the list.");
                    input = sc.nextLine();
                    continue;
                }
            }


            try {
                validateTaskCommand(input, taskCommands);
            } catch (DukeException de) {
                System.out.println(de.getMessage());
                input = sc.nextLine();
                continue;
            }

            String[] inputArr = input.split(" ", 2);
            String taskType = inputArr[0];

            switch (taskType) {
                case "todo":
                    ToDo todoTask = new ToDo(inputArr[1]);
                    taskStorage.add(todoTask);
                    System.out.println("Got it. I've added this ToDo task:");
                    System.out.println(todoTask);
                    System.out.println("Now you have " + Task.numTask + " tasks in the list.");

                    break;
                case "deadline": {
                    String[] newInputArr = inputArr[1].split(" /by ", 2);
                    Deadline deadlineTask = new Deadline(newInputArr[0], newInputArr[1]);
                    taskStorage.add(deadlineTask);
                    System.out.println("Got it. I've added this Deadline task:");
                    System.out.println(deadlineTask);
                    System.out.println("Now you have " + Task.numTask + " tasks in the list.");

                    break;
                }
                case "event": {
                    String[] newInputArr = inputArr[1].split(" /from ", 2);
                    String[] newerInputArr = newInputArr[1].split(" /to ", 2);
                    Event eventTask = new Event(newInputArr[0], newerInputArr[0], newerInputArr[1]);
                    taskStorage.add(eventTask);
                    System.out.println("Got it. I've added this Event task:");
                    System.out.println(eventTask);
                    System.out.println("Now you have " + Task.numTask + " tasks in the list.");

                    break;
                }
            }

            input = sc.nextLine();
        }

        try {
            PrintWriter writer = new PrintWriter(dukeFile);
            printGoodbye(taskStorage, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }

    public static String returnCommand(String input, String[] commands) {
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            boolean gotMatch = match.find();

            if (gotMatch && match.start() == 0) {
                return s;
            }
        }

        return input;
    }

    public static void validateTaskCommand(String input, String[] commands) throws DukeException {
        input = input.trim();
        for (String s : commands) {
            if (s.equals(input + " ")) {
                throw new DukeException("OOPS!!! The description of a " + input + " task cannot be empty!");
            }
        }

        boolean correct = false;
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            boolean gotMatch = match.find() && (match.start() == 0);
            correct = correct || gotMatch;
        }

        if (!correct) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means! :(");
        }
    }

    public static void printGoodbye(ArrayList<Task> taskStorage, PrintWriter writer) {
        System.out.println("Updating your data. Please wait..");

        int count = 0;

        for (Task task : taskStorage) {
            count++;
            String toWrite = task.getSaveFormat();
            if (count == 1) {
                writer.println(toWrite);
            } else {
                writer.append(toWrite + "\n");
            }
        }
        writer.close();

        System.out.println("All changes saved successfully!");

        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
    }

    public static void printTasks(ArrayList<Task> taskStorage) {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskStorage) {
            String output = String.format("%d.%s", count++, task.toString());
            System.out.println(output);
        }
    }

}
