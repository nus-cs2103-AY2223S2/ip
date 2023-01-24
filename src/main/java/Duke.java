import tasklist.TaskList;
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

        TaskList tasks = new TaskList();

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

                tasks.loadTask(task);
            }

            if (!emptyFile) {
                tasks.printTasks();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                tasks.printTasks();
                input = sc.nextLine();
                continue;
            }

            String command = returnCommand(input, valueCommands);
            switch (command) {
                case "unmark ": {
                    String[] inputArr = input.split(" ");
                    int toUnmark = Integer.parseInt(inputArr[1]);
                    tasks.unmarkTask(toUnmark);
                    input = sc.nextLine();
                    continue;
                }

                case "mark ": {
                    String[] inputArr = input.split(" ");
                    int toMark = Integer.parseInt(inputArr[1]);
                    tasks.markTask(toMark);
                    input = sc.nextLine();
                    continue;
                }

                case "delete ": {
                    String[] inputArr = input.split(" ");
                    int toDelete = Integer.parseInt(inputArr[1]);
                    if (toDelete > tasks.getNumTasks()) {
                        System.out.println("Task does not exist. Please enter a valid input.");
                        input = sc.nextLine();
                        continue;
                    }
                    tasks.deleteTask(toDelete);
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
                    tasks.addTask(todoTask);
                    break;
                case "deadline": {
                    String[] newInputArr = inputArr[1].split(" /by ", 2);
                    Deadline deadlineTask = new Deadline(newInputArr[0], newInputArr[1]);
                    tasks.addTask(deadlineTask);
                    break;
                }
                case "event": {
                    String[] newInputArr = inputArr[1].split(" /from ", 2);
                    String[] newerInputArr = newInputArr[1].split(" /to ", 2);
                    Event eventTask = new Event(newInputArr[0], newerInputArr[0], newerInputArr[1]);
                    tasks.addTask(eventTask);
                    break;
                }
            }

            input = sc.nextLine();
        }

        try {
            PrintWriter writer = new PrintWriter(dukeFile);
            printGoodbye(tasks, writer);
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

    public static void printGoodbye(TaskList tasks, PrintWriter writer) {
        System.out.println("Updating your data. Please wait..");

        int count = 0;

        for (Task task : tasks.getTasks()) {
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

}
