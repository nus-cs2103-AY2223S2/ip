package duke;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Class for Storage object.
 * Saves list of tasks of current session in hard disk.
 * Loads pre-existing list of tasks upon startup if any.
 * 
 * @author Bryan Tan
 */
public class Storage {
    private DateTimeFormatter savedFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy HHmm");
    private DateTimeFormatter loadFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private boolean exist;
    private File saved;
    private String path;

    /**
     * Constructor for Storage class.
     * 
     * @param path path of file to load (if exists).
     * @throws IOException when path is invalid.
     * @return A Storage object.
     */
    public Storage(String path) throws IOException {
        this.path = path;
        this.saved = new File(path);
        if(this.saved.createNewFile()) {
            this.exist = false;
        } else {
            this.exist = true;
        }
    }

    /**
     * Checks if a pre-existing saved list of tasks is available.
     * 
     * @return true if file is available, false otherwise.
     */
    public boolean isSaved() {
        return this.exist;
    }

    /**
     * Saves list of tasks in current session into same file path and file name.
     * 
     * @param list List of tasks to be saved.
     * @throws IOException when file path is invalid.
     */
    public void save(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(path, false);
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            boolean currMarked = curr.isMarked();

            if (curr instanceof Deadline) {
                Deadline newCurr = (Deadline) curr;
                if (currMarked) {
                    fw.write("D" + "|" + "1" + "|" + newCurr.getTask() + "|" + newCurr.getDeadline()
                            + "|" + (newCurr.isTagged() ? newCurr.getTag() : "0")
                        + System.lineSeparator());
                } else {
                    fw.write("D" + "|" + "0" + "|" + newCurr.getTask() + "|" + newCurr.getDeadline()
                            + "|" + (newCurr.isTagged() ? newCurr.getTag() : "0")
                        + System.lineSeparator());
                }
            } else if (curr instanceof Event) {
                Event newCurr = (Event) curr;
                if (currMarked) {
                    fw.write("E" + "|" + "1"  + "|" + newCurr.getTask() + "|" + 
                            newCurr.getStart() + "|" + newCurr.getEnd()
                            + "|" + (newCurr.isTagged() ? newCurr.getTag() : "0")
                            + System.lineSeparator());
                } else {
                    fw.write("E" + "|" + "0"  + "|" + newCurr.getTask() + "|" + 
                            newCurr.getStart() + "|" + newCurr.getEnd()
                            + "|" + (newCurr.isTagged() ? newCurr.getTag() : "0")
                            + System.lineSeparator());
                }
            } else if (curr instanceof ToDo) {
                ToDo newCurr = (ToDo) curr;
                    if (currMarked) {
                        fw.write("T" + "|" + "1" + "|" + newCurr.getTask()
                                + "|" + (newCurr.isTagged() ? newCurr.getTag() : "0")
                                + System.lineSeparator());
                    } else {
                        fw.write("T" + "|" + "0" + "|" + newCurr.getTask()
                                + "|" + (newCurr.isTagged() ? newCurr.getTag() : "0")
                                + System.lineSeparator());
                    }
            }
        }
        fw.close();
    }

    /**
     * Loads pre-existing saved list of tasks.
     * 
     * @return Arraylist containing tasks created from previous session.
     * @throws FileNotFoundException when file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.saved);
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String curr = sc.nextLine();
            String[] parsed = curr.split("\\|");
            int length = parsed.length;
            String type = parsed[0];
            boolean isMarked = parsed[1].equals("1");
            boolean isTagged = !parsed[length - 1].equals("0");

            if (type.equals("D")) {
                LocalDateTime savedDateTime = LocalDateTime.parse(parsed[3], savedFormat);
                LocalDateTime loadDateTime = LocalDateTime.parse(savedDateTime.format(loadFormat), loadFormat);
                Deadline temp = new Deadline(parsed[2], loadDateTime.format(loadFormat));
                if (isMarked) {
                    temp.mark();
                }
                if (isTagged) {
                    temp.tag(parsed[length - 1]);
                }
                list.add(temp);
            } else if (type.equals("E")) {
                LocalDateTime savedStartTime = LocalDateTime.parse(parsed[3], savedFormat);
                LocalDateTime loadStartTime = LocalDateTime.parse(savedStartTime.format(loadFormat), loadFormat);
                LocalDateTime savedEndTime = LocalDateTime.parse(parsed[4], savedFormat);
                LocalDateTime loadEndTime = LocalDateTime.parse(savedEndTime.format(loadFormat), loadFormat);
                Event temp = new Event(parsed[2], 
                    loadStartTime.format(loadFormat),
                    loadEndTime.format(loadFormat));
                if (isMarked) {
                    temp.mark();
                }
                if (isTagged) {
                    temp.tag(parsed[length - 1]);
                }
                list.add(temp);
            } else if (type.equals("T")) {
                ToDo temp = new ToDo(parsed[2]);
                if (isMarked) {
                    temp.mark();
                }
                if (isTagged) {
                    temp.tag(parsed[length - 1]);
                }
                list.add(temp);
            }
        }
        sc.close();
        return list;
    }
}
