package task;

import java.io.File;

/**
 * This class handles the storing of
 * Todo Task attributes.
 * @author Bryan Ong
 */
public class Todo extends Task {

    public Todo(String name, boolean done) {
        super(name, done);
    }

    /**
     * This method handles the writing to data file
     * @param file File to be written to
     * @return String Information of task to be written.
     */
    @Override
    public String write(File file) {
        return this.toWrite();
    }

    /**
     * This method handles the creation of the String
     * to be printed on user interface.
     * @return String Message to be printed.
     */
    @Override
    public String toString() {
        return "   [T]" + super.toString() + "\n";
    }

    /**
     * This method handles the creation of the String
     * to be written on data file.
     * @return String Message to be written.
     */
    @Override
    public String toWrite() {
        return "T | " + super.toWrite() + " |\n";
    }
}
