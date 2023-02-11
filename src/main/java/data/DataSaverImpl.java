package data;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import core.exceptions.DisposableException;
import core.exceptions.WriteException;
import domain.entities.DataSaver;

/**
 * The implementation of the data saver, which will save the data to a file.
 */
public class DataSaverImpl extends DataSaver {
    /**
     * The name of the file to which the data will be saved.
     */
    final String fileName;
    /**
     * The file to which the data will be saved.
     */
    final File file;
    /**
     * The file writer.
     */
    final FileWriter fileWriter;
    /**
     * The buffered writer.
     */
    final BufferedWriter bufferedWriter;

    /**
     * Creates a new data saver that will save the data to file with the
     * given file name.
     *
     * @param fileName the name of the file to which the data will be saved.
     * @throws WriteException if the file could not be created or written to.
     */
    public DataSaverImpl(String fileName) throws WriteException {
        this.fileName = fileName;
        this.file = new File(fileName);
        // create the file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new WriteException("Could not create file: " + fileName + " " + e.getMessage());
            }
        }
        try {
            this.fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            throw new WriteException("Could not read file: " + fileName);
        }
        this.bufferedWriter = new BufferedWriter(fileWriter);
    }

    @Override
    public void write(Object content) throws WriteException {
        try {
            bufferedWriter.append(content.toString());
        } catch (IOException e) {
            throw new WriteException("Could not write to file: " + e.getMessage());
        }
    }

    @Override
    public void writeln(Object content) throws WriteException {
        try {
            bufferedWriter.append(content.toString().trim() + "\n");
        } catch (IOException e) {
            throw new WriteException("Could not write to file: " + e.getMessage());
        }
    }

    @Override
    public void clear() throws WriteException {
        try {
            final FileWriter fileWriter1 = new FileWriter(file, false);
            final PrintWriter printWriter = new PrintWriter(fileWriter1);
            printWriter.print("");
            printWriter.close();
            fileWriter1.close();
        } catch (IOException e) {
            throw new WriteException("Could not clear file: " + e.getMessage());
        }
    }

    @Override
    public void dispose() throws DisposableException {
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new DisposableException("Could not close file writer: " + e.getMessage());
        }
    }
}
