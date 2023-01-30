package task;

import java.io.File;

public class Todo extends Task {

    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String write(File file) {
        return this.toWrite();
    }

    @Override
    public String toString() {
        return "   [T]" + super.toString() + "\n";
    }

    @Override
    public String toWrite() {
        return "T | " + super.toWrite() + " |\n";
    }
}
