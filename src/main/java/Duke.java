import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String PATH = "data/Duke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(formatOutput("Hey there! I'm Sirius\n\t What can I do for you today? :D"));
        List<Task> taskList = loadFile();

        String input = sc.nextLine();
        while (!isBye(input)) {
            String[] inputArr = input.split(" ", 2);
            String firstWord = inputArr[0];
            int index = 0;
            String taskInput = "";
            try {
                switch (firstWord) {
                    case "list":
                        StringBuilder sb = new StringBuilder();
                        sb.append("Here are the tasks in your list:\n\t ");
                        for (int i = 0; i < taskList.size(); i++) {
                            int count = i + 1;
                            String res = count + "." + taskList.get(i).toString();
                            if (i != taskList.size() - 1) {
                                res += "\n\t ";
                            }
                            sb.append(res);
                        }
                        System.out.println(formatOutput(sb.toString()));
                        break;
                    case "mark":
                        index = Integer.parseInt(inputArr[1]) - 1;
                        if (index >= taskList.size()) {
                            System.out.println("Oopsies.. Seems like that task does not exist :(");
                        } else {
                            Task currentTask = taskList.get(index);
                            currentTask.markAsDone();
                            System.out.println(formatOutput("Great :D I knew you could do it! I've marked this task as done:\n\t\t" + currentTask.toString()));
                        }
                        break;
                    case "unmark":
                        index = Integer.parseInt(inputArr[1]) - 1;
                        if (index >= taskList.size()) {
                            System.out.println("Oopsies.. Seems like that task does not exist :(");
                        } else {
                            Task currentTask = taskList.get(index);
                            currentTask.markAsNotDone();
                            System.out.println(formatOutput("Ok, I've marked this task as not done yet:\n\t\t" + currentTask.toString()));
                        }
                        break;
                    case "delete":
                        index = Integer.parseInt(inputArr[1]) - 1;
                        Task toDelete = taskList.get(index);
                        taskList.remove(index);
                        System.out.println(formatOutput("Ok. I've removed this task:\n\t\t" + toDelete.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    case "todo":
                        if (isDescriptionEmpty(inputArr)) {
                            throw new DukeException(formatOutput("Hey now.. The description of a todo cannot be empty. >:("));
                        }
                        taskInput = inputArr[1];
                        Todo todo = new Todo(taskInput);
                        taskList.add(todo);
                        System.out.println(formatOutput("Got it. I've added this task:\n\t\t" + todo.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    case "deadline":
                        if (isDescriptionEmpty(inputArr)) {
                            throw new DukeException(formatOutput("Hey now.. The description of a deadline cannot be empty. >:("));
                        }
                        taskInput = inputArr[1];
                        String[] dArr = taskInput.split("/by", 2);
                        String deadlineDesc = dArr[0].trim();
                        String by = dArr[1].trim();
                        Deadline deadline = new Deadline(deadlineDesc, by);
                        taskList.add(deadline);
                        System.out.println(formatOutput("Got it. I've added this task:\n\t\t" + deadline.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    case "event":
                        if (isDescriptionEmpty(inputArr)) {
                            throw new DukeException(formatOutput("Hey now.. The description of an event cannot be empty. >:("));
                        }
                        taskInput = inputArr[1];
                        String[] eArr = taskInput.split("/");
                        String eventDesc = eArr[0].trim();
                        String from = eArr[1].trim().substring(5);
                        String to = eArr[2].trim().substring(3);
                        Event event = new Event(eventDesc, from, to);
                        taskList.add(event);
                        System.out.println(formatOutput("Got it. I've added this task:\n\t\t" + event.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    default:
                        throw new DukeException("Huh? What do you mean? :o");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Woah.. Error parsing date time for deadline task. Please enter the by datetime in this format yyyy-mm-dd (e.g., 2023-10-15).");
            } finally {
                saveListToFile(taskList);
            }
            input = sc.nextLine();
        }

        System.out.println(formatOutput("Well, I'm off! Hope to see you again soon :)"));
    }

    private static List<Task> loadFile() {
        List<Task> initList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            String line = reader.readLine().trim();
            while (line != null) {
                String[] strArr = line.split(" \\| ");
                String type = strArr[0];
                boolean isCompleted = Integer.parseInt(strArr[1]) == 1;
                String taskDesc = strArr[2];

                switch (type) {
                    case "T":
                        initList.add(new Todo(taskDesc, isCompleted));
                        break;
                    case "D":
                        initList.add(new Deadline(taskDesc, isCompleted, strArr[3]));
                        break;
                    case "E":
                        initList.add(new Event(taskDesc, isCompleted, strArr[3], strArr[4]));
                        break;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("No initial data found. I'll be generating a new task list for you..");
            File newFile = new File(PATH);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        } finally {
            return initList;
        }
    }

    private static void saveListToFile(List<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(PATH, false);
            for (Task t : taskList) {
                writer.write(t.parseToSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Uh oh... An error occurred while I was trying to save your latest task list :0");
        }
    }


    public static String formatOutput(String input) {
        String line = "\t____________________________________________________________\n";
        return line + "\t " + input + "\n" + line;
    }

    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    public static boolean isList(String input) {
        return input.equalsIgnoreCase("list");
    }

    public static boolean isDescriptionEmpty(String[] arr) {
        return arr.length == 1 || arr[1].trim().isEmpty();
    }
}
