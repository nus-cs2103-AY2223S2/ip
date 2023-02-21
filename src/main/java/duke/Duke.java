package duke;

import duke.exceptions.DukeyException;

import java.io.FileNotFoundException;

/**
 * Interactive list which allows users to add Tasks and perform various operations to these Tasks.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Returns a new instance of Duke with a Storage, Ui and TaskList.
     * @param filePath the file path of the save file used to save data from the Duke
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("DukeySave.txt");
        duke.initiateDukeyList();

    }


    /**
     * Takes in user input and prints it back to the user.
     */
    public void echo() {
        while (true) {
            String input = ui.readEchoInput();
            if (input.equals("bye")) {
                break;
            }
            ui.echo(input);
        }
        ui.printGoodbyeMessage();

    }


    /**
     * Initiates the DukeyList by loading a save, then scans for and performs commands from the user.
     */
    public void initiateDukeyList() {
        ui.printWelcomeMessage();
        ui.printInstruction();
        try {
            taskList.initiate(storage);
        } catch (FileNotFoundException e) {
            ui.printLoadMessage(0);
        }

        boolean isStillRunning = true;
        boolean isForceStopped = false;

        while (isStillRunning && !isForceStopped) {
            ui.printLnBreak();
            ActionEnum actionEnum = ActionEnum.BYE;
            try {
                actionEnum = ui.readCommand();
                switch (actionEnum) {
                    case TODO:
                        taskList.addTask(ToDo.createToDo(ui));
                        break;
                    case DEADLINE:
                        taskList.addTask(Deadline.createDeadline(ui));
                        break;
                    case EVENT:
                        taskList.addTask(Event.createEvent(ui));
                        break;
                    case MARK:
                        taskList.mark();
                        break;
                    case UNMARK:
                        taskList.unmark();
                        break;
                    case DELETE:
                        taskList.delete();
                        break;
                    case LIST:
                        taskList.readList();
                        break;
                    case CLEARLIST:
                        taskList.clearList();
                        break;
                    case FIND:
                        taskList.find();
                        break;
                    case SAVE:
                        taskList.save(storage);
                        break;
                    case CLEARSAVE:
                        taskList.clearSave(storage);
                        break;
                    case BYE:
                        isStillRunning = false;
                        break;
                    case FORCESTOP:
                        isForceStopped = true;
                        break;
                }
            } catch (DukeyException e) {
                ui.printExceptionMessage(e);
            }

            if (!isStillRunning) {
                taskList.save(storage);
                ui.printGoodbyeMessage();
            }

        }




    }






}
