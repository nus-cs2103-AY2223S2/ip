import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

/**
 * Represents the main program for Fake Duke the chat bot.
 */
public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String horizontalLine = "________________________________________________________________\n";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    private static enum Action {
        todo,
        deadline,
        event,
        list,
        mark,
        unmark,
        delete,
        bye;
    }

    private static enum TaskType {
        T,
        D,
        E;
    }

    public static void main(String[] args) {
        greeting();
        try {
            readFile();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        while(true) {
            try {
                String input = sc.nextLine();
                if (printMenu(input)) {
                    break;
                }
            } catch (DukeException | NoSuchElementException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prints out the greeting for Fake Duke.
     */
    private static void greeting() {
        String logo = "  _____     _       _  __  U _____ u      ____     _   _    _  __  U _____ u \n"
                + " |\" ___|U  /\"\\  u  |\"|/ /  \\| ___\"|/     |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/ \n"
                + "U| |_  u \\/ _ \\/   | ' /    |  _|\"      /| | | | \\| |\\| |  | ' /    |  _|\"   \n"
                + "\\|  _|/  / ___ \\ U/| . \\\\u  | |___      U| |_| |\\ | |_| |U/| . \\\\u  | |___   \n"
                + " |_|    /_/   \\_\\  |_|\\_\\   |_____|      |____/ u<<\\___/   |_|\\_\\   |_____|  \n"
                + " )(\\\\,-  \\\\    >>,-,>> \\\\,-.<<   >>       |||_  (__) )(  ,-,>> \\\\,-.<<   >>  \n"
                + "(__)(_/ (__)  (__)\\.)   (_/(__) (__)     (__)_)     (__)  \\.)   (_/(__) (__) \n";
        System.out.println(horizontalLine
                + "Hello!~ I'm the one and only\n"
                + logo
                + "What can I do for you?\n"
                + horizontalLine);
    }

    /**
     * Reads file if exists. Create directory (if necessary) and file if the file does not exists.
     *
     * @throws DukeException
     */
    private static void readFile () throws DukeException {
        String fileName = "data/duke.txt";
        File f = new File(fileName);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                processTask(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ioe) {
                throw new DukeException("Fake Duke can't create the file.");
            }
            throw new DukeException("Fake Duke can't find the file. I will create the file (./data/duke.txt) :D");
        }
    }

    /**
     * Processes tasks from the list of tasks in file in the hard disk.
     *
     * @param input One task.
     * @throws DukeException
     * @throws NoSuchElementException
     */
    private static void processTask(String input) throws DukeException, NoSuchElementException {
        String[] splitInput = input.split(" ~ ");

        try {
            TaskType taskType = TaskType.valueOf(splitInput[0]);

            int idx = -1;

            switch (taskType) {
            case T:
                if (splitInput.length != 3) {
                    throw new DukeException("Todo task is of invalid format in the file.");
                }
                idx = addTodo(splitInput[2]);
                break;
            case D:
                if (splitInput.length != 4) {
                    throw new DukeException("Deadline task is of invalid format in the file.");
                }
                idx = addDeadline(String.format("%s /by %s"
                        , splitInput[2]
                        , splitInput[3]));
                break;
            case E:
                if (splitInput.length != 5) {
                    throw new DukeException("Event task is of invalid format in the file.");
                }
                idx = addEvent(String.format("%s /from %s /to %s"
                        , splitInput[2]
                        , splitInput[3]
                        , splitInput[4]));
                break;
            }
            if (splitInput[1].equals("1") && idx != -1) {
                markTask(idx);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
    }

    private static void saveTasks() {
        String fileName = "data/duke.txt";
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).getRawTask());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints a menu that consists of these features:
     * - "todo": Adds todo task,
     * - "deadline": Adds deadline task,
     * - "event": Adds event task,
     * - "list": Displays the list of text entered by user,
     * - "mark": Marks a task as done,
     * - "unmark": Unmarks a task as undone,
     * - "delete": Deletes indicated task,
     * - "bye": Exits program,
     * - Enters any other String of invalid syntax: Rejected.
     *
     * @param   input   User input for the program menu.
     * @return  Status whether the program should exit or not.
     */
    private static boolean printMenu(String input) throws DukeException, NoSuchElementException {
        boolean exitStatus = false;

        String[] splitInput = input.split(" ");

        try {
            Action action = Action.valueOf(splitInput[0]);

            switch (action) {
            case todo:
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                addTodo(input.split(" ", 2)[1]);
                saveTasks();
                break;
            case deadline:
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                addDeadline(input.split(" ", 2)[1]);
                saveTasks();
                break;
            case event:
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                addEvent(input.split(" ", 2)[1]);
                saveTasks();
                break;
            case list:
                list();
                break;
            case mark:
                if (splitInput.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                markTask(Integer.parseInt(splitInput[1]));
                saveTasks();
                break;
            case unmark:
                if (splitInput.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                unmarkTask(Integer.parseInt(splitInput[1]));
                saveTasks();
                break;
            case delete:
                if (splitInput.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                deleteTask(splitInput[1]);
                saveTasks();
                break;
            case bye:
                exit();
                exitStatus = true;
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }

        return exitStatus;
    }

    /**
     * Handles the adding of todo tasks.
     *
     * @param   taskDesc    Description of task.
     */
    private static int addTodo(String taskDesc) {
        Todo todo = new Todo(taskDesc);
        addTask(todo);
        return taskList.size();
    }

    /**
     * Handles the adding of deadline tasks.
     *
     * @param   taskDesc    Description of task.
     */
    private static int addDeadline(String taskDesc) throws DukeException {
        Deadline deadline = new Deadline(taskDesc);
        addTask(deadline);
        return taskList.size();
    }

    /**
     * Handles the adding of event tasks.
     *
     * @param   taskDesc    Description of task.
     */
    private static int addEvent(String taskDesc) throws DukeException {
        Event event = new Event(taskDesc);
        addTask(event);
        return taskList.size();
    }

    /**
     * Handles the adding of any tasks.
     *
     * @param   task    Task to be added.
     */
    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println(horizontalLine
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskList.size()
                + " tasks in the list.\n"
                + horizontalLine);
    }

    /**
     * Displays the list of tasks.
     */
    private static void list() {
        System.out.println(horizontalLine
                + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1).toString());
        }
        System.out.println(horizontalLine);
    }

    /**
     * Marks task as done.
     *
     * @param   idx  Index of task.
     */
    private static void markTask(int idx) throws DukeException {
        checkIdx(idx);
        Task task = taskList.get(idx - 1);
        task.mark();
        System.out.println(horizontalLine
                + "Nice! I've marked this task as done:\n"
                + task.toString()
                + "\n"
                + horizontalLine);
    }

    /**
     * Unmarks task as undone.
     *
     * @param   idx  Index of task.
     */
    private static void unmarkTask(int idx) throws DukeException {
        checkIdx(idx);
        Task task = taskList.get(idx - 1);
        task.unmark();
        System.out.println(horizontalLine
                + "OK, I've marked this task as not done yet:\n"
                + task.toString()
                + "\n"
                + horizontalLine);
    }

    /**
     * Deletes task given its index.
     *
     * @param   strIdx  Index of task.
     */
    private static void deleteTask(String strIdx) throws DukeException {
        int idx = Integer.parseInt(strIdx);
        checkIdx(idx);
        System.out.println(horizontalLine
                + "Noted. I've removed this task:\n"
                + taskList.get(idx - 1).toString());
        taskList.remove(idx - 1);
        System.out.println("Now you have "
                + taskList.size()
                + " tasks in the list.\n"
                + horizontalLine);
    }

    /**
     * Checks the task index given by the user.
     *
     * @param   idx     Index of task.
     */
    private static void checkIdx(int idx) throws DukeException {
        if (idx - 1 > taskList.size() || idx - 1 < 0) {
            throw new DukeException("Task index given is invalid :( Try again.");
        }
    }

    /**
     * Prints out an exit message and exits the program.
     */
    private static void exit() {
        System.out.println(horizontalLine
                + "Hope I have been useful to you.\n"
                + "See you again soon. Bye!~\n"
                + horizontalLine);
    }
}
