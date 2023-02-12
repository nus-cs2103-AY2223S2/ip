package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Makes sense of the user command.
 */
public class Parser {
    private TaskStorage taskStorage;

    /**
     * Constructs a Parser object with the given taskStorage.
     *
     * @param taskStorage The given taskStorage.
     */
    public Parser(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    /**
     * Returns the taskStorage of the Parser object.
     *
     * @return The taskStorage of the Parser object.
     */
    public TaskStorage getTaskStorage() {
        return this.taskStorage;
    }

    public boolean isTerminate(String inp) {
        String[] input = inp.split(" ");
        boolean isExit = false;
        switch(input[0]) {
        case "bye":
            isExit = true;
            break;

        }
        return isExit;
    }

    /**
     * Returns the goodbye message.
     *
     * @return The goodbye message.
     */
    public String bye() {
        String byeString = "Byeee! Hope to see you again! Signing off, duke.";
        return byeString;
    }

    /**
     * Returns a string to let the user know the task numbered at that list
     * is marked done.
     *
     * Marks that task as done with a cross.
     *
     * @param input The task number on the taskStorage object.
     * @return The string to let the user know the task numbered at that list
     * is marked done.
     */
    public String mark(String[] input) {
        int taskNo = Integer.parseInt(input[1]);
        String response = "";
        try {
            //int taskNo = Integer.parseInt(input[1]);
            if (taskNo > taskStorage.noTasks() || taskNo <= 0) {

                throw new DukeException("Give a vaild number");
            }
            response = taskStorage.getTask(taskNo - 1).markAsDone();
        } catch (NumberFormatException e) {
            response =  "Number should be typed in";
        } catch (DukeException e){
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    /**
     * Returns a string to let the user know the task numbered at that list
     * is marked undone.
     *
     * Marks that task as undone without a cross.
     *
     * @param input The task number on the taskStorage object.
     * @return The string to let the user know the task numbered at that list
     * is marked undone.
     */
    public String unMark(String[] input) {
        String response = "";
        int taskNoUnMark = Integer.parseInt(input[1]);
        try {
            if (taskNoUnMark > taskStorage.noTasks() || taskNoUnMark <= 0) {
                throw new DukeException("Give a valid number");
            }
            response = taskStorage.getTask(taskNoUnMark - 1).markAsUnDone();
        } catch (NumberFormatException e) {
            response =  "Number should be typed in";
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    public String todo(String inp) {
        String response = "";
        try {
            String[] inpTodo = inp.split(" ");
            if (inpTodo.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String todoTask = inp.substring(5);
            Task todo = new Todo(todoTask);
            response = taskStorage.addTask(todo);
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    public String deadline(String inp) {
        String response = "";
        try {
            if (inp.length() == 8) {
                throw new DukeException("☹ OOPS!!! The description of a deadline must have a date.");
            }
            String deadlineStr = inp.substring(9);
            String[] inputDeadline = deadlineStr.split("/");
            if (inputDeadline.length != 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline must have a date.");
            }
            String deadLineTaskStr = inputDeadline[0];
            assert(inputDeadline[1].contains("by"));
            String end = inputDeadline[1].substring(3);
            Task deadLineTask = new Deadline(deadLineTaskStr, end);
            response = taskStorage.addTask(deadLineTask);
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    public String event(String inp) {
        String response = "";

        try {
            if (inp.length() == 6) {
                throw new DukeException("☹ OOPS!!! The description of an event must have a start and end time.");
            }
            String eventStr = inp.substring(6);

            String[] eventStrsplit = eventStr.split("/");
            if (eventStrsplit.length != 3) {
                throw new DukeException("☹ OOPS!!! The description of an event must have a start and end time.");
            }
            String eventTaskStr = eventStrsplit[0];
            assert(eventStrsplit[1].contains("from"));
            String eventBegin = eventStrsplit[1].substring(5);
            eventBegin = eventBegin.substring(0, eventBegin.length() - 1);
            assert(eventStrsplit[2].contains("to"));
            String eventEnd = eventStrsplit[2].substring(3);
            Task eventTask = new Event(eventTaskStr, eventBegin, eventEnd);
            response = taskStorage.addTask(eventTask);
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    public String delete(String inp) {
        String response = "";
        String[] input = inp.split(" ");
        try {
            int taskNo = Integer.parseInt(input[1]);
            if (taskNo > taskStorage.noTasks() || taskNo <= 0) {
                throw new DukeException("Give a vaild number");
            }
            Task eventTask = taskStorage.getTask(taskNo - 1);
            response = taskStorage.deleteTask(eventTask);
        } catch (NumberFormatException e) {
            response = "Number should be typed in";
        } catch (DukeException e){
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    public String find(String inp) {
        String response = "";
        try {
            StringBuilder chunkOfText = new StringBuilder();
            chunkOfText.append("Here are the matching tasks in your list:\n");
            if (inp.length() == 5) {
                throw new DukeException("☹ OOPS!!! There's nothing to find in empty input!");
            }
            String findString = inp.substring(5);
            TaskStorage findStorage = new TaskStorage();
            for (int i = 0; i < taskStorage.noTasks(); i++) {
                Task t = taskStorage.getTask(i);
                if (t.isInDescription(findString)) {
                    findStorage.addTaskWithoutPrinting(t);
                }
            }
            response = findStorage.listTask();
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    /**
     * Sort the tasks in the taskStorage list by deadlines, events, and todos.
     *
     * Within each category, the tasks are sorted by alphabetical order.
     *
     * @return A string to indicate the tasks in the taskStorage list has been sorted.
     */
    public String sort() {
        String response = "";
        response = this.taskStorage.sortTask();
        return response;
    }

    /**
     * The main logic of the program.
     * Execute a command based on the string input given.
     *
     * @param inp The string input given.
     * @return False if the input contains "Bye", otherwise return True.
     */
    public String execute(String inp) {
        String[] input = inp.split(" ");
        String response = "";
        switch(input[0]) {
            case "list":
                response = taskStorage.listTask();
                break;

            case "bye":
                response = this.bye();
                break;

            case "mark":
                response = this.mark(input);
                break;

            case "unmark":
                response = this.unMark(input);
                break;

            case "todo":
                response = this.todo(inp);
                break;

            case "deadline":
                response = this.deadline(inp);
                break;

            case "event":
                response = this.event(inp);
                break;

            case "delete":
                response = this.delete(inp);
                break;

            case "find":
                response = this.find(inp);
                break;

            case "sort":
                response = this.sort();
                break;

            default:
                response = new DukeException().getMessage();
        }
        return response;
    }
}
