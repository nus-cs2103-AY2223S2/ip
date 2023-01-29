package rick;

import rick.task.RickTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class that either accesses existing storage,
 * or creates a new .txt file in storage to store the
 * task data.
 */
public class Storage {
    private static final String FOLDER_PATH = "data";
    private static final String FILE_PATH = "data/RICKSBRAIN.TXT";
    private final File store;

    /**
     * Private constructor that initialises the file and folder structure.
     */
    private Storage() {
        //Create directory if it does not yet exist
        Path parentDir = Paths.get(FOLDER_PATH);
        try {
            Files.createDirectory(parentDir);
        } catch (FileAlreadyExistsException fe) {
            //Already exists, no action required
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        //Create file if it does not yet exist.
        File store = new File(FILE_PATH);
        if (!store.exists()) {
            try {
                store.createNewFile();
            } catch (IOException e) {
                System.exit(1);
            }
        }
        this.store = store;
    }

    /**
     * Factory method that creates a RickFileWriter instance.
     * @return The created RickFileWriter.
     */
    public static Storage create() {
        return new Storage();
    }

    /**
     * Appends a new record to the end of the file.
     * @param s The record to be appended.
     * @return The count of records in the store.
     */
    public long write(String s) {
        this.store.setWritable(true);
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.newLine();
            bw.close();
            fw.close();
            this.store.setWritable(false);
            return Files.lines(Paths.get(FILE_PATH)).count();
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Modifies a task in the store.
     * @param index The index of the task in the store.
     * @param done The status of the task in the store.
     * @return The modified task.
     */
    public RickTask setDone(int index, boolean done) {
        Path path = Paths.get(FILE_PATH);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            RickTask task = Objects.requireNonNull(RickTask.fromDbSchema(lines.get(index)));
            if (done) {
                task.setDone();
            } else {
                task.markUndone();
            }
            lines.set(index, task.toDbSchema());

            this.store.setWritable(true);
            Files.write(path, lines, StandardCharsets.UTF_8);
            this.store.setWritable(false);

            return task;
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Deletes a task in the store.
     * @param index The index of the task in the store.
     * @return The deleted task.
     */
    public RickTask delete(int index) {
        Path path = Paths.get(FILE_PATH);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            RickTask task = RickTask.fromDbSchema(lines.remove(index));

            this.store.setWritable(true);
            Files.write(path, lines, StandardCharsets.UTF_8);
            this.store.setWritable(false);

            return task;
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Prints the store's contents as a list, line by line.
     * @return The store's contents as a String list.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        long lines = 0;
        try {
            Path path = Paths.get(FILE_PATH);
            Stream<String> fileStream = Files.lines(path);
            lines = Files.lines(path).count();
            Object[] output = fileStream.toArray();
            for (int i = 0; i < output.length; i++) {
                out.append(String.format(
                        "%s. %s\n",
                        i + 1,
                        RickTask.fromDbSchema(output[i])
                ));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        if (lines == 0) {
            return "No records are added yet. Add some by typing them!";
        }
        return out.substring(0, out.length() - 1);
    }

    /**
     * Creates a list of all tasks currently in the storage.
     *
     * @return The list of tasks.
     */
    public List<RickTask> toList() {
        try {
            Path path = Paths.get(FILE_PATH);
            Stream<String> fileStream = Files.lines(path);
            return fileStream
                    .map(task -> RickTask.fromDbSchema(task))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return List.of();
        }
    }

    /**
     * Calculates the number of records currently stored in the store.
     * @return The number of records stored.
     */
    public long size() {
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            return lines.count();
        } catch (IOException e) {
            return 0L;
        }
    }
}
