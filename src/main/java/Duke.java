import java.util.*;
import java.time.LocalDate;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    private static final String FILE_DESTINATION = "data/duke.txt";
    private static TaskList taskList;
    public static void main(String[] args) throws DukeException {
        TextUi TextUi = new TextUi();
        Parser Parser = new Parser();
        Storage Storage = new Storage();
        taskList = new TaskList();
        File file = new File(FILE_DESTINATION);
        Storage.readSavedFile(file, taskList); // loads saved strings in duke.txt to tasklist
        TextUi.getWelcomeMessage();
        String input;

        while (!(input = TextUi.in.nextLine()).equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("____________________");
                    if (taskList.isEmpty()) {
                        System.out.println("You currently have no task.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.getArraySize(); i++) {
                            Task currTask = taskList.getTask(i);
                            int taskIndex = i + 1;
                            System.out.println(taskIndex + ". " + currTask);
                        }
                    }
                    System.out.println("____________________");
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    int indexToDelete = Integer.parseInt(input.substring(7)) - 1;
                    if (indexToDelete < taskList.getArraySize()) {
                        TextUi.getTaskRemovedMessage(taskList.getTask(indexToDelete),taskList.getArraySize() - 1);
                        taskList.removeTask(indexToDelete);
                        Storage.saveTaskListToStorage(file, taskList);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                    int indexToMark = Integer.parseInt(input.substring(5)) - 1;
                    if (indexToMark < taskList.getArraySize()) {
                        Task toMark = taskList.getTask(indexToMark);
                        toMark.markAsDone();
                        Storage.saveTaskListToStorage(file, taskList);
                        TextUi.getCustomMessage("Nice! I've marked this task as done:\n" + toMark);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                    int indexToUnmark = Integer.parseInt(input.substring(7)) - 1;
                    if (indexToUnmark < taskList.getArraySize()) {
                        Task toUnmark = taskList.getTask(indexToUnmark);
                        toUnmark.markAsUndone();
                        Storage.saveTaskListToStorage(file, taskList);
                        TextUi.getCustomMessage("OK, I've marked this task as not done yet:\n" + toUnmark);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else {
                    if (input.length() >= 4 && input.substring(0,4).equals("todo")) {
                        String check = Parser.removeWhiteSpaces(input);
                        if (check.equals("todo")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Task newTask = new ToDo(input.substring(5, input.length()));
                        taskList.addTask(newTask);
                        TextUi.getTaskAddedMessage(newTask, taskList.getArraySize());
                    } else if (input.length() >= 5 && input.substring(0,5).equals("event")) {
                        String check = Parser.removeWhiteSpaces(input);
                        if (check.equals("event")) {
                            throw new DukeException("The description of a event cannot be empty.");
                        }
                        String[] str = input.substring(6).split("/");
                        Task newTask = new Event(str[0].substring(0, str[0].length() - 1)
                                , LocalDate.parse(str[1].substring(5, str[1].length() - 1))
                                , LocalDate.parse(str[2].substring(3)));
                        taskList.addTask(newTask);
                        TextUi.getTaskAddedMessage(newTask, taskList.getArraySize());
                    } else if (input.length() >= 8 && input.substring(0,8).equals("deadline")) {
                        String check = Parser.removeWhiteSpaces(input);
                        if (check.equals("deadline")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        String[] str = input.substring(9).split("/");
                        Task newTask = new Deadline(str[0].substring(0, str[0].length() - 1)
                                , LocalDate.parse(str[1].substring(3)));
                        taskList.addTask(newTask);
                        TextUi.getTaskAddedMessage(newTask, taskList.getArraySize());
                        Storage.saveTaskListToStorage(file, taskList);
                    } else {
                        throw new DukeException("I'm sorry, I don't know what that means!");
                    }
                    Storage.saveTaskListToStorage(file, taskList);
                }
            } catch (DukeException dukeException) {
                TextUi.getCustomMessage(dukeException.getMessage());
            }
        }
        TextUi.getGoodbyeMessage();
    }
}
