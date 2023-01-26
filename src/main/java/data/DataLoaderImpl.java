package data;

import core.exceptions.LoadException;
import domain.entities.DataLoader;

import java.io.File;
import java.util.Scanner;

public class DataLoaderImpl extends DataLoader {
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

    private final File file;

    private final Scanner scanner;

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
