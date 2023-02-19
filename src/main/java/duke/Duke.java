package duke;

import java.util.ArrayList;

import command.Command;
import command.ExitCommand;
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
    protected static final String NAME = "TaskWizard";
    protected static final String RECORD_DIR = "./data";
    protected static final String RECORD_NAME = "/duke.txt";
    protected static final String NUMBER_FORMAT_ERROR = "Please specify the index.";
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
                saveCommandAfterRunning(inMsg, isRunning);
            } catch (DukeException | AssertionError e) {
                ui.printStructuredString(e.toString());
            }
        }
    }

    /**
     * Runs command and handles exceptions
     * @param inMsg the user-input command
     * @return the response message to print out
     */
    public Message handleCommandWithException(String inMsg) {
        Message responseMessage;

        try {
            String responseString = runCommandReturnResponse(inMsg);
            boolean isRunning = !ExitCommand.isByeString(inMsg);
            saveCommandAfterRunning(inMsg, isRunning);
            responseMessage = new Message(responseString, false);
        } catch (DukeException e) {
            responseMessage = new Message(e.toString(), true);
        }

        return responseMessage;
    }

    /**
     * Saves the command to the list and saves the command list to file
     * after running a command
     * @param command the command to run
     * @param isRunning whether the program should be stopped or is still running
     */
    protected void saveCommandAfterRunning(String command, boolean isRunning) {
        if (isRunning) {
            commandList.add(command);
        } else {
            hasExited = true;
            storage.saveToFile(getCommandListString());
        }
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
        return !command.getIsExit();
    }

    /**
     * Handles a string command
     * @param inMsg the user-input command
     * @return the response from Duke
     * @throws DukeException when the command is unknown or incomplete
     */
    public String runCommandReturnResponse(String inMsg) throws DukeException {
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
