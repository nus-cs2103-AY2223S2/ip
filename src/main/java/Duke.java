import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static List<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
        boolean isRunning = true;
        loadSave();
        while (isRunning) {
            String currInput = input.nextLine();
            String[] splitInput = currInput.split(" ", 2);
            String currCommand = splitInput[0];
            Integer taskIndex;
            try {
                switch (currCommand) {
                    case "bye":
                        System.out.println("Adios!");
                        isRunning = false;
                        break;
                    case "list":
                        if (taskList.size() == 0) {
                            System.out.println("You have no tasks in your list!");
                            break;
                        }
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            Task task = taskList.get(i);
                            System.out.println(i + 1 + "." + task);
                        }
                        break;

                    case "mark":
                        if (splitInput.length == 1) {
                            throw new DukeException("'mark' requires additional arguments!");
                        }
                        try {
                            taskIndex = Integer.valueOf(splitInput[1]) - 1;
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            break;
                        }
                        if (taskIndex < 0 || taskIndex > taskList.size()) {
                            System.out.println("invalid task index!");
                            break;
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        Task task = taskList.get(taskIndex);
                        task.setDone(true);
                        System.out.println(task);
                        break;
                    case "unmark":
                        if (splitInput.length == 1) {
                            throw new DukeException("'unmark' requires additional arguments!");
                        }
                        try {
                            taskIndex = Integer.valueOf(splitInput[1]) - 1;
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            break;
                        }
                        if (taskIndex < 0 || taskIndex > taskList.size()) {
                            System.out.println("invalid task index!");
                            break;
                        }
                        System.out.println("OK, I've marked this task as not done yet:");
                        task = taskList.get(taskIndex);
                        task.setDone(false);
                        System.out.println(task);
                        break;
                    case "todo":
                        if (splitInput.length == 1) {
                            throw new DukeException("'todo' requires additional arguments!");
                        }
                        taskList.add(new ToDo(splitInput[1]));
                        System.out.println("Added Todo task:\n" + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list!");
                        break;
                    case "deadline":
                        if (splitInput.length == 1) {
                            throw new DukeException("'deadline' requires additional arguments!");
                        }
                        Integer indexBy = splitInput[1].indexOf("/by ");
                        taskList.add(
                            new Deadline(splitInput[1].substring(0, indexBy - 1),
                                splitInput[1].substring(indexBy + 4)));
                        System.out.println("Added Deadline task:\n" + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list!");
                        break;
                    case "event":
                        if (splitInput.length == 1) {
                            throw new DukeException("'event' requires additional arguments!");
                        }
                        Integer indexFrom = splitInput[1].indexOf("/from ");
                        Integer indexTo = splitInput[1].indexOf("/to ");
                        taskList.add(new Event(
                            splitInput[1].substring(0, indexFrom - 1),
                            splitInput[1].substring(indexFrom + 6, indexTo - 1),
                            splitInput[1].substring(indexTo + 4)));
                        System.out.println("Added Event task:\n" + taskList.get(taskList.size() - 1));
                        System.out.println("Now you have " + taskList.size() + " tasks in the list!");
                        break;
                    case "delete":
                        if (splitInput.length == 1) {
                            throw new DukeException("'delete' requires additional arguments!");
                        }
                        try {
                            taskIndex = Integer.valueOf(splitInput[1]) - 1;
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            break;
                        }

                        if (taskIndex < 0 || taskIndex > taskList.size()) {
                            System.out.println("invalid task index!");
                            break;
                        }
                        System.out.println("Gotcha, removed this task: ");
                        System.out.println(taskList.get(taskIndex));
                        taskList.remove(taskIndex.intValue());
                        break;
                    default:
                        throw new DukeException("Please enter a valid command!");
                }
                updateSave();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void updateSave() {
        String toSave = "";
        for (int i = 0; i < taskList.size(); i++) {
            
            toSave += taskList.get(i).serialize() + "\n";
        }
        try {
            FileWriter writer = new FileWriter("donkey.txt");
            writer.write(toSave);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadSave() {
        try {
            File save = new File("donkey.txt");
            if (save.exists()) {
                Scanner scanner = new Scanner(save);
                while (scanner.hasNextLine()) {
                    taskList.add(deserializeTask(scanner.nextLine()));
                }
            } else {
                createSave();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createSave() {
        try {
            File save = new File("donkey.txt");
            if (save.createNewFile()) {
                System.out.println("Save created: " + save.getName());
            } else {
                System.out.println("Save already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Task deserializeTask(String data) {
        String[] splitData = data.split(" \\| ");

        switch (splitData[0]) {
            case "T":
                return new ToDo(splitData[2], splitData[1].equals("1"));
            case "E":
                return new Event(splitData[2], splitData[1].equals("1"), splitData[3], splitData[4]);
            case "D":
                return new Deadline(splitData[2], splitData[1].equals("1"), splitData[3]);
            default:
                System.out.println("Attempting to deserialize wrongly formatted task: ");
                System.out.println(data);
                return null;
        }
    }
}
