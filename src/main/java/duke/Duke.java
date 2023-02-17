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
        Duke duke = new Duke();
        duke.start();
    }



    /**
     * Starts the execution of Duke
     */
    public void start() throws DukeException {
        System.out.println(ui.start());
        System.out.println(storage.findData());
        storage.connect();
        Scanner input = new Scanner(System.in);
        String cmd;
        String out;
        while (true) {
            System.out.println("•──────────────────♛─────────────────•");
            try {
                cmd = input.nextLine();
                System.out.println("•──────────────────♛─────────────────•");
                out = getResponse(cmd);
                System.out.println(out);
                // User calls for exit
                if (cmd.equals("bye")) {
                    return;
                }
            } catch (DukeException e) {
                out = ui.showException(e);
                System.out.println(out);
            }
        }
    }

    /**
     * Execute the command and return the response string
     * @param cmd Command by user
     * @return Appropriate response from Duke
     * @throws DukeException
     */
    public String getResponse(String cmd) throws DukeException {
        try {
            String out;
            if (cmd.equals("bye")) {
                out = doBye();
            } else if (cmd.equals("list")) {
                out = doList();
            } else if (cmd.startsWith("mark") || cmd.startsWith("unmark")) {
                out = doMark(cmd);
            } else if (cmd.startsWith("todo") || cmd.startsWith("deadline") || cmd.startsWith("event")) {
                out = addTask(cmd);
            } else if (cmd.startsWith("delete")) {
                out = deleteTask(cmd);
            } else if (cmd.startsWith("find")) {
                out = findTask(cmd);
            } else if (cmd.equals("help")) {
                out = getHelp();
            } else if (cmd.equals("retrieve data")) {
                out = retrieveData();
            } else {
                throw new DukeException("('o')!! :: OOPS!!! I'm sorry, but I don't know what that means! " +
                        "Try entering 'help' to find out more about the features");
            }
            return out;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves the tasklist and show exit message
     * @return Exit message
     */
    private String doBye() {
        storage.save(list);
        return ui.showExit();
    }

    /**
     * Shows list of tasks
     * @return String display for tasklist
     */
    private String doList() {
        return ui.showList(list);
    }

    /**
     * Marks the task
     * @param cmd Mark command
     * @return Mark task message
     * @throws DukeException
     */
    private String doMark(String cmd) throws DukeException {
        int num = parser.getMarkNum(cmd, cmd.startsWith("mark"));
        if (list.getSize() < num) {
            throw new DukeException("('o')!! :: OOPS!!! The task does not exist!");
        }
        if (cmd.startsWith("mark")) {
            return ui.mark(list, num);
        } else {
            return ui.unmark(list, num);
        }
    }

    /**
     * Adds task to tasklist
     * @param cmd add task command
     * @return Task added message
     * @throws DukeException
     */
    private String addTask(String cmd) throws DukeException {
        if (cmd.startsWith("todo")) {
            if (parser.getTodoName(cmd).equals("")) {
                throw new DukeException("('o')!! :: OOPS!!! The description of a todo cannot be empty!");
            } else {
                return ui.addTodo(list, parser.getTodoName(cmd));
            }
        } else if (cmd.startsWith("deadline")) {
            if (parser.getDeadlineDl(cmd).equals("")) {
                throw new DukeException("('o')!! :: OOPS!!! The deadline is missing!");
            }
            if (parser.getDeadlineName(cmd).equals("")) {
                throw new DukeException("('o')!! :: OOPS!!! The description of a deadline cannot be empty!");
            }
            return ui.addDeadline(list, parser.getDeadlineName(cmd), parser.getDeadlineDl(cmd));
        } else {
            if (parser.getEventStart(cmd).equals("")) {
                throw new DukeException("('o')!! :: OOPS!!! The event duration is missing!");
            }
            if (parser.getEventName(cmd).equals("")) {
                throw new DukeException("('o')!! :: OOPS!!! The description of an event cannot be empty!");
            }
            return ui.addEvent(list, parser.getEventName(cmd), parser.getEventStart(cmd), parser.getEventEnd(cmd));
        }
    }

    /**
     * Deletes task from tasklist
     * @param cmd Delete command
     * @return Deleted task message
     * @throws DukeException
     */
    private String deleteTask(String cmd) throws DukeException {
        if (list.getSize() == 0) {
            throw new DukeException("('o')!! :: ☹ OOPS!!! The list is empty!");
        }
        int num = parser.getDeleteNum(cmd);
        if (list.getSize() < num) {
            throw new DukeException("('o')!! :: ☹ OOPS!!! The task does not exist!");
        }
        return ui.removeTask(list, num);
    }

    /**
     * Find tasks in the tasklist
     * @param cmd Find command
     * @return Found tasks message
     */
    private String findTask(String cmd) {
        String str = parser.getKeyword(cmd);
        return ui.showFoundTasks(list.findTask(str));
    }

    /**
     * Get help menu
     * @return Help menu
     */
    private String getHelp() {
        return ui.getHelp();
    }

    /**
     * Connects and retrieves saved data from duke.txt
     * @return Retrieval message
     * @throws DukeException
     */
    private String retrieveData() throws DukeException {
        String out = storage.findData();
        storage.connect();
        return out;
    }
}
