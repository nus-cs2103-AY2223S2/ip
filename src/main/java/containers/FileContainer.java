package containers;

import types.IContainer;
import types.data.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FileContainer implements IContainer<Task> {
    private final BufferedWriter bw;
    private final File file;

    private FileContainer(File file, BufferedWriter bw) {
        this.file = file;
        this.bw = bw;
    }

    public static FileContainer ofLocation(String candidateLocation, boolean isOverwrite) {
        Path candidatePath = Path.of(candidateLocation);

        if (!Files.exists(candidatePath)) {
            try {
                Files.createDirectory(candidatePath.getParent());
            } catch (IOException e) {
                throw new InvalidPathException(candidateLocation, "got IOException");
            }
        }

        if (Files.isDirectory(candidatePath)) {
            throw new InvalidPathException(candidateLocation, "the target specified is a directory");
        }

        try {
            return new FileContainer(candidatePath.toFile(), new BufferedWriter(new FileWriter(candidatePath.toFile(), !isOverwrite)));
        } catch (IOException ignored) {
            return null;
        }
    }

    @Override
    public void add(Task record) {
        try {
            bw.append(String.valueOf(record));
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void push(List<Task> records) {
        try {
            bw.write("");
            for (Task i : records) {
                bw.append(String.valueOf(i));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> fetch() {
        throw new UnsupportedOperationException("This is just for show!");
    }
}
