package Duke.Storage;


import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.DukeMainExceptions;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;


public class Storage {
    protected File file;
    protected String filePath;

    public Storage(String filePath) throws  IOException{
        this.filePath = filePath;
        this.file = new File(filePath);
        // Check whether the file already exist
        if (this.file.exists() == false) {
            new File("data").mkdir();
            this.file.createNewFile();
        }
    }

    public ArrayList<Task> loadTasks() throws IOException, DukeMainExceptions {
        ArrayList<Task> database = new ArrayList<>(100);
        Scanner scanner = new Scanner(this.file);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" \\| ");
            Task task;
            if (splitInput[0].equals("T")) {
                task = new Todo(splitInput[2]);
            } else if (splitInput[0].equals("E")) {
                task = new Event(splitInput[2], splitInput[3], splitInput[4]);
            } else if (splitInput[0].equals("D")) {
                task = new Deadline(splitInput[2], splitInput[3]);
            } else {
                throw new DukeMainExceptions("Unknown task type. Please check again.");
            }
            // Check the status of the task
            if (splitInput[1].equals("X")) {
                task.markDone();
            }

            database.add(task);
        }
        return database;
    }

    public void storeTasks(ArrayList<Task> taskList) throws IOException {
//        try {
//            FileWriter fw = new FileWriter(filePath);
//            for (Task task : taskList) {
//                fw.write(task.printTask() + "\n");
//            }
//            fw.close();
//        }
//        catch (IOException exception) {
//            System.out.println("Path invalid. Please try again.");
//        }
        String filePath = "./data/duke.txt";
        File file  = new File(filePath);

        final FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (Task task : taskList) {
            bw.append(task.printTask());
        }
        bw.close();
    }

}
