import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private TaskList tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new TaskList();
        try {
            File data = new File(filePath);
            data.createNewFile();
        } catch (IOException e) {
            System.out.println("Storage constructor: " + e);
        }
    }

    public TaskList readData() {
        try {
            File data = new File(filePath);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String Line = sc.nextLine();
                char Type = Line.charAt(0);
                int isDone = Integer.parseInt(Character.toString(Line.charAt(2)));
                Task task = null;
                if (Type == 'T') { // T 1 read book
                    String description = Line.substring(4);
                    task = new ToDo(description);
                    if (isDone == 1) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } else if (Type == 'D') { // D 1 read book | June 12 4pm
                    int dividerIndex = Line.indexOf('|');
                    String description = Line.substring(4, dividerIndex - 1);
                    String by = Line.substring(dividerIndex + 2);
                    try {
                        task = new Deadline(description, by);
                        if (isDone == 1) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    } catch (DukeException e) {
                        continue;
                    }

                } else if (Type == 'E') { // E 1 read book | June 12 4pm | June 13 4pm
                    int firstDividerIndex = Line.indexOf('|');
                    int lastDividerIndex = Line.lastIndexOf('|');
                    String description = Line.substring(4, firstDividerIndex - 1);
                    String from = Line.substring(firstDividerIndex + 2, lastDividerIndex - 1);
                    String to = Line.substring(lastDividerIndex + 2);
                    try {
                        task = new Event(description, from, to);
                        if (isDone == 1) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    } catch (DukeException e) {
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("readData(): " + e);
        }
        return tasks;
    }

    public void writeData() {
        try {
            File data = new File(filePath);
            FileWriter writer = new FileWriter(data);
            while (!tasks.isEmpty()) {
                Task task = tasks.remove(0);
                String isDone = "";
                String writeTask = "";

                if(task.isDone()) {
                    isDone = "1";
                } else {
                    isDone = "0";
                }

                if (task instanceof ToDo) {
                    writeTask = "T " + isDone + " " + task.getDescription() + "\n";
                } else if (task instanceof Deadline) {
                    writeTask += "D " + isDone + " " + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n";
                } else if (task instanceof Event) {
                    writeTask += "E " + isDone + " " + task.getDescription() + " | " + ((Event) task).getFrom() + " | "
                            + ((Event) task).getTo() + "\n";
                }
                writer.write(writeTask);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("writeData: " + e);
        }

    }
}
