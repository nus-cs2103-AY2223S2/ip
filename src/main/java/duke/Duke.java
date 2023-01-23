package duke;

import command.Command;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import task.Event;
import task.Deadline;

import ui.Parser;
import ui.TextUi;

import java.util.ArrayList;


/**
 * duke.Duke is the class that responds to user enquiry.
 * It has other objects such as UI and storage, and connected these components
 * to process user-input commands, recording, carry out actual operations, and respond
 * to users.
 */
public class Duke {
    protected final TextUi ui;
    protected final TaskList taskList;
    protected final String myName;
    protected final ArrayList<String> commandList;
    protected final String RECORD_DIR = "./data";
    protected final String RECORD_NAME = "/duke.txt";
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructor
     */
    public Duke() {
        this.myName = "Duke";
        this.ui = new TextUi(myName);
        this.taskList = new TaskList();
        this.storage = new Storage(RECORD_DIR, RECORD_NAME);
        this.parser = new Parser();
        this.commandList = new ArrayList<>();
        loadRecord();
    }

    /**
     * The process that interacts with user
     */
    public void run() {
        ui.showWelcome();

        String inMsg = null;
        while (true) {
            inMsg = ui.getUserInput();
            if (ui.isEnd(inMsg)) {
                break;
            }

            try {
                handleCommand(inMsg, false);
                addCommandList(inMsg);
            } catch (DukeException e) {
                ui.printStructuredString(e.toString());
            } catch (NumberFormatException e) {
                ui.printStructuredString("Please enter a number.");
            }
        }

        storage.saveToFile(getCommandListString());
        ui.sayGoodbye();
    }

    /**
     * Returns the name of the robot
     *
     * @return the name
     */
    public String getName() {
        return myName;
    }

    /**
     * Load record file
     */
    public void loadRecord() {
        storage.loadRecordIfExists(commandList);
        for (String s : commandList) {
            try {
                handleCommand(s, true);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Handles the input string from the user. It checks whether the input is
     * some command and performs the corresponding operation.
     *
     * In case of invalid or incomplete command, it throws an error message
     * to the user and prompts for a new command.
     *
     * @param inMsg:         the input message from the user
     * @param suppressPrint: suppress print out message or not
     * @throws DukeException when the command is unknown
     */
    public void handleCommand(String inMsg, boolean suppressPrint) throws DukeException {
        String stringToPrint = "";
        if (parser.checkCommand(inMsg, Command.LIST)) {
            stringToPrint = listTasks();
        } else if (parser.checkCommand(inMsg, Command.MARK)) {
            int idx = Integer.parseInt(inMsg.substring(5)) - 1;
            stringToPrint = markTaskDone(idx);
        } else if (parser.checkCommand(inMsg, Command.UNMARK)) {
            int idx = Integer.parseInt(inMsg.substring(7)) - 1;
            stringToPrint = unmarkTaskDone(idx);
        } else if (parser.checkCommand(inMsg, Command.TODO)) {
            String todoName = parser.getCommandContent(inMsg, Command.TODO);
            ToDo todo = new ToDo(todoName);
            stringToPrint = addTask(todo);
        } else if (parser.checkCommand(inMsg, Command.DEADLINE)) {
            String deadlineContent = parser.getCommandContent(inMsg, Command.DEADLINE);
            Deadline ddl = new Deadline(deadlineContent);
            stringToPrint = addTask(ddl);
        } else if (parser.checkCommand(inMsg, Command.EVENT)) {
            String eventContent = parser.getCommandContent(inMsg, Command.EVENT);
            Event event = new Event(eventContent);
            stringToPrint = addTask(event);
        } else if (parser.checkCommand(inMsg, Command.DELETE)) {
            String indexToDelete = parser.getCommandContent(inMsg, Command.DELETE);
            stringToPrint = deleteTask(Integer.parseInt(indexToDelete));
        } else if (parser.checkCommand(inMsg, Command.FIND)) {
            String keyword = parser.getCommandContent(inMsg, Command.FIND);
            stringToPrint = find(keyword);
        } else {
                throw new DukeException("  OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (!suppressPrint) {
            ui.printStructuredString(stringToPrint);
        }
    }

    /**
     * Returns the string representation to be printed out when the command "list" is invoked
     *
     * @return the string representation of the message
     */
    public String listTasks() {
        String taskListString = "Here are the tasks in your list:\n" +
                taskList.getTaskListString(true);
        return taskListString;
    }

    /**
     * Add a task to the list
     *
     * @param task: a task to add
     * @return the string response after adding a task
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return String.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the list.",
                task,
                taskList.size());
    }

    /**
     * Marks a task as done by index
     *
     * @param idx: index of the task
     * @return the string message to print out
     */
    public String markTaskDone(int idx) {
        Task t = taskList.get(idx);
        t.markDone();
        return String.format("Nice! I've marked this task as done:\n  %s", t);
    }

    /**
     * Marks a task as undone by index
     *
     * @param idx: index of the task
     * @return the string message to print out
     */
    public String unmarkTaskDone(int idx) {
        Task t = this.taskList.get(idx);
        t.unmarkDone();
        return String.format("OK, I've marked this task as not done yet:\n  %s", t);
    }

    /**
     * Deletes a task by index
     *
     * @param idx: the index of the task
     * @return the string message to print out
     */
    public String deleteTask(int idx) {
        idx = idx - 1;   // count from zero
        Task t = taskList.get(idx);
        taskList.remove(idx);
        return String.format("Noted. I've removed this task:\n  %s\n" +
                "Now you have %d tasks in the list.", t, taskList.size());
    }

    /**
     * Adds user command to a list. The list will be used for
     * saving to local files as history.
     *
     * @param string: the user-input command
     */
    public void addCommandList(String string) {
        commandList.add(string);
    }

    /**
     * Returns the string representation of all history commands.
     * This is different from the string of all tasks.
     *
     * @return the string containing all commands
     */
    public String getCommandListString() {
        String string = "";
        for (String s : commandList) {
            string = string + s + "\n";
        }
        return string;
    }

    /**
     * Return the string representation of the list of tasks
     * whose names contain the given keyword.
     * @param string: the keyword
     * @return the string of the task list
     */
    public String find(String string) {
        String taskListString = "Here are the matching tasks in your list:\n" +
                taskList.getTaskNameContains(string).getTaskListString(true);
        return taskListString;
    }
}
