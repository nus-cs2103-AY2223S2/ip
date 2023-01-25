package dudu;

import dudu.command.*;
import dudu.exception.*;
import dudu.task.Event;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;
import dudu.util.Ui;
import dudu.exception.DuduException;

import java.util.Scanner;
public class Dudu {
    private TaskList list;
    private Scanner scanner;
    private Storage storage;
    private Ui ui;
    private static boolean isExit = false;

    public Dudu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        scanner = new Scanner(System.in);
        try {
            list = new TaskList(storage.loadTask());
        } catch (DuduException ex) {
            list = new TaskList();
        }
    }

    /**
     * Dudu exit
     */
    public static void exit() {
        isExit = true;
    }

    /**
     * Run Dudu
     */
    public void run() {
        ui.showWelcome();
        while (!isExit && scanner.hasNext()) {
            String input = scanner.nextLine().strip();
            // skip the empty lines
            if (input.isBlank()) {
                continue;
            }
            String[] inputArr = input.split(" ");
            try {
                if (inputArr[0].equals("bye")) {
                    new ByeCommand(Command.Instruction.BYE).execute(list, storage);
                } else if (inputArr[0].equals("list")) {
                    list.printList();
                } else if (inputArr[0].equals("delete")) {
                    new DeleteCommand(Command.Instruction.DELETE, inputArr[1]).execute(list, storage);
                } else if (inputArr[0].equals("mark")) {
                    new MarkCommand(Command.Instruction.MARK, inputArr[1]).execute(list, storage);
                } else if (inputArr[0].equals("unmark")) {
                    new UnMarkCommand(Command.Instruction.UNMARK, inputArr[1]).execute(list, storage);
                } else if (inputArr[0].equals("deadline")) {
                    if (input.trim().length() == 8) {
                        throw new EmptyDescriptionException("deadline","description", "Missing task description");
                    }
                    new DeadlineCommand(Command.Instruction.DEADLINE, input.substring(9)).execute(list, storage);
                } else if (inputArr[0].equals("todo")) {
                    if (input.trim().length() == 4) {
                        throw new EmptyDescriptionException("todo", "description", "Missing task description");
                    }
                    new TodoCommand(Command.Instruction.TODO, input.substring(5)).execute(list, storage);
                } else if (inputArr[0].equals("event")) {
                    if (input.trim().length() == 5) {
                        throw new EmptyDescriptionException("event", "description", "Missing task description");
                    }
                    new EventCommand(Command.Instruction.EVENT, input.substring(6)).execute(list, storage);
                } else {
                    throw new InvalidCommandException("Invalid Command");
                }
            } catch (InvalidCommandException ex){
                System.out.println(ex);
            } catch (DuduException ex) {
                System.out.println(ex);
            }
            ui.showLine();
        }
        ui.showLine();
    }
    public static void main(String[] args) {
        new Dudu("data/tasks.txt").run();
    }

}
