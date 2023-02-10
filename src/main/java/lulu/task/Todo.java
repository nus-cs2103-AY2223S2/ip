package lulu.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public String toMemory() {
        int i = this.isDone ? 1 : 0;
        return ("T`" + i + "`" + this.description + '\n');
    }

    @Override
    public void update(String text) {
        String[] updateInformation = text.split(" ");
        String update = updateInformation[0].toUpperCase();
        switch (update) {
        case "DESCRIPTION":
            this.description = updateInformation[1];
            break;
        }
    }
}
