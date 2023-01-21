/**
 * Project name: Duke
 * Author: Tan Jun Da
 * Student Number: A0234893U
 *
 * This class is the main class for the duke ip.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    public enum Commands {
        bye,
        mark,
        unmark,
        list,
        todo,
        deadline,
        event,
        delete
    }

    public static void main(String[] args) {
        //Scanner to scan user input.
        Scanner sc = new Scanner(System.in);

        // User input.
        String input = "";

        // Description of the task.
        String description = "";

        //Storage for the list function.
        List<Task> storage = new ArrayList<>();

        //Counter to count the number of items in the list.
        int counter = 0;

        //Logo of Duke
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Start of program.
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        try {
            counter = readAddFileContents(storage, "data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        while(true) {
            description = "";
            input = sc.nextLine();
            String[] inputArr = input.split(" ");
            System.out.println("____________________________________________________________");
            try {
                Commands userCommand = checkCommand(inputArr[0]);
                switch(userCommand) {
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        break;
                    case mark:
                        System.out.println("Nice! I've marked this task as done:");
                        storage.get(Integer.parseInt(inputArr[1]) - 1).mark();
                        System.out.println(storage.get(Integer.parseInt(inputArr[1]) - 1).toString());
                        break;
                    case unmark:
                        System.out.println("OK, I've marked this task as not done yet:");
                        storage.get(Integer.parseInt(inputArr[1]) - 1).unmark();
                        System.out.println(storage.get(Integer.parseInt(inputArr[1]) - 1).toString());
                        break;
                    case list:
                        int numbering = 1;
                        for (int i = 0; i < counter; i++) {
                            System.out.println(numbering + ". " + storage.get(i).toString());
                            numbering++;
                        }
                        break;
                    case todo:
                        for (int i = 1; i < inputArr.length; i++) {
                            description = description + inputArr[i];
                            if (i != inputArr.length - 1) description += " ";
                        }
                        storage.add(new Todo(checkDescription(description, "todo")));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(storage.get(counter).toString());
                        counter++;
                        System.out.println("Now you have " + counter + " task(s) in the list.");
                        break;
                    case deadline:
                        String deadline = "";
                        for (int i = 1; i < inputArr.length; i++) {
                            if (inputArr[i].charAt(0) == '/') {
                                i++;
                                while (i < inputArr.length) {
                                    deadline += inputArr[i];
                                    if (i != inputArr.length - 1) deadline += " ";
                                    i++;
                                }
                                break;
                            }
                            description = description + inputArr[i] + " ";
                        }
                        storage.add(new Deadline(checkDescription(description, "deadline"),
                                checkTime(deadline, "deadline", "by")));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(storage.get(counter).toString());
                        counter++;
                        System.out.println("Now you have " + counter + " task(s) in the list.");
                        break;
                    case event:
                        String from = "";
                        String to = "";
                        for (int i = 1; i < inputArr.length; i++) {
                            if (inputArr[i].charAt(0) == '/') {
                                i++;
                                while (i < inputArr.length) {
                                    if (inputArr[i].charAt(0) == '/') {
                                        i++;
                                        while (i < inputArr.length) {
                                            to += inputArr[i];
                                            if (i != inputArr.length - 1) to += " ";
                                            i++;
                                        }
                                        break;
                                    }
                                    from += inputArr[i] + " ";
                                    i++;
                                }
                                break;
                            }
                            description = description + inputArr[i] + " ";
                        }
                        storage.add(new Event(checkDescription(description, "event"),
                                checkTime(from, "event", "from"),
                                checkTime(to, "event", "to")));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(storage.get(counter).toString());
                        counter++;
                        System.out.println("Now you have " + counter + " task(s) in the list.");
                        break;
                    case delete:
                        Task deleted = storage.remove(Integer.parseInt(inputArr[1]) - 1);
                        counter--;
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deleted.toString());
                        System.out.println("Now you have " + counter + " task(s) in the list.");
                        break;
                }
                try {
                    writeToFile(storage, "data/duke.txt");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                if(userCommand.name().equals("bye")) {
                    break;
                }
            } catch (DukeException e) { // Catches the DukeException.
                    System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Checks for an empty description.
     * @param str The description of the task.
     * @param task The type of task.
     * @return The description of the task if it is not empty.
     * @throws DukeException If the description is empty.
     */
    public static String checkDescription(String str, String task) throws DukeException {
        if(str.equals("")) {
            String message = "☹ OOPS!!! The description of a " + task + " cannot be empty.";
            throw new DukeException(message);
        }
        return str;
    }

    /**
     * Checks for an empty time.
     * @param str The time for that specific task type.
     * @param task The current task.
     * @param type The type of timeline from the task. (ie, by, from, to).
     * @return The time if it is not empty.
     * @throws DukeException If there is an empty time.
     */
    public static String checkTime(String str, String task, String type) throws DukeException {
        if(str.equals("")) {
            String message = "☹ OOPS!!! The /" + type + " part of a " + task + " cannot be empty.";
            throw new DukeException(message);
        }
        return str;
    }

    /**
     * Checks for an invalid command.
     * @param command The command input by the user.
     * @return The command for enum Commands.
     * @throws DukeException If there is an invalid Command.
     */
    public static Commands checkCommand(String command) throws DukeException {
        boolean flag = true;
        for(Commands c : Commands.values()) {
            if(command.equals(c.name())) flag = false;
        }
        if(flag) {
            String message = "____________________________________________________________\n"
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw new DukeException(message);
        }
        return Commands.valueOf(command);
    }

    private static int readAddFileContents(List<Task> storage, String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner fileScanner = new Scanner(f);
        while(fileScanner.hasNext()) {
            String[] currArray = fileScanner.nextLine().split("\\|");
            boolean mark = false;
            if(currArray[1].trim().equals("1")) mark = true;
            if(currArray[0].trim().equals("T")) {
                Todo t = new Todo(currArray[2]);
                if(mark) {
                    t.mark();
                }
                storage.add(t);
            } else if(currArray[0].trim().equals("D")) {
                Deadline d = new Deadline(currArray[2], currArray[3]);
                if(mark) {
                    d.mark();
                }
                storage.add(d);
            } else {
                Event e = new Event(currArray[2], currArray[3], currArray[4]);
                if(mark) {
                    e.mark();
                }
                storage.add(e);
            }
        }
        return storage.size();
    }

    private static void writeToFile(List<Task> storage, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task element : storage) {
            String mark = "0";
            if(element.getStatusIcon().equals("X")) mark = "1";
            if(element instanceof Todo) {
                fw.write("T | " + mark + " | " + element.description);
            }
            if(element instanceof Deadline) {
                fw.write("D | " + mark + " | " + element.description + " | " + ((Deadline) element).by);
            }
            if(element instanceof Event){
                fw.write("E | " + mark + " | " + element.description + " | " + ((Event) element).from + " | " + ((Event) element).to);
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
