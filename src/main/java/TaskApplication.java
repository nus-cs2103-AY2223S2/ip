import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
/**
 * TaskApplication is a model class that has the basic functionalities of a checklist.
 * It helps to keep track of everyday tasks or events.
 */
public class TaskApplication {
    private List<Task> userTasks;

    private static Task fromString(String str) {
        System.out.println(str);
        Task newtask;
        String[] tokens = str.split("\\|");
        System.out.println(Arrays.toString(tokens));
        if (tokens[0].equals("T")) {
            newtask = new ToDo(tokens[2], tokens[1] == "1");
        } else if (tokens[0].equals("D")) {
            newtask = new Deadline(tokens[2], tokens[3],tokens[1] == "1");
        } else {
            newtask = new Event(tokens[2], tokens[3],tokens[4],tokens[1] == "1");
        }
        System.out.println(newtask);
        return newtask;
    }

    /**
     * https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
     * https://www.baeldung.com/java-path-vs-file
     * https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
     */
    private static List<Task> loadData() {
        List<Task> result = new ArrayList<>();
        try {
            Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
            if (!Files.exists(path)) {
                Files.createDirectories(Paths.get(System.getProperty("user.dir"), "data"));
                Files.createFile(path);
            }
            BufferedReader bf = Files.newBufferedReader(path);
            String line = bf.readLine();
            while (line != null) {
                result.add(fromString(line));
                line = bf.readLine();
            }
            bf.close();
        } catch (IOException e) {
            result = new ArrayList<>();
            System.out.println("Unable to open data\\duke.txt");
        }
        return result;
    }

    /**
     * https://beginnersbook.com/2014/01/how-to-write-to-file-in-java-using-bufferedwriter/
     * @param userTasks
     */
    private static void saveData(List<Task> userTasks) {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        BufferedWriter bw = null;
        try {
            bw = Files.newBufferedWriter(path);
            for (Task t: userTasks) {
                System.out.println(t.toDiskFormat());
                bw.write(t.toDiskFormat());
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw!=null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Unable to save to data\\duke.txt");
            }
        }
    }
    public TaskApplication() {
         this.userTasks = loadData();
    }

    public void markTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("\tThere are only %d tasks. There is no task" +
                            "with index %d", this.userTasks.size(), index)
            );
        }
        this.userTasks.get(index).setIsDone(true);
    }

    public void unmarkTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("\tThere are only %d tasks. There is no task" +
                            "with index %d\n", this.userTasks.size(), index)
            );
        }
        this.userTasks.get(index).setIsDone(false);
    }

    public void addTask(Task t) {
        this.userTasks.add(t);
    }

    public Task popTask(int index) throws DukeInvalidArgumentException {
        if (index < 0 || index > this.userTasks.size() - 1) {
            throw new DukeInvalidArgumentException(
                    String.format("\tThere are only %d tasks. There is no task" +
                            "with index %d\n", this.userTasks.size(), index)
            );
        }
        return this.userTasks.remove(index);
    }

    public void printAllTasks() {
        int i = 1;
        for (Task s: this.userTasks) {
            System.out.printf("\t%d.%s\n", i, s);
            i++;
        }
    }

    public Task getTask(int index) {
        return this.userTasks.get(index);
    }

    public int getNoOfTasks() {
        return this.userTasks.size();
    }

    public void close() {
        saveData(this.userTasks);
    }

}
