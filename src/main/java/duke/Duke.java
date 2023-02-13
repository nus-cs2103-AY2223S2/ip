package duke;

import java.util.Scanner;

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

    public static void start(UI ui, TaskList list, Storage storage, Parser parser) throws DukeException {
        Scanner input = new Scanner(System.in);
        String cmd;
        Integer num;
        while (true) {
            System.out.println("•──────────────────♛─────────────────•");
            try {
                cmd = input.nextLine();
                System.out.println("•──────────────────♛─────────────────•");
                if (cmd.equals("bye")) {
                    storage.save();
                    ui.showExit();
                    return;
                } else if (cmd.equals("list")) {
                    ui.showList(list);
                } else if (cmd.startsWith("mark") || cmd.startsWith("unmark")) {
                    num = parser.getMarkNum(cmd, cmd.startsWith("mark"));
                    if (list.getSize() < num) {
                        throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The task does not exist!");
                    }
                    if (cmd.startsWith("mark")) {
                        ui.mark(list, num);
                    } else {
                        ui.unmark(list, num);
                    }
                } else if (cmd.startsWith("todo") || cmd.startsWith("deadline") || cmd.startsWith("event")) {
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
                } else if (cmd.startsWith("delete")) {
                    if (list.getSize() == 0) {
                        throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The list is empty!");
                    }
                    num = parser.getDeleteNum(cmd);
                    if (list.getSize() < num) {
                        throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The task does not exist!");
                    }
                    ui.removeTask(list, num);
                } else if (cmd.startsWith("find")) {
                    findTask(cmd, list, parser, ui);
                } else {
                    throw new DukeException("╮ʕ˚ᴥ˚ʔ╭ :: ☹ OOPS!!! I'm sorry, but I don't know what that means!");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private static void findTask(String cmd, TaskList list, Parser parser, UI ui) {
        String str = parser.getKeyword(cmd);
        ui.showFoundTasks(list.findTask(str));
    }
}
