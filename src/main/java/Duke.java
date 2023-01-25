import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        introMessage();

        Checker checker = new Checker();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        ArrayList<Task> lstOfItems = new ArrayList<>();
        String path = "src/data/duke.txt";


        while (!checker.checkEnd(userInput)) {
            if (checker.checkListRequest(userInput)) {
                tasks.printContents();
            } else if (checker.checkMarkRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                tasks.markTask(itemNo);
            } else if (checker.checkDeleteRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                tasks.deleteTask(itemNo);
            } else {
                String[] terms = userInput.split(" ");
                Task newTask;
                if (terms[0].equals("todo")) {
                    try {
                        if (terms.length == 1) {
                            String error = "The description of a todo cannot be empty";
                            throw new DukeException(error);
                        }
                        newTask = new Todo(userInput.substring(5));
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                } else if (terms[0].equals("deadline")) {
                    String[] splitBySlash = userInput.split("/");
                    try {
                        if (splitBySlash.length != 2) {
                            throw new DukeException("Wrong format for deadline Task");
                        }
                        String description = splitBySlash[0].substring(9);
                        String by = splitBySlash[1].substring(3);
                        newTask = new Deadline(description, by);
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }

                } else if (terms[0].equals("event")) {
                    String[] splitBySlash = userInput.split("/");
                    try {
                        if (splitBySlash.length != 3) {
                            throw new DukeException("Wrong format for event Task");
                        }
                        String description = splitBySlash[0].substring(6);
                        String from = splitBySlash[1].substring(5);
                        String to = splitBySlash[2].substring(3);
                        newTask = new Event(description, from, to);
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                } else {
                    try {
                        throw new DukeException("I don't know what that means.");
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                }

            }
            userInput = scan.nextLine();
        }
        storage.addToFile(tasks);
        endMessage();

    }

    public static void introMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void endMessage() {
        System.out.println("Bye. Hope to see you again!");
    }

    public static void addTask(ArrayList<Task> lstOfItems, Task newTask) {
        System.out.println("Got it. I have added: ");
        System.out.println(newTask);
        lstOfItems.add(newTask);
        System.out.print("Now you have " + String.valueOf(lstOfItems.size()));
        if (lstOfItems.size() == 1) {
            System.out.print(" task");
        } else {
            System.out.print(" tasks");
        }
        System.out.println(" in the list");
    }

    public static void addToFile(ArrayList<Task> lstOfItems) {
        String path = "src/data/duke.txt";
        // Idea for the following code snippet is taken from:
        // https://stackoverflow.com/questions/1053467/how-do-i-save-a-string-to-a-text-file-using-java
        try {
            ArrayList<String> lst = new ArrayList<>();
            lst.add(String.valueOf(lstOfItems.size()));
            for (int i = 0; i < lstOfItems.size(); i++) {
                Task current = lstOfItems.get(i);
                lst.add(current.parse());
            }
            Files.write(Paths.get(path), lst);
        } catch (IOException err) {
            System.out.println(err);
        }
    }
}
