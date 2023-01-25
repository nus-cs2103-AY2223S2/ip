package support;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

/**
 * Represents a database.
 */
public class Storage {
    private File file;

    /**
     * Creates a directory data, and file duke.txt if it does not exist.
     */
    public Storage() {
        try {
            this.file = new File("./" + "data/duke.txt");
            file.createNewFile();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Writes data to file after program finishes execution.
     * @param s data to be written
     */
    public void update(String s) {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
