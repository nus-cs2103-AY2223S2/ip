package membot.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a file storage manager responsible for reading and writing
 * data to and from a file.
 */
public class StorageManager implements Outputable {
    private final File file;

    /**
     * Generates a <code>StorageManager</code> object.
     *
     * @param filePath The absolute or relative path to the file to be read from
     *                 or written to.
     * @throws IOException If <code>file</code> cannot be read from or written to.
     */
    public StorageManager(String filePath) throws IOException {
        this.file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Writes data to the <code>file</code>.
     *
     * @param data Data to be outputted.
     * @throws IOException If <code>file</code> cannot be read from or written to.
     */
    @Override
    public void write(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
        writer.write(data);
        writer.close();
    }

    /**
     * Reads data from <code>file</code> into an <code>ArrayList</code>, delimited
     * by new lines.
     *
     * @return An <code>ArrayList</code> of the lines in the file.
     * @throws IOException If <code>file</code> cannot be read from or written to.
     */
    public ArrayList<String> loadFromFile() throws IOException {
        Scanner reader = new Scanner(this.file);
        ArrayList<String> lines = new ArrayList<>();

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            lines.add(data);
        }
        reader.close();

        return lines;
    }
}
