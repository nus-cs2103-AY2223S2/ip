import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageList {
    private ArrayList<Task> list;

    public StorageList() {
        this.list = new ArrayList<>();
        try {
            Path path = Paths.get("data", "duke.txt");
            File file = new File(String.valueOf(path));
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] linearr = line.split("\\] ");
                String[] linetype = linearr[0].split("\\]");
                list.add(new Task(linearr[1], linetype[0].substring(1), linetype[1].substring(1)));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateStorage() {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();
            java.nio.file.Path path = java.nio.file.Paths.get("data", "duke.txt");
            FileWriter writer = new FileWriter(String.valueOf(path));
            for (Task str : list) {
                writer.write(str.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void markTask(int taskno) {
        list.get(taskno).markAsDone();
        updateStorage();
        System.out.println(list.get(taskno));
    }

    public void unmarkTask(int taskno) {
        list.get(taskno).markAsUndone();
        updateStorage();
        System.out.println(list.get(taskno));
    }

    public void printList() {
        System.out.println("Here are the tasks in your list: ");
        for (int x = 0; x < list.size(); x++) {
            System.out.println((x + 1) + "." + list.get(x));
        }
        System.out.println("");
    }

    public void addTodo(String sentence) throws DukeException {
        Todo t = new Todo(sentence);
        list.add(t);
        updateStorage();
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
    }

    public void addDeadline(String sentence, String by) throws DukeException {
        Deadline t = new Deadline(sentence, by);
        if (!t.checkFormat()) {
            list.add(t);
            updateStorage();
            System.out.println("Got it, I've added this task:");
            System.out.println(t);
        } else {
            System.out.println("Wrong Format, Please fill in with the following format: YYYY-MM-DD h:mm");
        }
    }

    public void addEvent(String sentence, String from, String to) {
        Event t = new Event(sentence, from, to);
        list.add(t);
        updateStorage();
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
    }

    public void deleteTask(int taskno) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskno));
        list.remove(taskno);
        updateStorage();
    }

    public int lengthOflist() {
        return list.size();
    }
}
