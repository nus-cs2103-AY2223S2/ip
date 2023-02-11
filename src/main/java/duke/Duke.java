package duke;

import java.util.ArrayList;

import command.Command;
import gui.Message;
import storage.Storage;
import task.TaskList;
import ui.Parser;
import ui.TextUi;

/**
 * duke.Duke is the class that responds to user enquiry.
 * It has other objects such as UI and storage, and connected these components
 * to process user-input commands, recording, carry out actual operations, and respond
 * to users.
 */
public class Duke {
    protected final String NAME = "Duke";
    protected final String RECORD_DIR = "./data";
    protected final String RECORD_NAME = "/duke.txt";
    protected final TextUi ui;
    protected final TaskList taskList;
    protected final ArrayList<String> commandList;
    protected final Storage storage;
    protected boolean hasExited;

    /**
     * Constructor
     */
    public Duke() {
        this.ui = new TextUi(NAME);
        this.taskList = new TaskList();
        this.storage = new Storage(RECORD_DIR, RECORD_NAME);
        this.commandList = new ArrayList<>();
        this.hasExited = false;
        loadRecord();
    }

    /**
     * The process that interacts with user
     */
    public void runWithTextUi() {
        ui.showWelcome();

        boolean isRunning = true;

        while (isRunning) {
            String inMsg = ui.getUserInput();
            try {
                isRunning = handleCommandReturnStatus(inMsg, false);
                if (isRunning) {
                    commandList.add(inMsg);
                }
            } catch (DukeException | AssertionError e) {
                ui.printStructuredString(e.toString());
            } catch (NumberFormatException e) {
                ui.printStructuredString("Please specify the index.");
            }
        }

        storage.saveToFile(getCommandListString());
    }

    /**
     * Runs command and handles exceptions
     * @param inMsg the user-input command
     * @return the response message to print out
     */
    public Message handleCommandWithException(String inMsg) {
        Message responseMessage;

        try {
            String responseString = handleCommandReturnResponse(inMsg);
            boolean isRunning = !inMsg.equalsIgnoreCase("bye"); // hardcode, not ideal

            if (isRunning) {
                commandList.add(inMsg);
            } else {
                hasExited = true;
                storage.saveToFile(getCommandListString());
            }

            responseMessage = new Message(responseString, false);
        } catch (DukeException e) {
            responseMessage = new Message(e.toString(), true);
        } catch (NumberFormatException e) {
            responseMessage = new Message("Please specify the index.", true);
        }

        return responseMessage;
    }

    /**
     * Returns whether the program has exited.
     * @return whether the program has exited.
     */
    public boolean isExited() {
        return hasExited;
    }

    /**
     * Returns the name of the robot
     *
     * @return the name
     */
    public String getName() {
        return NAME;
    }

    /**
     * Load record file
     */
    public void loadRecord() {
        storage.loadRecordIfExists(commandList);
        commandList.forEach(x -> {
            try {
                handleCommandReturnStatus(x, true);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        });
    }

    /**
     * Handles the input string from the user. It checks whether the input is
     * some command and performs the corresponding operation.
     *
     * In case of invalid or incomplete command, it throws an error message
     * to the user and prompts for a new command.
     *
     * @param inMsg         the input message from the user
     * @param suppressPrint suppress print out message or not
     * @return whether the program continues or not
     * @throws DukeException when the command is unknown
     */
    public boolean handleCommandReturnStatus(String inMsg, boolean suppressPrint) throws DukeException {
        Command command = Parser.parseCommand(inMsg, suppressPrint);
        command.execute(taskList, ui);
        return !command.isExit();
    }

    /**
     * Handles a string command
     * @param inMsg the user-input command
     * @return the response from Duke
     * @throws DukeException when the command is unknown or incomplete
     */
    public String handleCommandReturnResponse(String inMsg) throws DukeException {
        Command command = Parser.parseCommand(inMsg, true);
        return command.execute(taskList);
    }

    /**
     * Returns the string representation of all history commands.
     * This is different from the string of all tasks.
     *
     * @return the string containing all commands
     */
    public String getCommandListString() {
        return commandList.stream()
                .reduce("", (cumulativeString, string) -> cumulativeString + string + "\n");
    }

    /**
     * Returns welcome message at startup
     * @return welcome message
     */
    public String getWelcomeMessage() {
        return String.format("Hello! I'm %s\nWhat can I do for you?", NAME);
    }
}
