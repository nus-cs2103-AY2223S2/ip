import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {};

    public void readInput(String[] input, TaskList taskList) throws DukeException {
        switch (input[0]) {
            case "list":   
                taskList.list(); 
                break;        
            case "mark":
                mark(input, taskList);
                break;
            case "unmark":
                unmark(input, taskList);
                break;
            case "todo":
                todo(input, taskList);
                break;
            case "deadline":
                deadline(input, taskList);
                break;
            case "event":
                event(input, taskList);
                break;
            case "delete":
                delete(input, taskList);
                break;
            default:
                throw new DukeException("Sorry I do not understand the command");
        }
    }

    public void mark(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Mark needs a number.");
        }
        if (Integer.parseInt(input[1]) > taskList.size()) {
            throw new DukeException("Invalid task.");
        }
        taskList.markTask(Integer.parseInt(input[1]) - 1);
    }

    public void unmark(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Unmark needs a number.");
        }
        if (Integer.parseInt(input[1]) > taskList.size()) {
            throw new DukeException("Invalid task.");
        }
        taskList.unmarkTask(Integer.parseInt(input[1]) - 1);
    }

    public void todo(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("todo needs a description");
        }
        Task t = new Todo(input[1].strip());
        taskList.addTask(t);
        System.out.println("Added new todo:\n  " + t + "\nNumber of tasks: " + taskList.size());
    }

    public void deadline(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1 || !input[1].contains("/by")) {
            throw new DukeException("Deadline needs a /by.");
        }
        String[] tempInput = input[1].strip().split("/by ");
        if (tempInput.length == 1) {
            throw new DukeException("/by needs a date/time.");
        }
        try {
            Task t = new Deadline(tempInput[0].strip(), tempInput[1].strip());
            taskList.addTask(t);
            System.out.println("Added new deadline:\n  " + t + "\nNumber of tasks: " + taskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /by needs to be in format yyyy-mm-dd");
        }
    }

    public void event(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1 || !input[1].contains("/from") || !input[1].contains("/to") ) {
            throw new DukeException("Event needs a /from and /to.");
        }
        String[] tempInput = input[1].split("/");
        String[] from = tempInput[1].split(" ",2);
        String[] to = tempInput[2].split(" ",2);
        if (from.length == 1 || to.length == 1) {
            throw new DukeException("/from and /to needs a date/time.");
        }
        try {
            Task t = new Event(tempInput[0].strip(), from[1].strip(), to[1].strip());
            taskList.addTask(t);
            System.out.println("Added new event:\n  " + t + "\nNumber of tasks: " + taskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /from and /to needs to be in format yyyy-mm-dd");
        }
    }

    public void delete(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Delete needs a number.");
        }
        int index = Integer.parseInt(input[1]); 
        if (index > taskList.size()) {
            throw new DukeException("Invalid task.");
        }
        taskList.deleteTask(index - 1);
    }
}
