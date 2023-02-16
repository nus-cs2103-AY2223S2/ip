package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The method updates the task list in the filepath according to the latest command by the user.
     *
     * @param dukeList the task list being acted on by the user.
     */
    public void writeToFile(ArrayList<Task> dukeList) {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            for (Task t: dukeList) {
                if (t instanceof Deadline) {
                    writer.write("D | " + t.getStatusIcon() + " | " + t.description + " |by " + ((Deadline) t).by
                            + System.lineSeparator());
                }
                if (t instanceof Event) {
                    writer.write("E | " + t.getStatusIcon() + " | " + t.description + " |f " + ((Event) t).from
                            + " |t " + ((Event) t).to + System.lineSeparator());
                }
                if (t instanceof Todo) {
                    writer.write("T | " + t.getStatusIcon() + " | " + t.description + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * The method reads the task list from the file stated in the declared filePath.
     *
     * @return the task list last saved by the user.
     * @throws DukeException if there is an error creating the file
     */
    public ArrayList<Task> readFile() throws DukeException {
        ArrayList<Task> dukeList = new ArrayList<>();
        File f = new File(filePath);
        if (f.exists()) {
            try {
                Scanner s = new Scanner(new File(filePath));
                //new FileWriter(filePath, false).close();
                while (s.hasNextLine()) {
                    String str = s.nextLine();
                    System.out.println(str);
                    if (str.startsWith("E")) {
                        Event e = new Event(str.substring(8, str.indexOf(" |f")),
                                str.substring(str.indexOf(" |f") + 4, str.indexOf(" |t")),
                                str.substring(str.indexOf(" |t") + 4));
                        if (str.startsWith("E | X")) {
                            e.markAsDone();
                        }
                        dukeList.add(e);
                    }
                    if (str.startsWith("D")) {
                        Deadline d = new Deadline(str.substring(8, str.indexOf(" |b")),
                                str.substring(str.indexOf(" |b") + 4));
                        if (str.startsWith("D | X")) {
                            d.markAsDone();
                        }
                        dukeList.add(d);
                    }
                    if (str.startsWith("T")) {
                        Todo t = new Todo(str.substring(8));
                        if (str.startsWith("T | X")) {
                            t.markAsDone();
                        }
                        dukeList.add(t);
                    }

                }
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error occurred.");
            }

        } else {
            try {
                String basePath = new File("").getAbsolutePath();
                File dir = new File(basePath.concat("/data"));
                File c = new File(filePath);
                dir.mkdir();
                c.createNewFile();
            } catch (IOException d) {
                System.out.println("Error while creating file");
            }
        }
        return dukeList;
    }
}
