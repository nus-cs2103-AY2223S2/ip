package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String path;
    File file;

    /**
     * This is the constructor for Storage
     * @param path path for the file
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File("text-ui-test/saved-tasks.txt");
    }

    /**
     * Handles all the input and makes sure they are formatted so that each object can be initialised
     * @throws IOException
     */
    public void handleLoad() throws IOException {
        BufferedReader taskLoader = new BufferedReader(new FileReader(this.file));
        assert Files.exists(Paths.get(this.path)) : "The file, " + this.path + ", does not exist.";
        String words = taskLoader.readLine();
        while (words != null) {
            String[] keywords = words.split(" \\|\\| ");
            Task current = null;
            if (keywords[0].equals("todo")) {
                current = new ToDo(keywords[2]);
            } else if (keywords[0].equals("event")) {
                String[] toandfrom =  keywords[3].split("-");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy");
                LocalDateTime starting = LocalDateTime.parse(toandfrom[0], formatter);
                LocalDateTime ending = LocalDateTime.parse(toandfrom[1], formatter);
                String timeStr = starting.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                String timeEnd = ending.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                String total = timeStr +"/" + timeEnd;
                current = new Event(keywords[2], total);
            } else if (keywords[0].equals("deadline")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy");
                LocalDateTime myDateObj = LocalDateTime.parse(keywords[3], formatter);
                String timeStr = myDateObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                current = new Deadline(keywords[2], timeStr);
            } else {
                System.out.println("error");
            }

            if (keywords[1].equals("1")) {
                current.handleMark();
            }
            Task.tasks.add(current);
            words = taskLoader.readLine();
        }
        taskLoader.close();
    }

    /**
     * Saves actions into the text file
     * @throws IOException
     */
    public void saveTasks() throws IOException {
        BufferedWriter taskWriter = new BufferedWriter(new FileWriter(path));
        String taskInString = "";
        for (int i = 0; i< (Task.tasks).size(); i++) {
            taskInString += Task.tasks.get(i).toSaveString() + "\n";
        }
        taskWriter.write(taskInString);
        taskWriter.close();
    }

}

