package duke;

import duke.taskers.Task;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Scanner;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePathName) {
        TaskList tasks1;
        this.ui = new Ui();
        this.storage = new Storage(filePathName);
        try {
            tasks1 = new TaskList(storage.loadFromFile());
        } catch (DukeException e) {
            tasks1 = new TaskList();
        }
        this.tasks = tasks1;
    }

     /**
     * creates the localdatetime by parsing the text string
     * @param dateTime the string representation of the local date time
     * @return the LocalDateTime object being created
     */
    public static LocalDateTime createLocalDateTime(String dateTime) {
        LocalDateTime date;
        try {
            String stringWithNoTrailingWhitespaces = dateTime.trim();
            date = LocalDateTime.parse(stringWithNoTrailingWhitespaces, FORMATTER);
        } catch (DateTimeException e) {
            date = null;
        }
        return date;
    }


    /**
     * driver function
     */
    public void run() {
        this.ui.greetings();
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                String line = input.nextLine();
                String upperLine = line.toUpperCase();
                String command = upperLine.split(" ")[0];
                Command cm = Parser.parseCommand(command);
                String[] elemArr = Parser.parseStartingElements(upperLine);



                if (cm.equals(Command.BYE)) {
                    if (elemArr.length == 1) {
                        break;
                    } else {
                        throw new DukeException("Did you mean to say bye? Type 'bye' to quit the program.");
                    }
                } else if (cm.equals(Command.LIST)) {
                    if (elemArr.length == 1) {
                        this.ui.printList(this.tasks.getList());
                    } else {
                        throw new DukeException("No argument in list allowed.");
                    }
                } else if (cm.equals(Command.MARK)) {
                    if (elemArr.length != 2) {
                        throw new DukeException("Wrong format. Format it as 'mark [index]'");
                    }
                    if (!elemArr[1].chars().allMatch(Character::isDigit)) {
                        throw new DukeException("Index should be a number");
                    }
                    int idx = Integer.parseInt(elemArr[1]) - 1;
                    if (idx >= this.tasks.getList().size() || idx < 0) {
                        throw new DukeException("This index doesn't exist.");
                    }
                    Task markedTask = this.tasks.markTaskInListDone(idx);
                    this.ui.markResponse(markedTask);
                    this.storage.deleteFileAndRedo(this.tasks.getList());
                } else if (cm.equals(Command.UNMARK)) {
                    if (elemArr.length != 2) {
                        throw new DukeException("Wrong format. Format it as 'mark [index]'");
                    }
                    if (!elemArr[1].chars().allMatch(Character::isDigit)) {
                        throw new DukeException("Index should be a number");
                    }
                    int idx = Integer.parseInt(elemArr[1]) - 1;
                    if (idx >= this.tasks.getList().size() || idx < 0) {
                        throw new DukeException("This index doesn't exist.");
                    }
                    Task unmarkedTask = this.tasks.markTaskInListUndone(idx);
                    this.ui.unmarkResponse(unmarkedTask);
                    this.storage.deleteFileAndRedo(this.tasks.getList());
                } else if (cm.equals(Command.DELETE)) {
                    if (elemArr.length != 2) {
                        throw new DukeException("Only one argument for delete allowed");
                    }
                    String idxStr = elemArr[1];
                    if (!idxStr.chars().allMatch(Character::isDigit)) {
                        throw new DukeException("Argument must be a digit");
                    }
                    int idx = Integer.parseInt(idxStr) - 1;
                    if (idx >= this.tasks.getList().size() || idx < 0) {
                        throw new DukeException("This index doesn't exist.");
                    }
                    Task removedTask = this.tasks.removeItem(idx);
                    this.ui.deleteItemResponse(removedTask, this.tasks.getList());
                    this.storage.deleteFileAndRedo(this.tasks.getList());
                } else {
                    Task addedTask = this.tasks.addItem(line, Command.valueOf(command));
                    this.storage.writeToFile(addedTask);
                    this.ui.addItemResponse(addedTask, this.tasks.getList());
                }
            } catch (DukeException e) {
                this.ui.printWithLines(" " + e.toString());
            }
        }
        this.ui.goodBye();
    }


    public static void main(String[] args) {
        String filePathName = "." + File.separator + "src" + File.separator + "main" + File.separator + "data" + File.separator + "duke";
        new Duke(filePathName).run();
    }
}

