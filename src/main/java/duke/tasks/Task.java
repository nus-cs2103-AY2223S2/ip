package duke.tasks;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String markAsDone() {
        if (isDone) {
            return String.format("Perhaps you forgot, but this task was already marked done!:\n\t%s", this);
        }
        isDone = true;
        return String.format("Well done! I've marked this task as done:\n\t%s", this);
    }

    public String markNotDone() {
        if (!isDone) {
            return String.format("No need to tell me, the task was not even marked as done!:\n\t%s", this);
        }
        isDone = false;
        return String.format("Okay, I have marked this task as not done:\n\t%s", this);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), name);
    }

    public String toText() {
        return String.format("! %d %s\n", isDone ? 1 : 0, name);
    }

    public static Task fromText(String taskText) throws DukeInvalidInputException, DukeEmptyInputException {
        String[] params = taskText.split(" ", 3);
        String type = params[0];
        String status = params[1];
        Task newTask;

        switch(type) {
        case "T":
            newTask = Todo.createTodo(params[2]);
            break;
        case "D":
            newTask = Deadline.createDeadline(params[2]);
            break;
        case "E":
            newTask = Event.createEvent(params[2]);
            break;
        default:
            System.out.println("Incorrect format");
            return null;
        }
        if (status.equals("1")) {
            newTask.isDone = true;
        }
        return newTask;
    }
}
