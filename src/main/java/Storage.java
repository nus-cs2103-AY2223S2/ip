import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    String filePath;
    ArrayList<Task> loadedTasks = new ArrayList<>();

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        try {
            Scanner scannerTxtFile = new Scanner(f);
            while (scannerTxtFile.hasNext()) {
                String txt = scannerTxtFile.nextLine();
                if (txt.substring(1, 2).equals("T")) {
                    String details = txt.substring(7);
                    Task t = new Todo(details);
                    if (txt.substring(4, 5).equals("X")) {
                        t.mark();
                    }
                    loadedTasks.add(t);
                } else if (txt.substring(1, 2).equals("D")) {
                    String[] detailsAndDueDate = txt.substring(7).split(" \\(");
                    String details = detailsAndDueDate[0];
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dueDate = LocalDate.parse(detailsAndDueDate[1].substring(0, detailsAndDueDate[1].length() - 1), dateFormatter);
                    Task t = new Deadline(details, dueDate);
                    if (txt.substring(4, 5).equals("X")) {
                        t.mark();
                    }
                    loadedTasks.add(t);
                } else if (txt.substring(1, 2).equals("E")) {
                    String[] detailsAndDate = txt.substring(7).split(" \\(");
                    String details = detailsAndDate[0];
                    String[] tmp = detailsAndDate[1].split(" to: ");
                    String to = tmp[1].substring(0, tmp[1].length() - 1);
                    String from = tmp[0].split("from: ")[1];
                    Task t = new Event(details, from, to);
                    if (txt.substring(4, 5).equals("X")) {
                        t.mark();
                    }
                    loadedTasks.add(t);
                }
            }
            scannerTxtFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        }

        return this.loadedTasks;
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            File f = new File(filePath);
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(tasks.tasksToStringFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("error writing into file");
        }
    }
}