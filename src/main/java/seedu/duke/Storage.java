package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents the Storage object.
 */
public class Storage {
    private File save;

    /**
     * Creates a Storage object.
     * Checks if a storage location file exists.
     * If it does not exist, create a file and use that as the storage location.
     * Else, use existing storage location.
     *
     * referenced from https://stackoverflow.com/a/28620461
     */
    public Storage() {
        String path = System.getProperty("user.dir") + System.getProperty("file.separator");
        File directory = new File(path);
        if (!directory.exists()) {
            File taskList = new File(path, "taskList.txt");
            try {
                FileWriter fw = new FileWriter(taskList);
                fw.close();
                this.save = taskList;
            } catch (IOException e) {
                System.out.println("Unable to create file!");
            }
        } else {
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                    + System.getProperty("file.separator") + "taskList.txt";
            File save = new File(filePath);
            if (!save.exists()) {
                File taskList = new File(path, "taskList.txt");
                this.save = taskList;
                try {
                    FileWriter fw = new FileWriter(taskList);
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Unable to create file!");
                }
            } else {
                this.save = save;
            }
        }
    }

    /**
     * Reads existing TaskList from saved file and returns the TaskList.
     *
     * @return TaskList with the saved file data.
     */
    public TaskList read() {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(this.save);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] s = task.split("\\|");
                String taskType = s[0].substring(0, 1);
                switch (taskType) {
                case "T":
                    String todoName = s[2].substring(1);
                    Todo todo = new Todo(todoName);
                    if (s[1].charAt(1) == '1') {
                        todo.toMark(true);
                    }
                    taskList.addTask(todo);
                    break;

                case "D":
                    String deadlineName = s[2].substring(1);
                    String[] deadlineDescription = s[3].substring(1).split(" ");
                    String deadlineDate = deadlineDescription[0];
                    Deadline deadline = new Deadline(deadlineName, LocalDate.parse(deadlineDate));
                    if (s[1].charAt(1) == '1') {
                        deadline.toMark(true);
                    }
                    taskList.addTask(deadline);
                    break;
                case "E":
                    String eventName = s[2].substring(1);
                    String[] eventDescription = s[3].substring(1).split(" ");
                    String eventDate = eventDescription[0];
                    Event event = new Event(eventName, LocalDate.parse(eventDate));
                    if (s[1].charAt(1) == '1') {
                        event.toMark(true);
                    }
                    taskList.addTask(event);
                    break;
                default:
                    break;
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file!");
        }
        return taskList;
    }

    /**
     * Writes TaskList to storage.
     *
     * @param taskList TaskList to be saved to storage.
     */
    public void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.save);
            StringBuilder list = new StringBuilder();
            for (int i = 1; i < taskList.size() + 1; i++) {
                list.append(taskList.get(i - 1).toSave()).append("\n");
            }
            fw.write(list.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }
}
