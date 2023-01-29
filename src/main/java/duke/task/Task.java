package duke.task;

public class Task {
    protected String item;
    protected boolean status;
    protected String types; // toDo, deadline, events

    public Task(String x, String y) {
        item = x;
        types = y;
        status = false;
    }

    public String getTime() {
        return "";
    }

    public String getItem() {
        return item;
    }
    public boolean getStatus() {
        return status;
    }

    public String getTypes() {
        return types;
    }

    @Override
    public String toString(){
        String getter;
        if (status == false) {
            getter = "[ ] " + item;
        } else {
            getter = "[X] " + item;
        }

        if (types.equals("T")) {
            return "[T]" + getter;
        }

        return getter;
    }

    public void mark(){
        status = true;
    }

    public void unmark(){
        status = false;
    }
}
