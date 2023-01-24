package duke.task;

import duke.utilities.Parser;

public abstract class Task {
    public String task_name;
    public String message_add;
    public String message_marked;
    public String message_unmarked;
    public String message_display;
    public String message_delete;
    public boolean done;
    public Parser parser;

    Task(String name, boolean done) {
        this.task_name = name;
        this.done = done;
        this.message_add = "";
        this.message_marked = "";
        this.message_unmarked = "";
        this.message_delete = "";
        parser = new Parser();
    }

    public abstract void add();

    public abstract void marked();

    public abstract void unmarked();

    public abstract void display();

    public abstract void delete();
}
