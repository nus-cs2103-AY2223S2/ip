package duke.storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Storage class to remember previous input and load them upon next run
 *
 * @author Pearl Twe
 * @version CS2103T AY22/23 Semester 2
 */
public class Storage {
    private File file;
    private String filePath;
    private String dataDirectory;

    /**
     * Constructor for Storage
     */
    public Storage() {
        this.dataDirectory = "./data/";
        this.filePath = this.dataDirectory + "duke.txt";
        this.file = new File(this.filePath);
        File directory = new File(this.dataDirectory);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the user input into duke.txt file.
     * @param taskList list of tasks inputted by user.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList.getTaskList()) {
                writer.write(task.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads previous taskList saved in duke.txt
     * @return List that was previously saved
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.file);
            while(scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String encoded[] = task.split(" ", 4);
                String taskType = encoded[0];
                String isDone = encoded[1];
                String priority = encoded[2];
                String parts = encoded[3];

                switch (taskType) {
                    case "todo":
                        // todo true 2 play
                        Task decodedTodo = new Todo(parts);
                        decodedTodo.setPriority(Integer.parseInt(priority));
                        list.add(decodedTodo);
                        if (isDone.equals("true")) {
                            decodedTodo.markDone();
                        }
                        break;
                    case "deadline":
                        // deadline true 2 eat with me /by: 2023-12-20

                        String parts2[] = parts.split("/by:", 2);
                        String deadlineDescription = parts2[0].trim();
                        String dateString = parts2[1].trim();
                        LocalDate date = LocalDate.parse(dateString);
                        Task decodedDeadline = new Deadline(deadlineDescription, date);
                        decodedDeadline.setPriority(Integer.parseInt(priority));
                        list.add(decodedDeadline);
                        if (isDone.equals("true")) {
                            decodedDeadline.markDone();
                        }
                        break;
                    case "event":
                        //event false 3 dancing festival /from: 2023-05-23 /to: 2023-12-20
                        String parts3[] = parts.split("/from:", 2);
                        String eventDescription = parts3[0].trim();

                        String timearr[] = parts3[1].split("/to:", 2);
                        String from = timearr[0].trim();
                        String to = timearr[1].trim();

                        LocalDate fromdate = LocalDate.parse(from);
                        LocalDate todate = LocalDate.parse(to);

                        Task decodedEvent = new Event(eventDescription, fromdate, todate);
                        decodedEvent.setPriority(Integer.parseInt(priority));
                        list.add(decodedEvent);
                        if (isDone.equals("true")) {
                            decodedEvent.markDone();
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
        return list;
    }

}
