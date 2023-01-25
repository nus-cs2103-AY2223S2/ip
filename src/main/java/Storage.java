import exception.EmptyStorageException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
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
                throw new EmptyStorageException("EmptyStorageException");

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
            System.out.println("Error occurred, can't open file or ");
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
}
