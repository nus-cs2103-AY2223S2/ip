package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a persistent storage that creates a file
 * , can be written to and read from.
 */
class Storage {
    private static final Pattern TO_MATCH = Pattern.compile("\\[(?<type>\\S)]\\[(?<done>[ X])] (?<arguments>.*)");
    private final String fileName;
    private FileWriter fw;
    private File dataFile;

    /**
     * Creates a storage.
     *
     * @param fileName File name for the file.
     * @throws IOException If I/O error occurs while Opening or Creating file.
     */
    Storage(String fileName) throws IOException {
        this.fileName = fileName;
        fw = new FileWriter(fileName, true);
    }

    /**
     * Reads file and returns TaskList.
     *
     * @return TaskLitt.
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
            Matcher matcher = TO_MATCH.matcher(s);
            if (!matcher.matches()) {
                throw new DukeException("Task List is Corrupted!");
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
                throw new DukeException("Task List is Corrupted!");
            }
        }
        System.out.println(tl.size());
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
        fw.write(s + '\n');
        fw.flush();
    }

    /**
     * Closes file.
     *
     * @throws IOException If I/O error occurs while closing.
     */
    void close() throws IOException {
        fw.close();
    }

    /**
     * Checks if string matches format.
     *
     * @param s String to check.
     * @return Boolean if string matches format.
     */
    boolean matchesFormat(String s) {
        Matcher matcher = TO_MATCH.matcher(s);
        return matcher.matches();
    }

    /**
     * Creates file.
     */
    void createFile() {
        File file = new File(fileName);
        File directory = new File(file.getParent());
        try {
            directory.mkdir();
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                System.out.println("Error: File already exists");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}