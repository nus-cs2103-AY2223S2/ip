package wessy;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import wessy.task.Task;

import wessy.exceptions.WessyException;
import wessy.exceptions.CommandNotFoundException;

import wessy.exceptions.numofinput.MissingInputException;
import wessy.exceptions.numofinput.TooManyInputException;

import wessy.exceptions.integer.NotAnIntegerException;

public class Wessy {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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
                    ui.printListMessage(tasks.getSize(), tasks.printAsStr());
                    break;

                case TODO:
                    // Fallthrough
                case DEADLINE:
                    // Fallthrough
                case EVENT:

                    UserInputChecker.checkMissingInput(userInput, cmd);
                    UserInputChecker.checkMissingKeyword(userInput, cmd);
                    if (cmd == CmdType.DEADLINE) {
                        UserInputChecker.checkDeadlineMissingInput(userInput, cmd);
                    } else if (cmd == CmdType.EVENT) {
                        UserInputChecker.checkEventMissingInput(userInput, cmd);
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

    public static void main(String[] args) {
        new Wessy("data/savedTasks.txt").run();
    }

    // HELPER FUNCTIONS
    private void startsUp() {
        ui.printWelcomeMessage(tasks.printAsStr(), tasks.getSize());
    }

    void saveToStorage() throws IOException {
        storage.save(tasks.saveAsStr());
    }

    void checkBeforeParse(String userInput, CmdType cmd) throws MissingInputException, NotAnIntegerException,
            TooManyInputException {
        UserInputChecker.checkMissingInput(userInput, cmd);
        UserInputChecker.checkNotNumerical(userInput, cmd);
        UserInputChecker.checkTooManyInputs(userInput, cmd);
    }
}
