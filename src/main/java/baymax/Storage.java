package baymax;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filepath = "./data/Baymax.txt";
    private final File file;
    private final Scanner fileReader;
    private final ArrayList<Task> myList;

    public Storage() throws FileNotFoundException {
        file = new File(filepath);
        fileReader = new Scanner(file);
        myList = new ArrayList<>();
        if (!file.isFile()) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Stores the task list into the data file.
     * @throws FileNotFoundException If there is an error in storing the task list.
     */
    public TaskList load() {
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            if (data.charAt(1) == 'T') {
                Task todo = new Todo(data.substring(7));
                if (isMarkedAsDone(data)) todo.markAsDone();
                myList.add(todo);
            }
            if (data.charAt(1) == 'D') {
                String one = data.split(" " + "[(]" + "by: ")[0].substring(7);
                String two = data.split(" " + "[(]" + "by: ")[1];
                two = two.substring(0, two.length() - 1);
                Task deadline = new Deadline(one, two);
                if (isMarkedAsDone(data)) deadline.markAsDone();
                myList.add(deadline);
            }
            if (data.charAt(1) == 'E') {
                String one = data.split(" " + "[(]" + "from: ")[0].substring(7);
                String two = data.split(" " + "[(]" + "from: ")[1].split(" to: ")[0];
                String three = data.split(" " + "[(]" + "from: ")[1].split(" to: ")[1];
                three = three.substring(0, three.length() - 1);
                Task event = new Event(one, two, three);
                if (isMarkedAsDone(data)) event.markAsDone();
                myList.add(event);
            }
        }
        return new TaskList(myList);
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

    public Boolean isMarkedAsDone(String string) {
        return string.charAt(4) == 'X';
    }
}
