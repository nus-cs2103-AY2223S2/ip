package duke;

import java.util.Scanner;

/**
 * Duke is a task tracker
 * @author Branda Ang
 * @version CS2103T AY22/23 SEM 2
 */
public class Duke {
    private UI ui;
    private TaskList list;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Duke
     */
    public Duke() {
        ui = new UI();
        list = new TaskList(100);
        storage = new Storage(list);
        parser = new Parser();
    }

    public static void main(String[] args) throws DukeException {
        new Duke().start();
    }



    /**
     * Starts the execution of Duke
     */
    public void start() throws DukeException {
        ui.start();
        storage.findData();
        storage.connect();
        Scanner input = new Scanner(System.in);
        String cmd;
        while (true) {
            System.out.println("•──────────────────♛─────────────────•");
            try {
                cmd = input.nextLine();
                System.out.println("•──────────────────♛─────────────────•");
                if (cmd.equals("bye")) {
                    doBye();
                    return;
                } else if (cmd.equals("list")) {
                    doList();
                } else if (cmd.startsWith("mark") || cmd.startsWith("unmark")) {
                    doMark(cmd);
                } else if (cmd.startsWith("todo") || cmd.startsWith("deadline") || cmd.startsWith("event")) {
                    addTask(cmd);
                } else if (cmd.startsWith("delete")) {
                    deleteTask(cmd);
                } else if (cmd.startsWith("find")) {
                    findTask(cmd);
                } else {
                    throw new DukeException("╮ʕ˚ᴥ˚ʔ╭ :: ☹ OOPS!!! I'm sorry, but I don't know what that means!");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private void doBye() {
        storage.save();
        ui.showExit();
    }

    private void doList() {
        ui.showList(list);
    }

    private void doMark(String cmd) throws DukeException {
        int num = parser.getMarkNum(cmd, cmd.startsWith("mark"));
        if (list.getSize() < num) {
            throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The task does not exist!");
        }
        if (cmd.startsWith("mark")) {
            ui.mark(list, num);
        } else {
            ui.unmark(list, num);
        }
    }

    private void addTask(String cmd) throws DukeException {
        if (cmd.startsWith("todo")) {
            if (parser.getTodoName(cmd).equals("")) {
                throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The description of a todo cannot be empty!");
            } else {
                ui.addTodo(list, parser.getTodoName(cmd));
            }
        } else if (cmd.startsWith("deadline")) {
            if (parser.getDeadlineDl(cmd).equals("")) {
                throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The deadline is missing!");
            }
            if (parser.getDeadlineName(cmd).equals("")) {
                throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The description of a deadline cannot be empty!");
            }
            ui.addDeadline(list, parser.getDeadlineName(cmd), parser.getDeadlineDl(cmd));
        } else {
            if (parser.getEventStart(cmd).equals("")) {
                throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The event duration is missing!");
            }
            if (parser.getEventName(cmd).equals("")) {
                throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The description of an event cannot be empty!");
            }
            ui.addEvent(list, parser.getEventName(cmd), parser.getEventStart(cmd), parser.getEventEnd(cmd));
        }
    }

    private void deleteTask(String cmd) throws DukeException {
        if (list.getSize() == 0) {
            throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The list is empty!");
        }
        int num = parser.getDeleteNum(cmd);
        if (list.getSize() < num) {
            throw new DukeException("( ﾟ 0 ﾟ) :: ☹ OOPS!!! The task does not exist!");
        }
        ui.removeTask(list, num);
    }

    private void findTask(String cmd) {
        String str = parser.getKeyword(cmd);
        ui.showFoundTasks(list.findTask(str));
    }
}
