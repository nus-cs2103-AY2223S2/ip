package duke.tasks;

import java.util.Objects;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(){
        this.isDone = true;
    }

    public void setNotDone(){
        this.isDone = false;
    }

    public String saveTask() { return this.description; }

    public static Task decode(String taskString){
//        System.out.println(taskString);
        String[] taskStringSplit = taskString.split(" \\| ",4);
        Task currTask = new Task("ll");

        switch(taskStringSplit[0]){
        case "T":
            currTask = new ToDo(taskStringSplit[2]);
            break;

        case "D":
            currTask = new Deadline(taskStringSplit[2], taskStringSplit[3]);
            break;

        case "E":
            String[]fromToSplit = taskStringSplit[3].split(" - ");
            currTask = new Event(taskStringSplit[2], fromToSplit[0], fromToSplit[1]);
            break;
        }

        if(taskStringSplit[1].equals("1")){
            currTask.setDone();
        }
        return currTask;
    }

    @Override
    public String toString (){
        return "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description);
    }
}

