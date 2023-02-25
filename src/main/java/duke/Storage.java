package duke;

import java.io.*;
import java.util.Scanner;

public class Storage {
    String path;
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Method to load content of the saved list and create the file for list storage if it does not exist.
     * @throws IOException
     */
    public void initialize() throws IOException {
        File newFile = new File(this.path);

        newFile.getParentFile().mkdirs();

        if (! newFile.exists()) {
            newFile.createNewFile();
        }
        loadTasks(newFile);
    }

    /**
     * Saves the current list into a text file.
     */
    public void saveTasks() {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            String tasks = "";
            for (Task task : TaskList.getContent()) {
                tasks += task.getSaveString() + "\n";
            }
            bufferWriter.write(tasks);
            bufferWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadTasks(File file) {
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String nextTask = sc.nextLine();
                String[] sections = nextTask.split(" / ", 6);
                String type = sections[0];
                String isDone = sections[1];

                Task currentTask = null;
                if (type.equals("todo")) {
                    currentTask = new Todo(sections[2]);
                } else if (type.equals("deadline")) {
                    currentTask = new Deadline(sections[2], Parser.retrieveDate(sections[3]));
                } else if (type.equals("event")) {
                    currentTask = new Event(sections[2], Parser.retrieveDate(sections[3]), Parser.retrieveDate(sections[4]));
                } else {
                    System.out.println("error");
                }

                if (isDone.equals("done")) {
                    currentTask.setDone();
                }
                TaskList.getContent().add(currentTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
