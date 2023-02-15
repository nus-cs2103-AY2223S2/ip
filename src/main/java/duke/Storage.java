package duke;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a persistent storage that creates a file
 * , can be written to and read from.
 */
class Storage {
    private static final Pattern TO_MATCH;

    static {
        TO_MATCH = Pattern.compile("[0-9]*\\. \\[(?<type>[DTH])]\\[(?<done>[ X])] (?<arguments>.*)");
    }

    private final String fileName;

    /**
     * Creates a storage.
     *
     * @param fileName File name for the file.
     * @throws IOException If I/O error occurs while Opening or Creating file.
     */
    Storage(String fileName) throws IOException {
        this.fileName = fileName;
    }

    /**
     * Reads file and returns TaskList.
     *
     * @return TaskList.
     * @throws IOException If file cannot be read.
     * @throws DukeException If file format is corrupted.
     */
    TaskList load() throws IOException, DukeException {
        TaskList tl = new TaskList();
        Parser tp = new Parser(tl);
        String[] task;
        Scanner sc = new Scanner(new FileReader(fileName));
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            assert TO_MATCH.matcher(s).matches() : "File is corrupted :(";
            Matcher matcher = TO_MATCH.matcher(s);
            if (!matcher.matches()) {
                throw new DukeException("CorruptedTaskListException");
            }
            String taskType = matcher.group("type");
            boolean isDone = matcher.group("done").equals("X");
            String description = matcher.group("arguments");
            switch (taskType) {
            case "T":
                task = tp.addTask("t " + description);
                if (isDone) {
                    tp.mark(task[0]);
                }
                break;
            case "D":
                task = tp.addTask("d " + description);
                if (isDone) {
                    tp.mark(task[0]);
                }
                break;
            case "E":
                task = tp.addTask("e " + description);
                if (isDone) {
                    tp.mark(task[0]);
                }
                break;
            default:
                throw new DukeException("CorruptedTaskListException");
            }
        }
        return tl;
    }

    /**
     * Writes line to file.
     * Newline will be appended after writing string.
     *
     * @param s String to write.
     * @throws IOException If I/O error occurs while writing.
     */
    void write(String s) throws IOException {
        assert Files.exists(Paths.get(fileName)) : "File should exist!";
        FileWriter fw = new FileWriter(fileName);
        fw.write(s);
        fw.close();
    }
}