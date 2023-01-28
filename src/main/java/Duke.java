import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Duke {

    private TaskList taskList = new TaskList();
    private Storage storage = new Storage();

    public void Duke() {
        taskList = new TaskList();
        storage = new Storage();
    }

    public void run() {
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
        boolean isRunning = true;
        storage.loadSave(taskList);
        while (isRunning) {
            Scanner input = new Scanner(System.in);
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
                        if (taskList.getSize() == 0) {
                            System.out.println("You have no tasks in your list!");
                            break;
                        }
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.getSize(); i++) {
                            Task task = taskList.getAtIndex(i);
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
                        if (taskIndex < 0 || taskIndex > taskList.getSize()) {
                            System.out.println("invalid task index!");
                            break;
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        Task task = taskList.getAtIndex(taskIndex);
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
                        if (taskIndex < 0 || taskIndex > taskList.getSize()) {
                            System.out.println("invalid task index!");
                            break;
                        }
                        System.out.println("OK, I've marked this task as not done yet:");
                        task = taskList.getAtIndex(taskIndex);
                        task.setDone(false);
                        System.out.println(task);
                        break;
                    case "todo":
                        if (splitInput.length == 1) {
                            throw new DukeException("'todo' requires additional arguments!");
                        }
                        taskList.addTask(new ToDo(splitInput[1]));
                        System.out.println("Added Todo task:\n" + taskList.getAtIndex(taskList.getSize() - 1));
                        System.out.println("Now you have " + taskList.getSize() + " tasks in the list!");
                        break;
                    case "deadline":
                        if (splitInput.length == 1) {
                            throw new DukeException("'deadline' requires additional arguments!");
                        }
                        Integer indexBy = splitInput[1].indexOf("/by ");
                        taskList.addTask(
                            new Deadline(splitInput[1].substring(0, indexBy - 1),
                                splitInput[1].substring(indexBy + 4)));
                        System.out.println("Added Deadline task:\n" + taskList.getAtIndex(taskList.getSize() - 1));
                        System.out.println("Now you have " + taskList.getSize() + " tasks in the list!");
                        break;
                    case "event":
                        if (splitInput.length == 1) {
                            throw new DukeException("'event' requires additional arguments!");
                        }
                        Integer indexFrom = splitInput[1].indexOf("/from ");
                        Integer indexTo = splitInput[1].indexOf("/to ");
                        taskList.addTask(new Event(
                            splitInput[1].substring(0, indexFrom - 1),
                            splitInput[1].substring(indexFrom + 6, indexTo - 1),
                            splitInput[1].substring(indexTo + 4)));
                        System.out.println("Added Event task:\n" + taskList.getAtIndex(taskList.getSize() - 1));
                        System.out.println("Now you have " + taskList.getSize() + " tasks in the list!");
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

                        if (taskIndex < 0 || taskIndex > taskList.getSize()) {
                            System.out.println("invalid task index!");
                            break;
                        }
                        System.out.println("Gotcha, removed this task: ");
                        System.out.println(taskList.getAtIndex(taskIndex));
                        taskList.removeAtIndex(taskIndex.intValue());
                        break;
                    default:
                        throw new DukeException("Please enter a valid command!");
                }
                storage.updateSave(taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
