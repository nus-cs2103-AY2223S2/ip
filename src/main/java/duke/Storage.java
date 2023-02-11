package duke;

import duke.task.*;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Storage class to handle file operations.
 */
public class Storage {
    private String filepath;

    /**
     * Public constructor
     * @param filepath = filepath of where txt is stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Read tasks from file, and add them all into a list.
     *
     * @return ArrayList<Task> that contains all the tasks
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList <Task>();

        //Create directory "data", if exist do nothing
        File directory = new File("data");
        directory.mkdir();

        //Create file if does not exist, if exist read the file
        File file = new File(this.filepath);
        if (!file.createNewFile()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] tokens = st.split(";");
                Task t = null;
                if( tokens[0].equals("todo"))
                    t = new Todo( tokens[2] , Boolean.parseBoolean(tokens[1]));
                else if ( tokens[0].equals("deadline"))
                    t = new Deadline( tokens[2], LocalDateTime.parse(tokens[3]),
                            Boolean.parseBoolean(tokens[1]));
                else if ( tokens[0].equals("event"))
                    t = new Event( tokens[2],LocalDateTime.parse(tokens[3]),
                            LocalDateTime.parse(tokens[4]),Boolean.parseBoolean(tokens[1]));
                list.add(t);
            }
            br.close();
        }

        return list;
    }

    /**
     * Write a new line to the file
     * @param t = task
     */
    public void write(Task t){
        try {
            FileWriter fw = new FileWriter(filepath, true);
            fw.write(t.toFile() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Remove all text from the file
     */
    public void empty() {
        try {
            new FileWriter(filepath, false).close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Write from TaskList to the file
     */
    public void writeFromList(TaskList tasks, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Create a file based on the filepath
     */
    public void createNewFile(String filepath) {
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getter method for filepath
     */
    public String getFilePath() {
        return this.filepath;
    }
}
