package Duke.Storage;

import java.io.*;
import java.util.Scanner;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.FileException;
import Duke.TaskList;
import Duke.Tasks.Task;

public class Storage {
    protected File file;
    protected String filePath;
    protected TaskList tasks;
    protected BufferedReader content;
    protected FileReader fileReader;

    /**
     * The constructor of Storage class. Use to create storage instance.
     *
     * @param filePath The path that directed to the location of the file.
     * @throws IOException
     */
    public Storage(String filePath) throws  IOException{
        this.filePath = filePath;
        this.file = new File(filePath);
        // Check whether the file already exist
        if (this.file.exists() == false) {
            new File("data").mkdir();
            this.file.createNewFile();
        }
    }

    public BufferedReader load() {
        assert this.fileReader != null && this.filePath != null && this.file != null;
        if (this.content != null) {
            return content;
        }
        this.content = new BufferedReader(this.fileReader);
        return this.content;
    }

    /**
     * Load the tasks that has been stored inside the file.
     *
     * @return Return the task list.
     * @throws IOException
     * @throws DukeMainExceptions
     */
    public TaskList loadTasks() throws IOException, DukeMainExceptions {
        this.tasks = new TaskList();
        Scanner scanner = new Scanner(this.file);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" \\| ");
            String splitDescription;
            Task task;
            if (splitInput[0].equals("T")) {
                splitDescription = input.split(" ", 2).length == 2
                        ? input.split(" ", 2)[1]
                        : "";
                task = tasks.addTodo(splitDescription);
                if  (splitInput[1].equals("X")) {
                    task.markDone();
                }
            } else if (splitInput[0].equals("E")) {
                splitDescription = input.split(" ", 2).length == 2
                        ? input.split(" ", 2)[1]
                        : "";
                task = tasks.addEvent(splitDescription);
                if  (splitInput[1].equals("X")) {
                    task.markDone();
                }
            } else if (splitInput[0].equals("D")) {
                splitDescription = input.split(" ", 2).length == 2
                        ? input.split(" ", 2)[1]
                        : "";
                task = tasks.addDeadline(splitDescription);
                if  (splitInput[1].equals("X")) {
                    task.markDone();
                }
            } else {
                throw new DukeMainExceptions("Unknown task type. Please check again.");
            }
        }
        return this.tasks;
    }

    /**
     * Store the tasks into the specific file.
     *
     * @throws IOException
     */
    public void storeTasks() throws IOException {
        String filePath = "./data/duke.txt";
        File file  = new File(filePath);

        final FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < this.tasks.getSize(); i++) {
            Task currTask = this.tasks.getTask(i);
            System.out.println(currTask.toString());
            bw.append(currTask.printTask());
        }
        bw.close();
    }

    public void store(TaskList tasks) throws FileException {
        try {
            this.file.createNewFile();
            FileWriter writer = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException ioException) {
            throw new FileException();
        }

    }
}
