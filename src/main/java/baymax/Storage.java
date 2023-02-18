package baymax;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;

import exceptions.BaymaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Stores the task list into the data file.
     * @throws BaymaxException If there is an error in storing the task list.
     */
    public ArrayList<Task> load() throws BaymaxException {
        ArrayList<Task> myList = new ArrayList<>();
        try {
            File file = new File(filepath);
            if (!file.isFile()) {
                throw new BaymaxException("File not found");
            }
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                if (data.charAt(1) == 'T') {
                    Task todo = new Todo(data.substring(7));
                    if (data.charAt(4) == 'X') {
                        todo.markAsDone();
                    }
                    myList.add(todo);
                }
                if (data.charAt(1) == 'D') {
                    String one = data.split(" " + "[(]" + "by: ")[0].substring(7);
                    String two = data.split(" " + "[(]" + "by: ")[1];
                    int l = two.length();
                    two = two.substring(0, l - 1);
                    Task deadline = new Deadline(one, two);
                    if (data.charAt(4) == 'X') {
                        deadline.markAsDone();
                    }
                    myList.add(deadline);
                }
                if (data.charAt(1) == 'E') {
                    String one = data.split(" " + "[(]" + "from: ")[0].substring(7);
                    String two = data.split(" " + "[(]" + "from: ")[1].split(" to: ")[0];
                    String three = data.split(" " + "[(]" + "from: ")[1].split(" to: ")[1];
                    int l = three.length();
                    three = three.substring(0, l - 1);
                    Task even = new Event(one, two, three);
                    if (data.charAt(4) == 'X') {
                        even.markAsDone();
                    }
                    myList.add(even);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return myList;
    }

    /**
     * Stores the task list into the data file.
     * @param taskList The task list to be stored.
     * @throws RuntimeException If there is an error in storing the task list.
     */
    public void store(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filepath);
            for(Task task: taskList.getTaskList()) {
                writer.write(task.writeToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
