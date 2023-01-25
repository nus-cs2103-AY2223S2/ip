package seedu.duke;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> tasksList;
    int counter = 0;

    public TaskList(List<Task> tasksList) throws DukeException{
        this.tasksList = tasksList;
        counter = tasksList.size();
    }

    public TaskList() {
        tasksList = new ArrayList<>();
    }

    public TaskList mark(Parser userParse) {
        tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).mark();
        return this;
    }

    public TaskList unmark(Parser userParse) {
        tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).unmark();
        return this;
    }

    public void addTodo(String description, Parser userParse) throws DukeException{
        int inputArrLength = userParse.inputArr.length;
        String[] inputArr = userParse.inputArr;
        for (int i = 1; i < inputArrLength; i++) {
            description = description + inputArr[i];
            if (i != inputArrLength - 1) {
                description += " ";
            }
        }
        try {
            tasksList.add(new Todo(userParse.checkDescription(description, "todo")));
            counter++;
        } catch (DukeException e) {
            throw e;
        }
    }

    public void addDeadline(String description, Parser userParse) throws DukeException {
        String deadline = "";
        int inputArrLength = userParse.inputArr.length;
        String[] inputArr = userParse.inputArr;
        for (int i = 1; i < inputArrLength; i++) {
            if (inputArr[i].charAt(0) == '/') {
                i++;
                while (i < inputArrLength) {
                    deadline += inputArr[i];
                    if (i != inputArrLength - 1) {
                        deadline += " ";
                    }
                    i++;
                }
                break;
            }
            description = description + inputArr[i] + " ";
        }
        try {
            tasksList.add(new Deadline(userParse.checkDescription(description, "deadline"),
                    userParse.checkTime(deadline, "deadline", "by")));
            counter++;
        } catch(DukeException e) {
            throw e;
        }
    }

    public void addEvent(String description, Parser userParse) throws DukeException {
        String from = "";
        String to = "";
        int inputArrLength = userParse.inputArr.length;
        String[] inputArr = userParse.inputArr;
        for (int i = 1; i < inputArrLength; i++) {
            if (inputArr[i].charAt(0) == '/') {
                i++;
                while (i < inputArrLength) {
                    if (inputArr[i].charAt(0) == '/') {
                        i++;
                        while (i < inputArr.length) {
                            to += inputArr[i];
                            if (i != inputArr.length - 1) {
                                to += " ";
                            }
                            i++;
                        }
                        break;
                    }
                    from += inputArr[i] + " ";
                    i++;
                }
                break;
            }
            description = description + inputArr[i] + " ";
        }
        try {
            tasksList.add(new Event(userParse.checkDescription(description, "event"),
                    userParse.checkTime(from, "event", "from"),
                    userParse.checkTime(to, "event", "to")));
            counter++;
        } catch (DukeException e) {
            throw e;
        }
    }

    public Task delete(Parser userParse) {
        String[] inputArr = userParse.inputArr;
        Task deleted = tasksList.remove(Integer.parseInt(inputArr[1]) - 1);
        counter--;
        return deleted;
    }
}
