package data;

import java.io.File;
import java.util.Scanner;

import core.exceptions.LoadException;
import domain.entities.DataLoader;

/**
 * The implementation of the data loader, which will load the data from a file.
 */
public class DataLoaderImpl extends DataLoader {
    private final File file;
    private final Scanner scanner;

    /**
     * Creates a new data loader that will load the data from the file with the
     * given path.
     *
     * @param path the path to the file from which the data will be loaded.
     * @throws LoadException if the file could not be read.
     */
    public DataLoaderImpl(String path) throws LoadException {
        this.file = new File(path);
        if (!file.exists()) {
            // try to create the file
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new LoadException("Could not create file: " + path + " " + e.getMessage());
            }
        }
        try {
            this.scanner = new Scanner(file);
        } catch (Exception e) {
            throw new LoadException("Could not read file: " + path + e.getMessage());
        }
    }

    @Override
    public void dispose() {
        scanner.close();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
}
