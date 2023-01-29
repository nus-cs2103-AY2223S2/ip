package duke.tasks;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskTypeException;

public abstract class Task {
    private final String task;
    private boolean done;

    public Task(String command) {
        this.task = command;
        this.done = false;
    }

    public static Task dataToTask(String data)
            throws InvalidTaskTypeException, InvalidDateException {
        Task task;

        //| is a metacharacter in regex. You'd need to escape it:
        String[] dataArray = data.split("\\|");
        //remove spaces in between
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = dataArray[i].trim();
        }

        switch(dataArray[0]) {
            case "[T]":
                task = new ToDo(dataArray[2]);
                break;
            case "[D]":
                task = new Deadline(dataArray[2], dataArray[3]);
                break;
            case "[E]":
                task = new Event(dataArray[2], dataArray[3], dataArray[4]);
                break;
            default:
                throw new InvalidTaskTypeException();
        }

        if(dataArray[1].equals("1")) {
            task.mark();
        }

        return task;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString(){
        String checkbox = "[" + (done ? "X" : " ") + "]";
        return checkbox + " " + this.task;
    }

    public abstract String taskToData();
}
