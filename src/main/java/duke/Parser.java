package duke;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import javafx.animation.Timeline;

/**
 * Stores all responses that Duke will display on the
 * user's machine. The Parser class also processes user commands such as
 * mark done, mark undone, add and delete tasks from the list of tasks.
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 */
class Parser {
    //Greetings
    static String INITIAL_GREETING = "Welcome to Manchester United.";
    static String FINAL_GREETING = "SUIII, Bye";
    //Command to display for various user input
    static String MARK_COMMAND = "SUI, I have marked this task from the training room: ";
    static String UNMARK_COMMAND = "SUI, I have unmarked this task from the training room: ";
    static String DELETE_COMMAND = "Tasks successfully deleted. SUI.";
    static String FIND_COMMAND = "Here are matching tasks in your list";
    static String ILLEGAL_COMMAND = "This command is incomplete or illegal";
    //Whitelist commands
    static String SHOW_TASKS = "list";
    static String TERMINATE = "exit";
    static String MARK = "mark";
    static String UNMARK = "unmark";
    static String TODO = "todo";
    static String DEADLINE = "deadline";
    static String EVENT = "event";
    static String DELETE = "delete";
    static String FIND = "find";
    static String RECUR = "recur";
    //List starts numbering from 1 not from 0
    static int DECREMENT = 1;
    //Symbols to display the state of the task to the user beside the type of Task
    static String MARK_SYMBOL = "X";
    static String TODO_SYMBOL = "T";
    static String DEADLINE_SYMBOL = "D";
    static String EVENT_SYMBOL = "E";
    //Blacklist
    static List<String> INVALID_COMMANDS = Arrays.asList("blah", "todo", "deadline", "event");

    /**
    * Makes the default constructor explicit
    */
    protected Parser() {

    }
    /**
     * Displays the start-up football character sentence introducing his
     * name, and where he is from
     */
    static void greet() {
        System.out.println(INITIAL_GREETING);
    }
    /**
     * Displays the last sentence Alex Furguson says when the user
     * gracefully exits the program
     */
    static void exit() {
        System.out.println(FINAL_GREETING);
        System.exit(0);
    }
    /**
     * Processes the saved user tasks that were mark as done. It will display
     * the command Duke says and mark the corresponding task as done
     * @param taskPosition The index of the task in the list of tasks, starting from 1.
     * @param tasks The list of tasks to be processed
     * @return TaskList The new TaskList with the corresponding task marked as done
     * @see TaskList
     */
    static TaskList<Task> mark(int taskPosition, TaskList<Task> tasks) {
        System.out.println(MARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsDone());
    }

    /**
     * Processes the user's input tasks that is marked as done. It will display
     * the command Alex Furguson (Duke) says and mark the corresponding task as done
     * @param sc The Scanner object to register the user's input, starting from 1.
     * @param tasks The list of tasks to be processed
     * @return TaskList The new TaskList with the corresponding task marked as done
     * @see TaskList
     */
    static TaskList<Task> mark(Scanner sc, TaskList<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        System.out.println(MARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsDone());
    }

    /**
     * Processes the saved tasks that were marked as undone. It will display
     * the command Alex Furguson (Duke) says and mark the corresponding task as undone
     * @param taskPosition The index of the task in the list of tasks, starting from 1.
     * @param tasks The list of tasks to be processed
     * @return TaskList The new TaskList with the corresponding task marked as undone
     * @see TaskList
     */
    static TaskList<Task> unmark(int taskPosition, TaskList<Task> tasks) {
        System.out.println(UNMARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsUndone());
    }

    /**
     * Processes the user's input tasks that is marked as undone. It will display
     * the command Alex Furguson (Duke) says and mark the corresponding task as done
     * @param sc The Scanner object to register the user's input,
     * @param tasks The list of tasks to be processed
     * @return TaskList The new TaskList with the corresponding task marked as done
     * @see TaskList
     */
    static TaskList<Task> unmark(Scanner sc, TaskList<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        System.out.println(UNMARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsUndone());
    }

    /**
     * Processes the saved deletion of a task. It will display the command that the
     * task has been deleted, and show the current list of tasks
     * @param taskPosition The index of the task to delete, starting from 1
     * @param tasks The list of tasks
     * @return TaskList New list of tasks with the corresponding task deleted
     */
    static TaskList<Task> delete(int taskPosition, TaskList<Task> tasks) {
        System.out.println(DELETE_COMMAND + tasks.get(taskPosition));
        return tasks.removeTask(taskPosition);
    }

    /**
     * Processes the user's input deletion of a task. It will display the command that the
     * task has been deleted, and show the current list of tasks
     * @param tasks The list of tasks
     * @param sc The Scanner object to register the user's input, starting from 1.
     * @return TaskList New list of tasks with the corresponding task deleted
     */
    static TaskList<Task> delete(Scanner sc, TaskList<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        System.out.println(DELETE_COMMAND + tasks.get(taskPosition));
        return tasks.removeTask(taskPosition);
    }

    /**
     * Adds recur task into timeline list and deletes recur task from timeline list
     * @param sc The Scanner object to register the user's input, starting from 1.
     * @param tasks The list of tasks
     * @param recurResponse list of Timeline tasks
     * @return
     */
    static TaskList<Task> recur(Scanner sc, TaskList<Task> tasks, List<Timeline> recurResponse) {

        boolean delete = sc.next().equals("delete");
        int deletePos = Integer.parseInt(sc.next());
        TaskList<Task> newTasks;
        if (delete) {
            int pos = IntStream.range(0, tasks.numberOfTasks())
                            .filter(x ->tasks.get(x).getDescription().contains("recur"))
                            .limit(deletePos).reduce((a, b) -> b)
                            .orElse(-1);
            newTasks = tasks.removeTask(pos);
            Timeline removeRecur = recurResponse.remove(deletePos - 1);
            removeRecur.stop();
        } else {
            return tasks.add(new Task("recur " + sc.nextLine()));
        }
        return newTasks;
    }

    /**
     * Adds a new Todo task into the list of tasks from the saved data.
     * @param description Title of the task
     * @param tasks List of tasks
     * @return TaskList New list of tasks with the latest being the todo task added
     * @exception DukeException
     */
    static TaskList<Task> toDo(String description, TaskList<Task> tasks) {
        if (description.trim().length() == 0) {
            throw new DukeException("Todo must not be empty");
        }
        Task newTask = new Todos(description);
        return tasks.add(newTask);
    }
    /**
     * Adds a new Todo task into the list of tasks from the user's input.
     * @param sc Scanner object to register user's input
     * @param tasks List of tasks
     * @return TaskList New list of tasks with the latest being the todo task added
     * @exception DukeException
     */
    static TaskList<Task> toDo(Scanner sc, TaskList<Task> tasks) {
        String description = sc.nextLine();
        if (description.trim().length() == 0) {
            throw new DukeException("Todo must not be empty");
        }
        Task newTask = new Todos(description);
        return tasks.add(newTask);
    }

    /**
     * Adds a new Deadline task into the list of tasks from the saved data.
     * @param description Title of the task
     * @param date End date of deadline task
     * @param tasks List of tasks
     * @return TaskList New list of tasks with the latest being the todo task added
     * @exception DukeException
     */
    static TaskList<Task> deadline(String description, String date, TaskList<Task> tasks) {
        if (description.trim().length() == 0) {
            throw new DukeException("Deadline must not be empty");
        }
        try {
            Task newTask = new Deadline(description, date);
            return tasks.add(newTask);
        } catch (DukeException e) {
            System.out.println("Todo must not be empty");
        }
        return tasks;
    }

    /**
     * Adds a new Deadline task into the list of tasks from the user's input.
     * @param sc Scanner object to register user's input
     * @param tasks List of tasks
     * @return TaskList New list of tasks with the latest being the todo task added
     * @exception DukeException
     */
    static TaskList<Task> deadline(Scanner sc, TaskList<Task> tasks) {
        String description = sc.nextLine();
        if (description.trim().length() == 0) {
            throw new DukeException("Deadline must not be empty");
        }
        try {
            String[] dateRange = description.split("/by");
            Task newTask = new Deadline(dateRange[0], dateRange[1].replaceFirst("by", ""));
            return tasks.add(newTask);
        } catch (DukeException e) {
            System.out.println("Todo must not be empty");
        }
        return tasks;
    }

    /**
     * Adds a new Event task into the list of tasks from the saved data.
     * @param description Title of the task
     * @param from Start date of the event task
     * @param tasks List of tasks
     * @return TaskList New list of tasks with the latest being the todo task added
     * @exception DukeException
     */
    static TaskList<Task> events(String description, String from, String to, TaskList<Task> tasks) {
        if (description.trim().length() == 0) {
            throw new DukeException("Event must not be empty");
        }
        Task newTask = new Events(description, from, to);
        return tasks.add(newTask);
    }

    /**
     * Adds a new Event task into the list of tasks from the user's input.
     * @param sc Scanner object to register user's input
     * @param tasks List of tasks
     * @return TaskList New list of tasks with the latest being the todo task added
     * @exception DukeException
     */
    static TaskList<Task> events(Scanner sc, TaskList<Task> tasks) {
         String description = sc.nextLine();
         if (description.trim().length() == 0) {
             throw new DukeException("Event must not be empty");
         }
         String[] dateRange = description.split("/from");
         Task newTask = new Events(dateRange[0],dateRange[1].split("/to")[0],dateRange[1].split("/to")[1]);
         return tasks.add(newTask);
    }

    static TaskList<Task> find(Scanner sc, TaskList<Task> tasks) {
         System.out.println(FIND_COMMAND);
         tasks.listFindTasks(sc.nextLine(), tasks);
         return tasks;
    }
}
