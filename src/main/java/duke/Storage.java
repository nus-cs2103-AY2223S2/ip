package duke;

import java.io.*;

import exception.DukeException;
import task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Storage {
    private File file;
    private String filePath;


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(Tasklist tasklist) {
        createOrOpenFile();
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 1; i <= tasklist.size(); i++) {
                fileWriter.write(tasklist.getTask(i).toSaveFormat());
                if (i < tasklist.size()) {
                    fileWriter.write('\n');
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("The file is not writable.");
        }
    }

    public void createOrOpenFile() {
        this.file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return tasks;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String input;
            while ((input = fileReader.readLine()) != null) {
                String[] parts = input.split(" \\| ");
                String taskType = parts[0].trim();

                Task task;
                switch (taskType) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate dueDate = LocalDate.parse(parts[3], formatter);
                        task = new Deadline(parts[2], dueDate);
                        break;
                    case "E":
                        String startDate = parts[3];
                        String endDate = parts [4];
                        LocalDate start = Parser.parseFile(startDate);
                        LocalDate end = Parser.parseFile(endDate);
                        task = new Event(parts[2], start, end);
                        break;
                    default:
                        throw new DukeException("Error parsing file, unexpected task type");
                }
                if (Integer.parseInt(parts[1].trim()) == 1) {
                    task.markDone();
                } else if (Integer.parseInt(parts[1].trim()) == 0) {
                    task.unmark();
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

}
