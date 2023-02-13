package Nerd.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import Nerd.entities.*;
import Nerd.exceptions.NerdException;
import Nerd.Parser.Parser;

/**
 * Represents the Storage of the Chat bot.
 */
public class Storage {
    private final Path HOME_DIRECTORY = Path.of(System.getProperty("user.dir") + "/data");
    private File file;
    private Parser parser;

    /**
     * Instantiates a storage object that stores and loads data from a file.
     *
     * @param file The name of the file.
     */
    public Storage(String file) {
        this.file = new File(this.HOME_DIRECTORY + "/" + file);
        this.parser = new Parser();
    }

    /**
     * Connects and checks if the Chat bot is connected to the database.
     *
     * @throws NerdException if file does not exist.
     */
    public void connect() throws NerdException {
        if (!file.exists()) {
            throw new NerdException("An error occurred when connecting to the database!");
        }
    }

    /**
     * Loads the storage to the Chat bot.
     *
     * @param list A TaskList object, preferably a new TaskList.
     * @return A boolean representation of the loading.
     * @throws FileNotFoundException if file does not exist.
     */
    public boolean load(TaskList list) throws FileNotFoundException {
        assert list != null : "Cannot load data into a non existing tasklist";
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (parser.parseText(input, list) == false) {
                return false;
            }
        }
        return true;

    }

    /**
     * Saves the current TaskList to the file.
     *
     * @param list A TaskList object.
     * @throws IOException When file cannot be written
     */
    public void save(TaskList list) throws IOException{
        assert list != null : "Cannot save data from a non existing tasklist";
        FileWriter myWriter = new FileWriter(file);
        for (int i = 0; i < list.getSize(); i++) {
            String line = list.getTask(i).toSave();
            myWriter.write(line);
        }
        myWriter.close();
    }
}