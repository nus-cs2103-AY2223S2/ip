import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    public void save(ArrayList<Task> list) throws DukeException {
        ArrayList<String> temp = new ArrayList<>();
        try {
            File file = new File("./data/duke.txt");
            file.getParentFile().mkdir();

            FileWriter fw = new FileWriter(file);

            for (int i = 0; i < list.size(); i++) {
                temp.add(list.get(i).sendOutputToFile());
            }
            fw.write(String.join("\n", temp));
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Error when adding file");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filepath);
            if (!file.exists()) {
                return tasks;
            }
            file.getParentFile().mkdirs();
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String[] curr = scanner.nextLine().split(" \\| ");
                    String taskType = curr[0];
                    Task task;
                    switch (taskType) {
                        case "E":
                            task = new Event(curr[2], curr[3]);
                            if (Integer.parseInt(curr[1]) == 1) {
                                task.markAsDone();
                            } else {
                                task.markAsNotDone();
                            }
                            tasks.add(task);
                            break;
                        case "D":
                            LocalDate temp = LocalDate.parse(curr[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            task = new Deadline(curr[2], temp);
                            if (Integer.parseInt(curr[1]) == 1) {
                                task.markAsDone();
                            } else {
                                task.markAsNotDone();
                            }
                            tasks.add(task);
                            break;

                        case "T":
                            task = new Todo(curr[2]);
                            if (Integer.parseInt(curr[1]) == 1) {
                                task.markAsDone();
                            } else {
                                task.markAsNotDone();
                            }
                            tasks.add(task);
                            break;
                        default:
                            throw new DukeException("Error: Wrong task encountered");
                    }
                }

            }
        } catch (Exception ex) {
            throw new DukeException("Exception has occurred");
        }
        return tasks;
    }




}
