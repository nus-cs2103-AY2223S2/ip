public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public static Task dataToTask(String data) {
        Task task = null;
        //| is a metacharacter in regex. You'd need to escape it:
        String[] tokens = data.split("\\|");
        //remove spaces in between
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
        }

        switch(tokens[0]) {
            case "[T]":
                task = new ToDo(tokens[2]);
                break;
            case "[D]":
                task = new Deadline(tokens[2], tokens[3]);
                break;
            case "[E]":
                task = new Event(tokens[2], tokens[3], tokens[4]);
                break;
        }

        if(tokens[1].equals("X")) {
            assert task != null;
            task.silentMark();
        }

        return task;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void silentMark() {
        this.isDone = true;
    }
    
    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n"
                + String.format("[%s][X] %s", this.getTaskType(), this);
    }

    public String markAsUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n"
                + String.format("[%s][ ] %s", this.getTaskType(), this);
    }

    public abstract String taskToData();

    public abstract String getTaskType();

    @Override
    public String toString() {
        return this.name;
    }
}
