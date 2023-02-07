package duke;

import java.util.ArrayList;
import java.util.Scanner;
import task.TaskList;
import task.Task;
import task.Command;
import task.Parser;
import task.Event;
import task.Deadline;
import task.Todo;
import storage.Storage;
import ui.Ui;

public class Duke {
    protected final TaskList taskList;
    protected final ArrayList<String> commandList;
    protected Parser parser;
    protected Storage storage;
    protected Ui ui;
    protected String directoryPath;
    protected String dataFilePath;

    public Duke() {
        this.dataFilePath = "./data/duke.txt";
        this.directoryPath = "./data";
        this.commandList = new ArrayList<>();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage(directoryPath, dataFilePath);
        this.ui = new Ui();
    }

    // methods
    public void addTask(Task task_given) {
        this.taskList.add(task_given);
    }

    public String msg_of_add(Task task) {
        return "Got it. I've added this task:\n " + task.toString() + "\nNow you have " +
                taskList.size() + " tasks in the list:D";
    }

    public String mark_as_done(int index) throws DukeException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = this.taskList.get(index);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    public String mark_as_undone(int index) throws DukeException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = this.taskList.get(index);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    public String delete_msg(int index) throws WrongIndexException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        return "Noted. I've removed this task:\n " + task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";

    }

    public String findMessage(String keyword) {
        return "Here are the matching tasks in your list:\n" + taskList.getTasksWanted(keyword);
    }
    public void loadDataFromDisk() {
        storage.loadData(commandList);
        for (String userInput: commandList) {
            String[] expressions = userInput.split(" ");
            String command = expressions[0];

            try {
                if (userInput.equals(parser.convertEnum(Command.LIST))) {
                    continue;
                } else if (command.equals(parser.convertEnum(Command.MARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    String tmp = mark_as_done(index);
                } else if (command.equals(parser.convertEnum(Command.UNMARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    String tmp = mark_as_undone(index);
                } else if (userInput.equals(parser.convertEnum(Command.BYE))) {
                    continue;
                } else if (command.equals(parser.convertEnum(Command.TODO))) {
                    parser.checkEmpty(userInput, command);
                    Todo todo_task = new Todo(userInput);
                    addTask(todo_task);
                    String tmp = msg_of_add(todo_task);
                } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                    parser.checkEmpty(userInput, command);
                    Deadline ddl_task = new Deadline(userInput);
                    addTask(ddl_task);
                    String tmp = msg_of_add(ddl_task);
                } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                    parser.checkEmpty(userInput, command);
                    Event event_task = new Event(userInput);
                    addTask(event_task);
                    String tmp = msg_of_add(event_task);
                } else if (command.equals(parser.convertEnum(Command.DELETE))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    String tmp = delete_msg(index);
                } else if (command.equals(parser.convertEnum(Command.FIND))) {
                    parser.checkEmpty(userInput, command);
                    String keyword = userInput.substring(5);
                    String tmp = findMessage(keyword);
                } else {
                    throw new WeirdInputException();
                }
            } catch (WeirdInputException exc)  {
                String tmp = ui.separate(exc.toString());
            } catch (DukeException exc) {
                String tmp = ui.separate(exc.toString());
            }
        }
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(ui.greeting());

        loadDataFromDisk();

        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            commandList.add(userInput);
            String[] expressions = userInput.split(" ");
            String command = expressions[0];

            try {
                if (userInput.equals(parser.convertEnum(Command.LIST))) {
                    System.out.println(ui.separate(taskList.print_curr_tasks()));
                } else if (command.equals(parser.convertEnum(Command.MARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(ui.separate(mark_as_done(index)));
                } else if (command.equals(parser.convertEnum(Command.UNMARK))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(ui.separate(mark_as_undone(index)));
                } else if (userInput.equals(parser.convertEnum(Command.BYE))) {
                    break;
                } else if (command.equals(parser.convertEnum(Command.TODO))) {
                    parser.checkEmpty(userInput, command);
                    Todo todo_task = new Todo(userInput);
                    addTask(todo_task);
                    System.out.println(ui.separate(msg_of_add(todo_task)));
                } else if (command.equals(parser.convertEnum(Command.DEADLINE))) {
                    parser.checkEmpty(userInput, command);
                    Deadline ddl_task = new Deadline(userInput);
                    addTask(ddl_task);
                    System.out.println(ui.separate(msg_of_add(ddl_task)));
                } else if (command.equals(parser.convertEnum(Command.EVENT))) {
                    parser.checkEmpty(userInput, command);
                    Event event_task = new Event(userInput);
                    addTask(event_task);
                    System.out.println(ui.separate(msg_of_add(event_task)));
                } else if (command.equals(parser.convertEnum(Command.DELETE))) {
                    parser.checkEmpty(userInput, command);
                    String[] words = userInput.split(" ");
                    int index = Integer.parseInt(words[1]) - 1;
                    System.out.println(ui.separate(delete_msg(index)));
                } else if (command.equals(parser.convertEnum(Command.FIND))) {
                    parser.checkEmpty(userInput, command);
                    String keyword = userInput.substring(5);
                    System.out.println(ui.separate(findMessage(keyword)));
                } else {
                    throw new WeirdInputException();
                }
            } catch (WeirdInputException exc) {
                System.out.println(ui.separate(exc.toString()));
            } catch (DukeException exc) {
                System.out.println(ui.separate(exc.toString()));
            }
        }

        System.out.println(ui.ending());

        String stringCommands = "";
        for (String command: commandList) {
            stringCommands += (command + "\n");
        }
        storage.saveToDisk(stringCommands + "\n");
    }
}
