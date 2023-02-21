package wessy;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import wessy.components.*;
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
    public void run() {
        startsUp();
        while (ui.hasNextLine()) {
            try {
                String userInput = ui.readNextLine();
                CmdType cmd = Parser.getCmd(userInput);
                if (cmd == null) {
                    throw new CommandNotFoundException();
                }
                UserInputChecker.checkSpacingAftCmd(userInput, cmd);

                switch (cmd) {

                case BYE:
                    ui.printByeMessage();
                    return;
                case LIST:
                    ui.printListOrFindMessage(tasks.printAsStr(), true);
                    break;

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
                    ui.printAddedMessage(newTask, tasks.getSize());
                    break;

                case MARK:
                case UNMARK:
                    checkBeforeParse(userInput, cmd);

                    boolean isMark = cmd == CmdType.MARK;
                    Task updatedTask = tasks.markOrUnmark(Parser.parseInt(userInput, cmd), isMark);
                    saveToStorage();
                    ui.printMarkUnmarkMessage(updatedTask, isMark);
                    break;

                case DELETE:
                    checkBeforeParse(userInput, cmd);

                    Task deletedTask = tasks.delete(Parser.parseInt(userInput, cmd));
                    saveToStorage();
                    ui.printDeleteMessage(deletedTask, tasks.getSize());
                    break;

                case FIND:
                    String target = userInput.substring(cmd.getStrLength() + 1);
                    ui.printListOrFindMessage(tasks.find(target), false);
                    break;

                case CLEAR:
                    tasks.clear();
                    saveToStorage();
                    ui.printClearMessage();
                    // Fallthrough
                }

            } catch (DateTimeParseException dtpe) {
                ui.handleException("Please enter the date (and time, if any) in the correct format.");
            } catch (SecurityException se) {
                ui.handleException("You do not have the permission to access the file.");
                se.printStackTrace();
            } catch (IOException ioe) {
                ui.handleException("There is some issue in the input-output operation.");
                ioe.printStackTrace();
            } catch (WessyException we) {
                ui.handleException(we.toString());
            } catch (Exception ex) {
                ui.handleException(ex.getMessage());
            }
        }
    }

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        new Wessy("data/savedTasks.txt").run();
    }

    /**
     *
     */
    public String startsUp() {
        return ui.printWelcomeMessage(tasks.printAsStr());
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



    public String respond(String userInput) {
        try {
            CmdType cmd = Parser.getCmd(userInput);
            if (cmd == null) {
                throw new CommandNotFoundException();
            }
            UserInputChecker.checkSpacingAftCmd(userInput, cmd);

            switch (cmd) {

                case BYE:
                    return ui.printByeMessage();
                case LIST:
                    return ui.printListOrFindMessage(tasks.printAsStr(), true);
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
                    return ui.printAddedMessage(newTask, tasks.getSize());

                case MARK:
                case UNMARK:
                    checkBeforeParse(userInput, cmd);

                    boolean isMark = cmd == CmdType.MARK;
                    Task updatedTask = tasks.markOrUnmark(Parser.parseInt(userInput, cmd), isMark);
                    saveToStorage();
                    return ui.printMarkUnmarkMessage(updatedTask, isMark);

                case DELETE:
                    checkBeforeParse(userInput, cmd);

                    Task deletedTask = tasks.delete(Parser.parseInt(userInput, cmd));
                    saveToStorage();
                    return ui.printDeleteMessage(deletedTask, tasks.getSize());

                case FIND:
                    String target = userInput.substring(cmd.getStrLength() + 1);
                    return ui.printListOrFindMessage(tasks.find(target), false);

                case CLEAR:
                    tasks.clear();
                    saveToStorage();
                    return ui.printClearMessage();
                    // Fallthrough
            }

        } catch (DateTimeParseException dtpe) {
            return ui.handleException("Please enter the date (and time, if any) in the correct format.");
        } catch (SecurityException se) {
            se.printStackTrace();
            return ui.handleException("You do not have the permission to access the file.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return ui.handleException("There is some issue in the input-output operation.");

        } catch (WessyException we) {
            return ui.printMessage(we.toString());
        } catch (Exception ex) {
            return ui.printMessage(ex.getMessage());
        }
        return ui.printMessage(new CommandNotFoundException().toString());
    }
}
