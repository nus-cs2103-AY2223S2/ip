package duke;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Storage {
    private DateTimeFormatter savedFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy HHmm");
    private DateTimeFormatter loadFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private boolean exist;
    private File saved;
    private String path;

    public Storage(String path) throws IOException {
        this.path = path;
        this.saved = new File(path);
        if(this.saved.createNewFile()) {
            this.exist = false;
        } else {
            this.exist = true;
        }
    }

    public boolean isSaved() {
        return this.exist;
    }

    public void save(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(path, false);
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            boolean currMarked = curr.isMarked();

            if (curr instanceof Deadline) {
                Deadline newCurr = (Deadline) curr;
                if (currMarked) {
                    fw.write("D" + "|" + "1" + "|" + newCurr.getTask() + "|" + newCurr.getDeadline() 
                        + System.lineSeparator());
                } else {
                    fw.write("D" + "|" + "0" + "|" + newCurr.getTask() + "|" + newCurr.getDeadline()
                        + System.lineSeparator());
                }
            } else if (curr instanceof Event) {
                Event newCurr = (Event) curr;
                if (currMarked) {
                    fw.write("E" + "|" + "1"  + "|" + newCurr.getTask() + "|" + 
                            newCurr.getStart() + "|" + newCurr.getEnd() + System.lineSeparator());
                } else {
                    fw.write("E" + "|" + "0"  + "|" + newCurr.getTask() + "|" + 
                            newCurr.getStart() + "|" + newCurr.getEnd() + System.lineSeparator());
                }
            } else if (curr instanceof ToDo) {
                ToDo newCurr = (ToDo) curr;
                    if (currMarked) {
                        fw.write("T" + "|" + "1" + "|" + newCurr.getTask() + System.lineSeparator());
                    } else {
                        fw.write("T" + "|" + "0" + "|" + newCurr.getTask() + System.lineSeparator());
                    }
            }
        }
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.saved);
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String curr = sc.nextLine();
            String[] parsed = curr.split("\\|");
            String type = parsed[0];
            boolean isMarked = parsed[1].equals("1");

            if (type.equals("D")) {
                LocalDateTime savedDateTime = LocalDateTime.parse(parsed[3], savedFormat);
                LocalDateTime loadDateTime = LocalDateTime.parse(savedDateTime.format(loadFormat), loadFormat);
                Deadline temp = new Deadline(parsed[2], loadDateTime.format(loadFormat));
                if (isMarked) {
                    temp.mark();
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
                list.add(temp);
            } else if (type.equals("T")) {
                ToDo temp = new ToDo(parsed[2]);
                if (isMarked) {
                    temp.mark();
                }
                list.add(temp);
            }
        }
        sc.close();
        return list;
    }
}
