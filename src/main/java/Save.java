import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Save {
    final static String path = System.getProperty("user.home") + "/ip/data";
    public static void readFromTextFile(ArrayList<Task> tasks) {
        try {
            File dir = new File(path);
            dir.mkdirs();
            File taskFile = new File(path + "/duke.txt");
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            Scanner fileScanner = new Scanner(taskFile);
            while(fileScanner.hasNext()) {
                String input = fileScanner.nextLine();
                Task task = parseTaskString(input);
                tasks.add(task);
            }
        } catch(IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Task parseTaskString(String input) throws DukeException{
        String[] parsed = input.split(" \\| ");
        Task task;
        int len = parsed.length;
        switch (len) {
            case 3: // this is a task
                Todo todo = new Todo(parsed[2]);
                if (parsed[1].equals("1")) {
                    todo.mark(); // need to change?
                }
                task = todo;
                break;
            case 4: // this is a deadline
                Deadline deadline = new Deadline(parsed[2], parsed[3]);
                if (parsed[1].equals("1")) {
                    deadline.mark(); // need to change?
                }
                task = deadline;
                break;
            case 5: // this is an event
                Event event = new Event(parsed[2], parsed[3], parsed[4]);
                if (parsed[1].equals("1")) {
                    event.mark(); // need to change?
                }
                task = event;
                break;
            default:
                throw new DukeException("Unable to read file contents!");
        }
        return task;
    }

    public static void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(path + "/duke.txt");
            for (Task task: tasks) {
                fw.write(task.getText());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

