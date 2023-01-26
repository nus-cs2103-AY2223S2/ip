package duke;

import duke.command.*;
import duke.task.*;

import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.io.FileNotFoundException;
import java.io.IOException;


enum action {
    bye,
    list,
    mark,
    unmark,
    todo,
    deadline,
    event,
    delete,
    find
}
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }
    static void checkEmptyAction(String[] arr, String action) throws DukeException {
        if (arr.length <= 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", action));
        }
    }

    public void run() {
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
                action myAction = action.valueOf(s);
                switch(myAction) {
                    case find:
                        try {
                            checkEmptyAction(arr, "find");
                            ui.findWordIntro(arr, listOfAction.checkWord(arr[1]));
                            listOfAction.findWord(arr[1]);
                        } catch (duke.DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        break;

                    case list:
                        System.out.println("Here are the tasks in your list:");
                        listOfAction.list();
                        break;

                    case mark:
                        try {
                            checkEmptyAction(arr, "mark");
                            int num = Integer.parseInt(arr[1]) - 1;
                            listOfAction = listOfAction.mark(num);
                            file.overwrite(listOfAction);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case unmark:
                        try {
                            checkEmptyAction(arr, "unmark");
                            int num1 = Integer.parseInt(arr[1]) - 1;
                            listOfAction = listOfAction.unmark(num1);
                            file.overwrite(listOfAction);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case delete:
                        try {
                            checkEmptyAction(arr, "delete");
                            int num1 = Integer.parseInt(arr[1]) - 1;
                            if (listOfAction.checkValidIndex(num1)) {
                                System.out.println("Noted. I've removed this task:");
                                listOfAction = listOfAction.delete(num1);
                                len--;
                                System.out.println(String.format("Now you have %d tasks in the list", len));
                                file.overwrite(listOfAction);
                            } else {
                                System.out.println(new DukeException("OPPS!!! Invalid index!"));
                            }
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case todo:
                        try {
                            checkEmptyAction(arr, "todo");
                            System.out.println("Got it. I've added this task:");
                            remaining = new Parser().toDo(arr);
                            Todo newTask = new Todo(s, remaining, false);
                            listOfAction.add(newTask); //String.format("[T][ ] %s", remaining);
                            //System.out.println(listOfAction[len]);
                            System.out.println(String.format("Now you have %d tasks in the list", len + 1));
                            len++;
                            file.overwrite(listOfAction);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case deadline:
                        try {
                            checkEmptyAction(arr, "deadline");
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

                            Deadline newTask = new Deadline(s, detail, remaining);
                            listOfAction.add(newTask); //String.format("[D][ ]%s", remaining);
                            System.out.println(String.format("Now you have %d tasks in the list", len + 1));
                            len++;
                            file.overwrite(listOfAction);

                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case event:
                        try {
                            checkEmptyAction(arr, "event");
                            System.out.println("Got it. I've added this task:");
                            //int k = 0;
                            int startIndex = new Parser().getEventStartTimeIndex(arr);
                            int endIndex = new Parser().getEventEndTimeIndex(arr, startIndex);
                            String detail = new Parser().getEventDetail(arr);

                            String start = (new Parser().getEventTime(arr, startIndex, endIndex))[0];
                            String end = (new Parser().getEventTime(arr, startIndex, endIndex))[1];
                            listOfAction.add(new Event("event", detail, start, end)); //String.format("[E][ ]%s", remaining);
                            System.out.println(String.format("Now you have %d tasks in the list", len + 1));
                            len++;
                            file.overwrite(listOfAction);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
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




