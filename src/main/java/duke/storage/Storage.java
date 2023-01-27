package duke.storage;

import duke.exception.EmptyStorageException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private ArrayList<Task> list;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> load() throws EmptyStorageException {
        try {
            File f = new File(this.filePath);
            File dir = f.getParentFile();
            if (!dir.exists() && !f.exists()) {
//                System.out.println("d and f don't exist");
//                if(dir.mkdir()) {
//                    System.out.println("Directory Created");
//                } else {
//                    System.out.println("Directory is not created");
//                }
//                if(f.createNewFile()) {
//                    System.out.println("File Created");
//                } else {
//                    System.out.println("File is not created");
//                }
                dir.mkdir(); f.createNewFile();
                throw new EmptyStorageException("No existing data, creating a new file now");
            }
            else if (!f.exists()) {
                f.createNewFile();
            }
            else if (!dir.exists() && f.exists()) {
                // TODO: move file to data directory if file exists but not directory
            }
            else {
//              Dir + file exists, update taskList arr
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String input = sc.nextLine();
                    String[] inputArr;
                    inputArr = input.split("\\|");
                    this.loadTasks(inputArr, inputArr[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred, can't open file or something else happened");
            e.printStackTrace();
        }
        return this.list;
    }

    public void loadTasks(String[] inputArr, String taskType) {
        switch (taskType) {
            case "T":
                Todo todo = new Todo(inputArr[2]);
                this.list.add(todo);
                todo.markStatus(inputArr[1]);
                break;
            case "D":
                String[] dateTime = inputArr[3].split(" ");
                Deadline deadline = new Deadline(inputArr[2], dateTime[0], dateTime[1]);
                this.list.add(deadline);
                deadline.markStatus(inputArr[1]);
                break;
            case "E":
                Event ev = new Event(inputArr[2], inputArr[3], inputArr[4]);
                this.list.add(ev);
                ev.markStatus(inputArr[1]);
        }
    }

    public void updateStorage(TaskList tasklist) throws IOException {
//        System.out.println("entered update storage");
        Task task;
        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter(filePath);
        for (Task value : tasklist.getList()) {
            task = value;
            String taskName = task.getName();
//            System.out.println("taskName: " + taskName);
            sb.append(taskName);
            sb.append("|");
            if (task.getStatusIcon().equals("X")) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append("|");
            sb.append(task.getDescription());
            if (taskName.equals("D")) {
                Deadline d = (Deadline) task;
                sb.append("|");
                sb.append(d.getDateTime());
            } else if (taskName.equals("E")) {
                Event ev = (Event) task;
                sb.append("|");
                sb.append(ev.getFrom());
                sb.append("|");
                sb.append(ev.getTo());
            }
            sb.append("\n");
        }
        fw.write(sb.toString().trim());
        fw.close();
    }
}
