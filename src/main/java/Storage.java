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
    private static final String folderPath = "data";
    private static final String filePath = "data/DUKEDB.TXT";
    private final File store;

    /**
     * Private constructor that initialises the file and folder structure.
     */
    private Storage() {
        //Create directory if it does not yet exist
        Path parentDir = Paths.get(folderPath);
        try {
            Files.createDirectory(parentDir);
        } catch (FileAlreadyExistsException fe) {
            //Already exists, no action required
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        //Create file if it does not yet exist.
        File store = new File(filePath);
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
     * Factory method that creates a DukeFileWriter instance.
     * @return The created DukeFileWriter.
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
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.newLine();
            bw.close();
            fw.close();
            this.store.setWritable(false);
            return Files.lines(Paths.get(filePath)).count();
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
    public DukeTask setDone(int index, boolean done) {
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            DukeTask task = Objects.requireNonNull(DukeTask.fromDBSchema(lines.get(index)));
            if (done) {
                task.setDone();
            } else {
                task.markUndone();
            }
            lines.set(index, task.toDBSchema());

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
    public DukeTask delete(int index) {
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            DukeTask task = DukeTask.fromDBSchema(lines.remove(index));

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
            Path path = Paths.get(filePath);
            Stream<String> fileStream = Files.lines(path);
            lines = Files.lines(path).count();
            Object[] output = fileStream.toArray();
            for (int i = 0; i < output.length; i++) {
                out.append(String.format(
                        "%s. %s\n",
                        i + 1,
                        DukeTask.fromDBSchema(output[i])
                ));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        if (lines == 0) {
            return "No records are added yet. Add some by typing them!";
        }
        return out.toString();
    }

    public List<DukeTask> toList() {
        try {
            Path path = Paths.get(filePath);
            Stream<String> fileStream = Files.lines(path);
            return fileStream
                    .map(task -> DukeTask.fromDBSchema(task))
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
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.count();
        } catch (IOException e) {
            return 0L;
        }
    }
}
