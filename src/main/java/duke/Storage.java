package duke;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task = null;
                String data = sc.nextLine();
                String[] commandInFile = data.split(" \\| ");
                boolean isDoneInFile = commandInFile[1].equals("1");
                if (commandInFile[0].equals("T")) {
                    task = new Todo(commandInFile[2], true);
                    task.isDone = isDoneInFile;
                    tasks.add(task);
                } else if (commandInFile[0].equals("D")) {
                    task = new Deadline(commandInFile[2], commandInFile[3], true);
                    task.isDone = isDoneInFile;
                    tasks.add(task);
                } else if (commandInFile[0].equals("E")) {
                    task = new Event(commandInFile[2], commandInFile[3], commandInFile[4], true);
                    task.isDone = isDoneInFile;
                    tasks.add(task);
                }
                if(task != null) {
                    task.isDone = isDoneInFile;
                }
            }
        } catch (FileNotFoundException e) {
            new File("data").mkdir();
            new File(filePath).createNewFile();
        }
        return tasks;
    }

    public void updateFile(TaskList t) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        if(t.getTaskCounter() > 0) {
            for (int i = 0; i < t.getTaskCounter(); i++) {
                appendToFile(t.get(i).file());
            }
        }
    }

    private void appendToFile(String info) throws IOException {
        //Constructs a FileWriter object given a file name
        // with a boolean indicating whether to append the data written
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(info + "\n");
        fw.close();
    }
}
