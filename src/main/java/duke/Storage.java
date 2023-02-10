package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;




/**
 * Storage class to handle saving and writing data
 */
public class Storage {

    protected String directory;
    protected String filePath;

    /**
     * constructor for storage class
     *
     * @param directory
     * @param filePath
     */
    public Storage(String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    /**
     * loads the file. creates new file if not created
     *
     * @return ArrayList of Task objects
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        assert directory != null;
        assert filePath != null;
        ArrayList<Task> al = new ArrayList<>();
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // make a file
        String fullFile = directory + File.separator + filePath;
        File file = new File(fullFile);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            al = readFile(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return al;
    }

    /**
     * reads and processes the data in the file
     *
     * @param file
     * @return
     */
    public static ArrayList<Task> readFile(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                String task = split[0];
                String mark = split[1];
                switch (task) {
                case "T":
                    Todo todo = new Todo(split[2]);
                    if (mark.equals("1")) {
                        todo.setAsMarked();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(split[2], split[3]);
                    if (mark.equals("1")) {
                        deadline.setAsMarked();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    String[] s = split[3].split("-");
                    String from = s[0].trim();
                    String to = s[1].trim();
                    Event event = new Event(split[2], from, to);
                    if (mark.equals("1")) {
                        event.setAsMarked();
                    }
                    tasks.add(event);
                    break;
                default:
                    //todo throw an error
//                    throw new DukeException("nothing here");
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * overwrites and rewrite to the text file
     *
     * @param taskList
     */
    public void writeFile(TaskList taskList) {
        assert directory != null;
        assert filePath != null;
        try {
            String fullFile = directory + File.separator + filePath;
            File newFile = new File(fullFile);
            FileWriter fw = new FileWriter(newFile);
            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                String done = task.getStatusIcon().equals("X") ? "1" : "0";
                String write = "|" + done + "|" + task.getDescription();
                if (task instanceof Todo) {
                    write = "T" + write;
                } else if (task instanceof Deadline) {
                    write = "D" + write + "|" + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    write = "E" + write + "|" + ((Event) task).getTime();
                }
                fw.write(write);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
