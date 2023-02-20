package duke;

import duke.exceptions.DukeyException;

import java.io.FileNotFoundException;


public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("DukeySave.txt");
        duke.initiateDukeyList();

    }


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


    public void initiateDukeyList() {
        ui.printWelcomeMessage();
        ui.printInstruction();
        try {
            taskList.initiate(storage);
        } catch (FileNotFoundException e) {
            ui.printExceptionMessage(new DukeyException("No saved list found, starting new list."));
        }

        boolean stillRunning = true;
        boolean forceStop = false;

        while (stillRunning && !forceStop) {
            ui.printLnBreak();
            ActionEnum actionEnum = ActionEnum.BYE;
            try {
                actionEnum = ui.readCommand();
                switch (actionEnum) {
                    case TODO:
                        taskList.addItem(ToDo.createToDo(ui));
                        break;
                    case DEADLINE:
                        taskList.addItem(Deadline.createDeadline(ui));
                        break;
                    case EVENT:
                        taskList.addItem(Event.createEvent(ui));
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
                    case SAVE:
                        taskList.save(storage);
                        break;
                    case CLEARSAVE:
                        taskList.clearSave(storage);
                        break;
                    case BYE:
                        stillRunning = false;
                        break;
                    case FORCESTOP:
                        forceStop = true;
                        break;
                }
            } catch (DukeyException e) {
                ui.printExceptionMessage(e);
            }

            if (!stillRunning) {
                taskList.save(storage);
                ui.printGoodbyeMessage();
            }

        }




    }






}
