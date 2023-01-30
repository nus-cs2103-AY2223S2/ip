import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Duke {
    protected final TaskList taskList;
    protected Parser parser;
    protected Storage storage;
    protected Ui ui;
    protected final String USER_HOME_DIRECTORY = System.getProperty("user.home");

    protected Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage();
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

    public String print_curr_taskList() {
        return taskList.print_curr_tasks();
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

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(ui.greeting());

        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
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
                }
                else {
                    throw new WeirdInputException();
                }
            } catch (WeirdInputException exc)  {
                System.out.println(ui.separate(exc.toString()));
            } catch (DukeException exc) {
                System.out.println(ui.separate(exc.toString()));
            }
        }
        System.out.println(ui.separate(ui.ending()));
    }
}
