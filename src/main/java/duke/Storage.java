package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (f.exists()) {
            try {
                Scanner sn = new Scanner(f);
                while (sn.hasNext()) {
                    String data = sn.nextLine();
                    String[] info = data.split("//");
                    String type = info[0];
                    String status = info[1];
                    String description = info[2];

                    if (type.equals("[T]")) {
                        Task todo = new Todo(description);

                        if (status.equals("X")) {
                            todo.mark();
                        }

                        tasks.add(todo);

                    } else if (type.equals("[D]")) {
                        String dateString = info[3];
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
                        LocalDate date = LocalDate.parse(dateString, dateFormatter);

                        Task deadline = new Deadline(description, date);

                        if (status.equals("X")) {
                            deadline.mark();
                        }

                        tasks.add(deadline);

                    } else if (type.equals("[E]")) {
                        String from = info[3];
                        String to = info[4];

                        Task event = new Event(description, from, to);

                        if (status.equals("X")) {
                            event.mark();
                        }

                        tasks.add(event);

                    } else {
                        throw new DukeException("Unknown task type found in storage file");
                    }
                }
                sn.close();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found!");
            }
        } else {
            try {
                File data = new File("./data");
                File d = new File(filePath);
                data.mkdir();
                d.createNewFile();
            } catch (IOException d) {
                System.out.println("Error creating file!");
            }
        }
        return tasks;
    }

    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            String dataString = taskList.toStorageData();
            fw.write(dataString);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing data into file.");
        }

    }
}
