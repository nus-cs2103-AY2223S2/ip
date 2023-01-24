import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String path;

    Storage(String path) {
        this.path = path;
    }

    public TaskList load() {
        TaskList tl = new TaskList();

        try {
            File f = new File(this.path);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String content = s.nextLine();
                tl.addTask(Task.parseTask(content));
            }
        } catch (FileNotFoundException e) {
            System.out.println("tasks.txt not found, generating new task list...");
        } catch (InvalidTaskStringException e) {
            System.out.println(e.getMessage());
        }

        return tl;
    }

    public void save(TaskList tl) {
        try {
            FileWriter fw = new FileWriter(this.path);

            // remove enumeration
            String[] lines = tl.toString().split("\n");
            StringBuilder newContent = new StringBuilder();

            for (String line: lines) {
                newContent.append(line.substring(3));
                newContent.append('\n');
            }

            // remove last new line
            newContent.deleteCharAt(newContent.length() - 1);

            // write to file
            fw.write(newContent.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
