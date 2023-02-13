package duke;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Platform;
import storage.Storage;
import task.Command;
import task.Deadline;
import task.Event;
import task.Parser;
import task.Task;
import task.TaskList;
import task.Todo;
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

    /**
     * Constructor
     */
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
     * @param taskGiven The task we want to add to the list.
     */
    public void addTask(Task taskGiven) {
        this.taskList.add(taskGiven);
    }

    /**
     * Returns the message of successfully adding a task to the taskList.
     *
     * @param task The task we want to add to the list.
     * @return Message of successful adding.
     */
    public String messageOfAdd(Task task) {
        return "Got it. I've added this task:\n " + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list:D";
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
        return "Noted. I've removed this task:\n " + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";

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
                    Todo todoTask = new Todo(userInput);
                    addTask(todoTask);
                    String tmp = messageOfAdd(todoTask);
                } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                    parser.checkEmpty(userInput, command);
                    Deadline ddlTask = new Deadline(userInput);
                    addTask(ddlTask);
                    String tmp = messageOfAdd(ddlTask);
                } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                    parser.checkEmpty(userInput, command);
                    Event eventTask = new Event(userInput);
                    addTask(eventTask);
                    String tmp = messageOfAdd(eventTask);
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
                Todo todoTask = new Todo(userInput);
                addTask(todoTask);
                response = messageOfAdd(todoTask);
            } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                parser.checkEmpty(userInput, command);
                Deadline ddlTask = new Deadline(userInput);
                addTask(ddlTask);
                response = messageOfAdd(ddlTask);
            } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                parser.checkEmpty(userInput, command);
                Event eventTask = new Event(userInput);
                addTask(eventTask);
                response = messageOfAdd(eventTask);
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
                    Todo todoTask = new Todo(userInput);
                    addTask(todoTask);
                    System.out.println(ui.separate(messageOfAdd(todoTask)));
                } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                    parser.checkEmpty(userInput, command);
                    Deadline ddlTask = new Deadline(userInput);
                    addTask(ddlTask);
                    System.out.println(ui.separate(messageOfAdd(ddlTask)));
                } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                    parser.checkEmpty(userInput, command);
                    Event eventTask = new Event(userInput);
                    addTask(eventTask);
                    System.out.println(ui.separate(messageOfAdd(eventTask)));
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
