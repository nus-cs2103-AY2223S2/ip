package wessy;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import wessy.components.*;
import wessy.exceptions.TimeSpecifierException;
import wessy.exceptions.UnspecifiedTimeException;
import wessy.exceptions.integer.EmptyListException;
import wessy.exceptions.integer.InvalidIntegerException;
import wessy.exceptions.integer.NotPositiveIntegerException;
import wessy.task.Task;

import wessy.exceptions.WessyException;
import wessy.exceptions.CommandNotFoundException;

import wessy.exceptions.numofinput.MissingInputException;
import wessy.exceptions.numofinput.TooManyInputException;

import wessy.exceptions.integer.NotAnIntegerException;

/**
 *
 */
public class Wessy {
    private static final String CORRECT_FORMAT_MSG = "Please enter the date (and time, if any) in the correct format.";
    private static final String PERMISSION_MSG = "You do not have the permission to access the file.";
    private static final String IO_MSG = "There is some issue in the input-output operation.";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an instance of Wessy.
     *
     * @param filePath
     */
    public Wessy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (IOException | SecurityException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    public Wessy() {
        this("data/savedTasks.txt");
    }

    /**
     *
     */
    public String startsUp() {
        return ui.getWelcomeMessage(tasks.printAsStr());
    }

    /**
     * A helper function
     *
     * @param userInput
     * @param cmd
     * @throws MissingInputException
     * @throws NotAnIntegerException
     * @throws TooManyInputException
     */
    void checkBeforeParse(String userInput, CmdType cmd) throws MissingInputException, NotAnIntegerException,
            TooManyInputException {
        UserInputChecker.checkMissingInput(userInput, cmd);
        UserInputChecker.checkNotNumerical(userInput, cmd);
        UserInputChecker.checkTooManyInputs(userInput, cmd);
    }

    /**
     * A helper function
     *
     * @throws IOException
     */
    void saveToStorage() throws IOException {
        storage.save(tasks.saveAsStr());
    }

    /**
     *
     */
    public String respond(String userInput) {
        try {
            int oldSize = tasks.getSize();
            CmdType cmd = Parser.getCmd(userInput);

            checkForUnknownCmd(cmd);
            UserInputChecker.checkSpacingAftCmd(userInput, cmd);

            assert cmd != null;
            assert cmd == CmdType.BYE || cmd == CmdType.LIST || cmd == CmdType.TODO ||
                    cmd == CmdType.DEADLINE || cmd == CmdType.EVENT || cmd == CmdType.MARK ||
                    cmd == CmdType.UNMARK || cmd == CmdType.DELETE || cmd == CmdType.CLEAR;

            return triage(userInput, cmd);

        } catch (DateTimeParseException dtpe) {
            return ui.handleException(CORRECT_FORMAT_MSG);
        } catch (SecurityException se) {
            se.printStackTrace();
            return ui.handleException(PERMISSION_MSG);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return ui.handleException(IO_MSG);

        } catch (WessyException we) {
            return ui.getMessage(we.toString());

        } catch (Exception ex) {
            return ui.getMessage(ex.getMessage());
        }
    }

    private String triage(String userInput, CmdType cmd) throws MissingInputException, TimeSpecifierException,
            UnspecifiedTimeException, IOException, TooManyInputException, NotAnIntegerException,
            NotPositiveIntegerException, InvalidIntegerException, EmptyListException {

        switch (cmd) {

            // Commands that do not require any argument
            case BYE:
                return ui.getByeMessage();
            case LIST:
                return ui.getListOrFindMessage(tasks.printAsStr(), true);

            // Task-creating commands
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:

                UserInputChecker.checkMissingInput(userInput, cmd);
                UserInputChecker.checkMissingKeyword(userInput, cmd);
                if (cmd == CmdType.DEADLINE) {
                    UserInputChecker.checkDeadlineMissingInput(userInput);
                } else if (cmd == CmdType.EVENT) {
                    UserInputChecker.checkEventMissingInput(userInput);
                }

                String[] taskComponents = Parser.getTaskComponents(userInput, cmd);
                Task newTask = tasks.add(taskComponents);
                saveToStorage();
                return ui.getAddedMessage(newTask, tasks.getSize());

            // Task's status manipulating commands
            case MARK:
                // Fallthrough
            case UNMARK:

                checkBeforeParse(userInput, cmd);

                boolean isMark = cmd == CmdType.MARK;
                Task updatedTask = tasks.markOrUnmark(Parser.parseInt(userInput, cmd), isMark);
                saveToStorage();
                return ui.getMarkUnmarkMessage(updatedTask, isMark);

            case DELETE:
                checkBeforeParse(userInput, cmd);

                Task deletedTask = tasks.delete(Parser.parseInt(userInput, cmd));
                saveToStorage();
                return ui.getDeleteMessage(deletedTask, tasks.getSize());

            case FIND:
                String target = userInput.substring(cmd.getStrLength() + 1);
                return ui.getListOrFindMessage(tasks.find(target), false);

            // Not covered in deliverables. Might be useful for debugging or future extended features.
            case CLEAR:
                tasks.clear();
                saveToStorage();
                return ui.getClearMessage();
        }
        return handleOtherCases();
    }

    private String handleOtherCases() {
        return ui.getMessage(new CommandNotFoundException().toString());
    }

    private void checkForUnknownCmd(CmdType cmd) throws CommandNotFoundException {
        if (cmd == null) {
            throw new CommandNotFoundException();
        }
    }
}
