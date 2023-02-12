package duke;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Platform;
import task.TaskList;
import task.Task;
import task.Command;
import task.Parser;
import task.Event;
import task.Deadline;
import task.Todo;
import storage.Storage;
import ui.Ui;

/**
 * Duke is the body part of the chatbot we designed.
 */
public class Duke {
    protected final TaskList taskList;
    protected final ArrayList<String> commandList;
    protected Parser parser;
    protected Storage storage;
    protected Ui ui;
    protected String directoryPath;
    protected String dataFilePath;

    public Duke() {
        this.dataFilePath = "./data/duke.txt";
        this.directoryPath = "./data";
        this.commandList = new ArrayList<>();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage(directoryPath, dataFilePath);
        this.ui = new Ui();
    }

    /**
     * Adds the task to the taskList.
     *
     * @param task_given The task we want to add to the list.
     */
    public void addTask(Task task_given) {
        this.taskList.add(task_given);
    }

    /**
     * Returns the message of successfully adding a task to the taskList.
     *
     * @param task The task we want to add to the list.
     * @return Message of successful adding.
     */
    public String messageOfAdd(Task task) {
        return "Got it. I've added this task:\n " + task.toString() + "\nNow you have " +
                taskList.size() + " tasks in the list:D";
    }


    /**
     * Marks a task as done.
     *
     * @param index The index of the task we want to mark as done.
     * @return Message of marking a task as done.
     * @throws DukeException When any DukeException happens.
     */
    public String markAsDone(int index) throws DukeException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = this.taskList.get(index);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task we want to mark as undone.
     * @return Message of marking a task as undone.
     * @throws DukeException When any DukeException happens.
     */
    public String markAsUndone(int index) throws DukeException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = this.taskList.get(index);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Deletes a task from the taskList.
     *
     * @param index The index of the task we want to delete.
     * @return Message of deletion.
     * @throws WrongIndexException If the index is out of bound.
     */
    public String deleteMessage(int index) throws WrongIndexException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        return "Noted. I've removed this task:\n " + task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";

    }

    /**
     * Prints the matching tasks in the taskList.
     *
     * @param keywordList Keywords used for searching.
     * @return Tasks that contains the keyword.
     */
    public String findMessage(String... keywordList) {
        return "Here are the matching tasks in your list:\n" + taskList.getTasksWanted(keywordList);
    }

    /**
     * Loads the data stored in the disk.
     */
    public void loadDataFromDisk() {
        storage.loadData(commandList);
        for (String userInput: commandList) {
            String[] expressions = userInput.split(" ");
            String command = expressions[0];

            try {
                if (userInput.equals(parser.convertEnum(Command.LIST))) {
                    continue;
                } else if (command.equals(parser.convertEnum(Command.MARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    String tmp = markAsDone(index);
                } else if (command.equals(parser.convertEnum(Command.UNMARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    String tmp = markAsUndone(index);
                } else if (userInput.equals(parser.convertEnum(Command.BYE))) {
                    continue;
                } else if (command.equals(parser.convertEnum(Command.TODO))) {
                    parser.checkEmpty(userInput, command);
                    Todo todo_task = new Todo(userInput);
                    addTask(todo_task);
                    String tmp = messageOfAdd(todo_task);
                } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                    parser.checkEmpty(userInput, command);
                    Deadline ddl_task = new Deadline(userInput);
                    addTask(ddl_task);
                    String tmp = messageOfAdd(ddl_task);
                } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                    parser.checkEmpty(userInput, command);
                    Event event_task = new Event(userInput);
                    addTask(event_task);
                    String tmp = messageOfAdd(event_task);
                } else if (command.equals(parser.convertEnum(Command.DELETE))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    String tmp = deleteMessage(index);
                } else if (command.equals(parser.convertEnum(Command.FIND))) {
                    parser.checkEmpty(userInput, command);
                    String keywords = userInput.substring(5);
                    String[] keywordList = keywords.split(" ");
                    String tmp = findMessage(keywordList);
                } else {
                    throw new WeirdInputException();
                }
            } catch (WeirdInputException exc)  {
                String tmp = ui.separate(exc.toString());
            } catch (DukeException exc) {
                String tmp = ui.separate(exc.toString());
            }
        }
    }

    /**
     * Output the response to user input.
     * @param userInput Command input by the user.
     * @return Corresponding output.
     */
    public String getResponse(String userInput) {
        commandList.add(userInput);
        storage.saveToDisk(userInput + "\n");
        try {
            String[] expressions = userInput.split(" ");
            String command = expressions[0];
            String response = null;
            if (userInput.equals(parser.convertEnum(Command.LIST))) {
                response = taskList.printCurrentTasks();
            } else if (command.equals(parser.convertEnum(Command.MARK))) {
                parser.checkEmpty(userInput, command);
                String[] words = userInput.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                response = markAsDone(index);
            } else if (command.equals(parser.convertEnum(Command.UNMARK))) {
                parser.checkEmpty(userInput, command);
                String[] words = userInput.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                response = markAsDone(index);
            } else if (userInput.equals(parser.convertEnum(Command.BYE))) {
                Platform.exit();
            } else if (command.equals(parser.convertEnum(Command.TODO))) {
                parser.checkEmpty(userInput, command);
                Todo todo_task = new Todo(userInput);
                addTask(todo_task);
                response = messageOfAdd(todo_task);
            } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                parser.checkEmpty(userInput, command);
                Deadline ddl_task = new Deadline(userInput);
                addTask(ddl_task);
                response = messageOfAdd(ddl_task);
            } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                parser.checkEmpty(userInput, command);
                Event event_task = new Event(userInput);
                addTask(event_task);
                response = messageOfAdd(event_task);
            } else if (command.equals(parser.convertEnum(Command.DELETE))) {
                parser.checkEmpty(userInput, command);
                String[] words = userInput.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                response = deleteMessage(index);
            } else if (command.equals(parser.convertEnum(Command.FIND))) {
                parser.checkEmpty(userInput, command);
                String keywords = userInput.substring(5);
                String[] keywordList = keywords.split(" ");
                response = findMessage(keywordList);
            } else {
                throw new WeirdInputException();
            }
            return response;
        } catch (WeirdInputException exc) {
            return exc.toString();
        } catch (DukeException exc) {
            return exc.toString();
        }
    }

    /**
     * Runs the program and outputs corresponding results.
     */
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(ui.greeting());

        loadDataFromDisk();

        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            commandList.add(userInput);
            String[] expressions = userInput.split(" ");
            String command = expressions[0];

            try {
                if (userInput.equals(parser.convertEnum(Command.LIST))) {
                    System.out.println(ui.separate(taskList.printCurrentTasks()));
                } else if (command.equals(parser.convertEnum(Command.MARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(ui.separate(markAsDone(index)));
                } else if (command.equals(parser.convertEnum(Command.UNMARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(ui.separate(markAsDone(index)));
                } else if (userInput.equals(parser.convertEnum(Command.BYE))) {
                    break;
                } else if (command.equals(parser.convertEnum(Command.TODO))) {
                    parser.checkEmpty(userInput, command);
                    Todo todo_task = new Todo(userInput);
                    addTask(todo_task);
                    System.out.println(ui.separate(messageOfAdd(todo_task)));
                } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                    parser.checkEmpty(userInput, command);
                    Deadline ddl_task = new Deadline(userInput);
                    addTask(ddl_task);
                    System.out.println(ui.separate(messageOfAdd(ddl_task)));
                } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                    parser.checkEmpty(userInput, command);
                    Event event_task = new Event(userInput);
                    addTask(event_task);
                    System.out.println(ui.separate(messageOfAdd(event_task)));
                } else if (command.equals(parser.convertEnum(Command.DELETE))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(ui.separate(deleteMessage(index)));
                } else if (command.equals(parser.convertEnum(Command.FIND))) {
                    parser.checkEmpty(userInput, command);
                    String keyword = userInput.substring(5);
                    System.out.println(ui.separate(findMessage(keyword)));
                } else {
                    throw new WeirdInputException();
                }
            } catch (WeirdInputException exc) {
                System.out.println(ui.separate(exc.toString()));
            } catch (DukeException exc) {
                System.out.println(ui.separate(exc.toString()));
            }
        }

        System.out.println(ui.ending());

        String stringCommands = "";
        for (String command: commandList) {
            stringCommands += (command + "\n");
        }
        storage.saveToDisk(stringCommands + "\n");
    }
}
