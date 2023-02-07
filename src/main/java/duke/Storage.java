package duke;

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
        File directory = new File("data");
        //Create directory "data", if exist do nothing
        directory.mkdir();
        File file = new File(this.filepath);
        //Create file if does not exist, if exist read the file
        if (!file.createNewFile()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] tokens = st.split(",");
                Task t = null;
                if( tokens[0].equals("todo"))
                    t = new Todo( tokens[2] , Boolean.parseBoolean(tokens[1]));
                else if ( tokens[0].equals("deadline"))
                    t = new Deadline( tokens[2], LocalDateTime.parse(tokens[3]),Boolean.parseBoolean(tokens[1]));
                else if ( tokens[0].equals("event"))
                    t = new Event( tokens[2],LocalDateTime.parse(tokens[3]),LocalDateTime.parse(tokens[4]),Boolean.parseBoolean(tokens[1]));

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
            FileWriter fw = new FileWriter("data/duke.txt", true);
            fw.write(t.toFile() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
