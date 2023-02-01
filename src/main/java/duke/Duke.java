package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

enum Action {
    Bye,
    Deadline,
    Delete,
    Event,
    Find,
    List,
    Mark,
    Todo,
    Unmark,
}

/**
 * Represents a Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialize a Duke with given information
     *
     * @param filepath where storage data will be stored
     * @throws FileNotFoundException if such filepath does not exist
     */
    public Duke(String filepath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Check if input array is empty or not.
     * For some types of tasks.
     *
     * @param arr    an array of the input.
     * @param action type of task given.
     * @throw DukeException if input array is empty.
     */

    private void run() throws IOException {
        ui.showWelcome();
        TaskList listOfAction = tasks;
        Scanner sc = new Scanner(System.in);
        Storage file = storage;
        String s = "";

        String newLine = sc.nextLine();
        String[] arr = newLine.split(" ");
        s = arr[0];
        int len = listOfAction.validLen();

        while (!s.equals("bye")) {
            String remaining = "";
            try {
                Action myAction = Action.valueOf(String.valueOf(s.charAt(0)).toUpperCase()
                        + s.substring(1));
                switch (myAction) {
                case Find:
                    ui.findWordIntro(arr, listOfAction.checkWord(arr[1]));
                    listOfAction.findWord(arr[1]);
                    break;

                case Bye:
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case List:
                    System.out.println("Here are the tasks in your list:");
                    listOfAction.list();
                    break;

                case Mark:
                    int num = Integer.parseInt(arr[1]) - 1;
                    listOfAction = listOfAction.mark(num);
                    file.overwrite(listOfAction);
                    break;

                case Unmark:
                    int num1 = Integer.parseInt(arr[1]) - 1;
                    listOfAction = listOfAction.unmark(num1);
                    file.overwrite(listOfAction);
                    break;

                case Delete:
                    num1 = Integer.parseInt(arr[1]) - 1;
                    if (listOfAction.checkValidIndex(num1)) {
                        System.out.println("Noted. I've removed this task:");
                        listOfAction = listOfAction.delete(num1);
                        len--;
                        System.out.println(String.format("Now you have %d "
                                + "tasks in the list", len));
                        file.overwrite(listOfAction);
                    } else {
                        System.out.println(new DukeException("OOPS!!! Invalid index!"));
                    }
                    break;

                case Todo:
                    System.out.println("Got it. I've added this task:");
                    remaining = new Parser().toDo(arr);
                    Todo newTask = new Todo(s, remaining, false);
                    listOfAction.add(newTask);
                    System.out.println(String.format("Now you have %d "
                            + "tasks in the list", len + 1));
                    len++;
                    file.overwrite(listOfAction);
                    break;

                case Deadline:
                    try {
                        System.out.println("Got it. I've added this task:");
                        String detail = new Parser().deadlineDetail(arr);
                        int pointer = new Parser().deadlineTimeIndex(arr);
                        for (int j = pointer; j < arr.length; j++) {
                            if (String.valueOf(arr[j]).equals("/")) {
                                remaining += "-";
                            } else {
                                remaining += arr[j];
                            }
                            if (j != arr.length - 1) {
                                remaining += " ";
                            }
                        }

                        Deadline newTaskDeadline = new Deadline(s, detail, remaining);
                        listOfAction.add(newTaskDeadline);
                        System.out.println(String.format("Now you have %d "
                                + "tasks in the list", len + 1));
                        len++;
                        file.overwrite(listOfAction);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case Event:
                    try {
                        System.out.println("Got it. I've added this task:");
                        int startIndex = new Parser()
                                .getEventStartTimeIndex(arr);
                        int endIndex = new Parser()
                                .getEventEndTimeIndex(arr, startIndex);
                        String detail = new Parser()
                                .getEventDetail(arr);
                        String start = (new Parser()
                                .getEventTime(arr, startIndex, endIndex))[0];
                        String end = (new Parser()
                                .getEventTime(arr, startIndex, endIndex))[1];
                        listOfAction.add(new Event("event",
                                detail, start, end));
                        System.out.println(String.format("Now you have %d "
                                + "tasks in the list", len + 1));
                        len++;
                        file.overwrite(listOfAction);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("OOPS!!! I'm sorry, "
                            + "but I don't know what that means :-(");
                    break;

                }
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(");
            }

            newLine = sc.nextLine();
            arr = newLine.split(" ");
            s = arr[0];
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/tasks.txt").run();
    }
}




