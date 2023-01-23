import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A chatbot with functionality to add or remove different
 * types of Tasks.
 */
public class Duke {
    // Arraylist to store the items.
    private static ArrayList<Task> list = new ArrayList<>();

    // Enums for the different commands
    private enum Command {
        TODO, DEADLINE, EVENT, LIST,
        MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    /**
     * Loads the data from hard disk upon start up.
     */
    public static void initialise() {
        // File object representing the data folder.
        File folder = new File("data");
        System.out.println("Initialising data dependencies:");
        System.out.println("    Checking for data directory...");
        try {
            if (folder.exists()) {
                System.out.println("    Data directory exists.");
            } else {
                System.out.println("    Data directory does not exist. Creating directory.");
                if (folder.mkdir()) {
                    System.out.println("    Data directory created.");
                } else {
                    throw new DukeException("    Data directory cannot be created.");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        // File object representing the list file.
        File list = new File("data/list.txt");
        System.out.println("    Checking for saved file...");
        if (list.exists()) {
            System.out.println("    Saved list exists.");
        } else {
            System.out.println("    Saved list does not exist. Creating list file.");
            try {
                if (list.createNewFile()) {
                    System.out.println("    List file created.");
                } else {
                    throw new DukeException("    List file cannot be created.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("    Loading data from file...");
        loadData();
        System.out.println("    Loading complete.");

        System.out.println("Initialisation complete.");
    }

    /**
     * Loads the data from hard disk to the ArrayList.
     */
    public static void loadData() {
        File text = new File("data/list.txt");
        try {
            Scanner input = new Scanner(text);
            while (input.hasNext()) {
                String task = input.nextLine();
                String[] items = task.split("/", 5);

                if (items[0].equals("T")) {
                    list.add(new Todo(items[2]));
                } else if (items[0].equals("D")) {
                    list.add(new Deadline(items[2], items[3]));
                } else if (items[0].equals("E")) {
                    list.add(new Event(items[2], items[3], items[4]));
                } else {
                    throw new DukeException("Unknown task type detected.");
                }

                if (items[1].equals("1")) {
                    list.get(list.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Writes the data provided into the hard disk.
     */
    public static void writeData() {
        try {
            FileWriter writer = new FileWriter("data/list.txt");
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).printData() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Write to list file failed. ("
                    + e.getMessage() + ")");
        }
    }

    /**
     * Adds user input to a list.
     * Supports several tasks, such as todo,
     * deadline and event.
     * Operations: todo, deadline, event, list,
     * mark, unmark, delete.
     * Also saves list to hard disk and loads it up
     * upon start up.
     */
    public static void greet() {
        // Scanner for user input.
        Scanner userInput = new Scanner(System.in);

        while (true) {
            // Stores the user input.
            String input = userInput.nextLine();
            Command inputType;

            if (input.equals("bye")) {
                inputType = Command.BYE;
            } else if (input.matches("todo(.*)")) {
                inputType = Command.TODO;
            } else if (input.matches("deadline(.*)")) {
                inputType = Command.DEADLINE;
            } else if (input.matches("event(.*)")) {
                inputType = Command.EVENT;
            } else if (input.equals("list")) {
                inputType = Command.LIST;
            } else if (input.matches("mark(.*)")) {
                inputType = Command.MARK;
            } else if (input.matches("unmark(.*)")) {
                inputType = Command.UNMARK;
            } else if (input.matches("delete(.*)")) {
                inputType = Command.DELETE;
            } else {
                inputType = Command.UNKNOWN;
            }

            System.out.println("____________________________________________________________");

            try {
                if (inputType == Command.BYE) {
                    // User input: bye
                    System.out.println("Bye. Hope to see you soon!");
                } else if (inputType == Command.TODO) {
                    // User input: todo x
                    input = input.replace("todo ", "");
                    if (input.equals("todo")) {
                        // Checks if todo is empty.
                        throw new DukeException("todo");
                    } else {
                        System.out.println("Got it. I've added this task:");
                        list.add(new Todo(input));
                        System.out.println("    " + list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        writeData();
                    }
                } else if (inputType == Command.DEADLINE) {
                    // User input: deadline x
                    input = input.replace("deadline ", "");
                    if (input.equals("deadline")) {
                        // Checks if deadline is empty.
                        throw new DukeException("deadline");
                    } else {
                        String[] inputs = input.split(" /by ", 2);
                        System.out.println("Got it. I've added this task:");
                        list.add(new Deadline(inputs[0], inputs[1]));
                        System.out.println("    " + list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        writeData();
                    }
                } else if (inputType == Command.EVENT) {
                    // User input: event x
                    input = input.replace("event ", "");
                    if (input.equals("event")) {
                        // Checks if event is empty.
                        throw new DukeException("event");
                    } else {
                        String[] inputs = input.split(" /", 3);
                        inputs[1] = inputs[1].replace("from ", "");
                        inputs[2] = inputs[2].replace("to ", "");
                        System.out.println("Got it. I've added this task:");
                        list.add(new Event(inputs[0], inputs[1], inputs[2]));
                        System.out.println("    " + list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        writeData();
                    }
                } else if (inputType == Command.LIST) {
                    // User input: list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i).toString());
                    }
                } else if (inputType == Command.MARK) {
                    // User input: mark x
                    // Extracts the numbered item from the user input string
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    if ((index < 0) | (index > (list.size() - 1)) ) {
                        // Checks if provided index is in range.
                        throw new DukeException("index");
                    } else {
                        list.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + list.get(index).toString());
                        writeData();
                    }
                } else if (inputType == Command.UNMARK) {
                    // User input: unmark x
                    // Extracts the numbered item from the user input string
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    if ((index < 0) | (index > (list.size() - 1)) ) {
                        // Checks if provided index is in range.
                        throw new DukeException("index");
                    } else {
                        list.get(index).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("    " + list.get(index).toString());
                        writeData();
                    }
                } else if (inputType == Command.DELETE) {
                    // User input: delete x
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    if ((index < 0) | (index > (list.size() - 1)) ) {
                        // Checks if provided index is in range.
                        throw new DukeException("index");
                    } else {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("    " + list.get(index).toString());
                        list.remove(index);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        writeData();
                    }
                } else if (inputType == Command.UNKNOWN){
                    // Unknown command.
                    throw new DukeException("unknown");
                }
            } catch (DukeException e) {
                if (e.getMessage().equals("index")) {
                    System.out.println("☹ OOPS!!! Index out of range.");
                } else if (e.getMessage().equals("unknown")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    System.out.println("☹ OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
                }
            }
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                break;
            }
        }

        userInput.close();
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("____________________________________________________________");
        initialise();
        System.out.println("____________________________________________________________");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        greet();
    }
}
