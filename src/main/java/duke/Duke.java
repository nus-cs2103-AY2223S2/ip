package duke;

import java.util.Scanner;

/**
 * Duke is a task tracker
 * @author Branda Ang
 * @version CS2103T AY22/23 SEM 2
 */
public class Duke {

    public static void main(String[] args) throws DukeException {
        UI ui = new UI();
        TaskList list = new TaskList(100);
        Storage storage = new Storage(list);
        Parser parser = new Parser();
        ui.start();
        storage.findData();
        storage.connect();
        start(ui, list, storage, parser);
    }

    /**
     * Starts the execution of Duke
     * @param ui UI for the application
     * @param list TaskList to keep track of the tasks
     * @param storage Storage to store final state of the task list
     * @param parser Parser to parse commands
     * @throws DukeException
     */
    public static void start(UI ui, TaskList list, Storage storage, Parser parser) throws DukeException {
        Scanner input = new Scanner(System.in);
        String cmd;
        while (true) {
            System.out.println("•──────────────────♛─────────────────•");
            try {
                cmd = input.nextLine();
                System.out.println("•──────────────────♛─────────────────•");
                if (cmd.equals("bye")) {
                    doBye(storage, ui);
                    return;
                } else if (cmd.equals("list")) {
                    doList(list, ui);
                } else if (cmd.startsWith("mark") || cmd.startsWith("unmark")) {
                    doMark(cmd, list, parser, ui);
                } else if (cmd.startsWith("todo") || cmd.startsWith("deadline") || cmd.startsWith("event")) {
                    addTask(cmd, list, parser, ui);
                } else if (cmd.startsWith("delete")) {
                    deleteTask(cmd, list, parser, ui);
                } else {
                    throw new DukeException("╮ʕ˚ᴥ˚ʔ╭ :: ☹ OOPS!!! I'm sorry, but I don't know what that means!");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private static void doBye(Storage storage, UI ui) {
        storage.save();
        ui.showExit();
    }

    private static void doList(TaskList list, UI ui) {
        ui.showList(list);
    }

    private static void doMark(String cmd, TaskList list, Parser parser, UI ui) throws DukeException {
        int num = parser.getMarkNum(cmd, cmd.startsWith("mark"));
        if (list.getSize() < num) {
            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The task does not exist!");
        }
        if (cmd.startsWith("mark")) {
            ui.mark(list, num);
        } else {
            ui.unmark(list, num);
        }
    }

    private static void addTask(String cmd, TaskList list, Parser parser, UI ui) throws DukeException {
        if (cmd.startsWith("todo")) {
            if (parser.getTodoName(cmd).equals("")) {
                throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The description of a todo cannot be empty!");
            } else {
                ui.addTodo(list, parser.getTodoName(cmd));
            }
        } else if (cmd.startsWith("deadline")) {
            if (parser.getDeadlineDl(cmd).equals("")) {
                throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The deadline is missing!");
            }
            if (parser.getDeadlineName(cmd).equals("")) {
                throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The description of a deadline cannot be empty!");
            }
            ui.addDeadline(list, parser.getDeadlineName(cmd), parser.getDeadlineDl(cmd));
        } else {
            if (parser.getEventStart(cmd).equals("")) {
                throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The event duration is missing!");
            }
            if (parser.getEventName(cmd).equals("")) {
                throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The description of an event cannot be empty!");
            }
            ui.addEvent(list, parser.getEventName(cmd), parser.getEventStart(cmd), parser.getEventEnd(cmd));
        }
    }

    private static void deleteTask(String cmd, TaskList list, Parser parser, UI ui) throws DukeException {
        if (list.getSize() == 0) {
            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The list is empty!");
        }
        int num = parser.getDeleteNum(cmd);
        if (list.getSize() < num) {
            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The task does not exist!");
        }
        ui.removeTask(list, num);
    }
}
