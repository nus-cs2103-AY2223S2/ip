package Duke;

import Duke.Event;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private File save;

    public Storage() {
        String directoryPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "lists";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            File taskList = new File(directoryPath, "taskList.txt");
            try {
                FileWriter fileWriter = new FileWriter(taskList);
                fileWriter.close();
                this.save = taskList;
            } catch (IOException e) {
                System.out.println("Unable to create file!");
            }
        } else {
            String FilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "lists" + System.getProperty("file.separator") + "taskList.txt";
            File save = new File(FilePath);
            if (!save.exists()) {
                File taskList = new File(directoryPath, "taskList.txt");
                this.save = taskList;
                try {
                    FileWriter fileWriter = new FileWriter(taskList);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to create file!");
                }
            } else {
                this.save = save;
            }
        }
    }

    public TaskList read() {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(this.save);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] s = task.split("\\|");
                String taskType = s[0].substring(0, 1);
                switch (taskType) {
                    // for Duke.Todo
                    case "T":
                        String todoName = s[2].substring(1);
                        Todo todo = new Todo(todoName);
                        if (s[1].charAt(1) == '1') {
                            todo.toMark(true);
                        }
                        taskList.addTask(todo);
                        break;
                    //for Duke.Duke.Deadline
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
                    // for Duke.Event
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
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file!");
        }
        return taskList;
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.save);
            StringBuilder list = new StringBuilder();
            for (int i = 1; i < taskList.size() + 1; i++) {
                list.append(taskList.get(i - 1).toSave()).append("\n");
            }
            fileWriter.write(list.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }
}
