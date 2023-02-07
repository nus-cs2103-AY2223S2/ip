import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException dukeException) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {

        ui.showWelcome();

        String userInput = null;
        while (!(userInput = ui.readCommand()).equals("bye")) {
            String[] splitInput = userInput.split(" ", 2);
            try {
                Command inputCommand = Parser.validateCommand(splitInput[0]);
                switch (inputCommand) {
                case LIST:
                    listTask();
                    break;
                case MARK:
                    markTask(splitInput);
                    break;
                case UNMARK:
                    unmarkTask(splitInput);
                    break;
                case DELETE:
                    deleteTask(splitInput);
                    break;
                case TODO:
                    addTodo(splitInput);
                    break;
                case DEADLINE:
                    addDeadline(splitInput);
                    break;
                case EVENT:
                    addEvent(splitInput);
                    break;
                default:
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } //end of switch-case

            } catch (DukeException dukeException) {
                ui.dukeSpeak(dukeException.getMessage());
            }
        } // end of while-loop


        ui.dukeSpeak("Bye. Hope to see you again soon!");
        ui.close();
    }



    private void addTodo(String[] input) throws DukeException {
        Todo tempTodo = Parser.parseTodo(input);
        String saveString = "T | 0 | " + tempTodo.getDescription();

        this.storage.save(saveString);
        this.tasks.addTask(tempTodo);

        String message = "Got it. I've added this task:\n " + tempTodo.toString();
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        ui.dukeSpeak(message);
    }

    private void addDeadline(String[] input) throws DukeException {
        Deadline tempDeadline = Parser.parseDeadline(input);
        String saveString = "D | 0 | " + tempDeadline.getDescription() + " | " + tempDeadline.getByDate();
        storage.save(saveString);
        tasks.addTask(tempDeadline);
        String message = "Got it. I've added this task:\n " + tempDeadline.toString();
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        ui.dukeSpeak(message);

    }

    private void addEvent(String[] input) throws DukeException {
        Event tempEvent = Parser.parseEvent(input);
        String saveString = "E | 0 | " + tempEvent.getDescription() + " | "
                + tempEvent.getFromDate() + ">" + tempEvent.getToDate();

        storage.save(saveString);
        tasks.addTask(tempEvent);
        String message = "Got it. I've added this task:\n " + tempEvent.toString();
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        ui.dukeSpeak(message);

    }

    private void listTask() {
        String message = "Here are the tasks in your list:";
        message += tasks.getTaskList();
        ui.dukeSpeak(message);
    }

    private void markTask(String[] input) throws DukeException {

        int taskNum = Parser.parseMarkTask(input);
        if (taskNum > tasks.numTasks() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }

        storage.markEntry(taskNum - 1, true);

        Task oneTask = tasks.getTask(taskNum - 1);
        tasks.markTask(taskNum - 1);

        String message = "Nice! I've marked this task as done:\n " + oneTask.toString();
        ui.dukeSpeak(message);
    }

    private void unmarkTask(String[] input) throws DukeException {

        int taskNum = Parser.parseUnmarkTask(input);
        if (taskNum > tasks.numTasks() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }

        storage.markEntry(taskNum - 1, false);

        Task oneTask = tasks.getTask(taskNum - 1);
        tasks.unmarkTask(taskNum - 1);
        String message = "OK! I've marked this task as not done yet:\n " + oneTask.toString();
        ui.dukeSpeak(message);
    }

    private void deleteTask(String[] input) throws DukeException {
        int taskNum = Parser.parseDeleteTask(input);
        if (taskNum > tasks.numTasks() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }

        storage.delete(taskNum-1);

        Task delTask = tasks.getTask(taskNum-1);
        tasks.deleteTask(taskNum-1);
        String message = "Noted. I've removed this task:\n " + delTask.toString();
        message += "\nNow you have " + tasks.numTasks() + " tasks in the list.";
        ui.dukeSpeak(message);
    }


}
