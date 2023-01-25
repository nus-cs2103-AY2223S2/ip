package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File f;
    private String text;

    public Storage(String filePath) {
        this.filePath = filePath;

        f = new File(filePath); // create a File for the given file path
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public TaskList load() throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList l = new TaskList();

        while (s.hasNext()) {
            String str = s.nextLine();
            String parts[] = str.split("~", 5);

            switch (parts[0]) {
                case "T":
                    l.add(new ToDo(parts[2], parts[1].equals("1")));
                    break;
                case "D":
                    l.add(new Deadline(parts[2], parts[1].equals("1"), LocalDate.parse(parts[3])));
                    break;
                case "E":
                    l.add(new Event(parts[2], parts[1].equals("1"), parts[3], parts[4]));
                    break;
            }
        }
        return l;
    }

    public void save(TaskList l) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (Task i : l) {
            fw.write(i.saveFormat() + "\n");
        }
        fw.close();
    }

}
