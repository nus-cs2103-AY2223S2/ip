package data;
import core.exceptions.DisposableException;
import core.exceptions.WriteException;
import domain.entities.DataSaver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class DataSaverImpl extends DataSaver {
    public DataSaverImpl(String filename) throws WriteException {
        this.file = new File(filename);
        // create the file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new WriteException("Could not create file: " + filename + " " + e.getMessage());
            }
        }
        try {

            this.fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            throw new WriteException("Could not read file: " + filename);
        }
        this.writer = new PrintWriter(fileWriter);
    }

    final File file;

    final FileWriter fileWriter;

    final PrintWriter writer;

    @Override
    public void write(Object content) {
        writer.print(content.toString());
    }

    @Override
    public void writeln(Object content) {
        writer.println(content.toString());
    }

    @Override
    public void dispose() throws DisposableException {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new DisposableException("Could not close file writer");
        }
        writer.close();
    }
}
