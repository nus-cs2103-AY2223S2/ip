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
     * @param taskStorage The given taskStorage.
     */
    public Parser(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    /**
     * Returns the taskStorage of the Parser object.
     * @return The taskStorage of the Parser object.
     */
    public TaskStorage getTaskStorage() {
        return this.taskStorage;
    }

    /**
     * The main logic of the program,
     * execute a command based on the string input given.
     * @param inp The string input given.
     * @return False if the input contains "Bye", otherwise return True.
     */
    public boolean execute(String inp) {
        String[] input = inp.split(" ");
        boolean isExit = false;
        switch(input[0]) {
            case "list":
                taskStorage.listTask();
                break;

            case "bye":
                System.out.println("Byeee! Hope to see you again! Signing off, duke.");
                isExit = true;
                break;

            case "mark":
                //System.out.println("Nice! I've marked this task as done:");
                try {
                    int taskNo = Integer.parseInt(input[1]);
                    if (taskNo > taskStorage.noTasks() || taskNo <= 0) {
                        throw new DukeException("Give a vaild number");
                    }
                    taskStorage.getTask(taskNo - 1).markAsDone();
                } catch (NumberFormatException e) {
                    System.out.println("Number should be typed in");
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }

            case "unmark":
                try {
                    int taskNoUnmark = Integer.parseInt(input[1]);
                    if (taskNoUnmark > taskStorage.noTasks() || taskNoUnmark <= 0) {
                        throw new DukeException("Give a valid number");
                    }
                    taskStorage.getTask(taskNoUnmark - 1).markAsUnDone();
                } catch (NumberFormatException e) {
                    System.out.println("Number should be typed in");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }

            case "todo":
                try {
                    String[] inpTodo = inp.split(" ");
                    if (inpTodo.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String todoTask = inp.substring(5);
                    Task todo = new Todo(todoTask);
                    taskStorage.addTask(todo);
                    break;
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }


            case "deadline":
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
                    String end = inputDeadline[1].substring(3);
                    Task deadLineTask = new Deadline(deadLineTaskStr, end);
                    //System.out.println(end);
                    taskStorage.addTask(deadLineTask);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }


            case "event":
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
                    String eventBegin = eventStrsplit[1].substring(5);
                    eventBegin = eventBegin.substring(0, eventBegin.length() - 1);
                    String eventEnd = eventStrsplit[2].substring(3);
                    Task eventTask = new Event(eventTaskStr, eventBegin, eventEnd);
                    taskStorage.addTask(eventTask);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }

            case "delete":
                try {
                    int taskNo = Integer.parseInt(input[1]);
                    if (taskNo > taskStorage.noTasks() || taskNo <= 0) {
                        throw new DukeException("Give a vaild number");
                    }
                    Task eventTask = taskStorage.getTask(taskNo - 1);
                    taskStorage.deleteTask(eventTask);
                } catch (NumberFormatException e) {
                    System.out.println("Number should be typed in");
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }

            case "find":
                try {
                    System.out.println("Here are the matching tasks in your list:");
                    String findString = inp.substring(5);
                    TaskStorage findStorage = new TaskStorage();
                    for (int i = 0; i < taskStorage.noTasks(); i++) {
                        Task t = taskStorage.getTask(i);
                        if (t.isInDescription(findString)) {
                            findStorage.addTaskWithoutPrinting(t);
                        }
                    }
                    findStorage.listTask();
                } catch (Exception e) {

                } finally {
                    break;
                }

            default:
                DukeException dukeException = new DukeException();
                System.out.println(dukeException.getMessage());
        }
        return isExit;
    }

}
