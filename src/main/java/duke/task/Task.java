package duke.task;

public class Task {
    protected String item;
    protected boolean isComplete;
    protected String types; // toDo, deadline, events

    public Task(String item, String types) {
        this.item = item;
        this.types = types;
        isComplete = false;
    }

    public String getTime() {
        return "";
    }

    public String getItem() {
        return item;
    }
    public boolean getComplete() {
        return isComplete;
    }

    public String getTypes() {
        return types;
    }

    @Override
    public String toString(){
        String getter;
        if (isComplete == false) {
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
        isComplete = true;
    }

    public void unmark(){
        isComplete = false;
    }
}
