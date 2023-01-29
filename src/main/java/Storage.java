import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    Path path;

    public Storage(String pathStr) {
        String home = System.getProperty("user.dir");
        this.path = Paths.get(home, pathStr);
    }

    public ToDoList load() {
        try {
            //assume its ./iP-data/data,txt only
            if (!Files.exists(path)) {
                if (!Files.exists(path.getParent())) {
                    Files.createDirectories(path.getParent());
                }
                Files.createFile(path);
            }
            File f = new File(path.toString());
            ToDoList ls = new ToDoList();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(Pattern.quote("/+/"));
                String command = input[0];
                Task task;
                switch (command) {
                case "TODO":
                    task = new ToDoTask(input[2]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                case "DEADLINE":
                    task = new DeadlineTask(input[2], input[3]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                case "EVENT":
                    task = new EventTask(input[2], input[3], input[4]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                }
            }
            return ls;
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    public void save(ToDoList ls)  {
        try {
            FileWriter fw = new FileWriter(path.toString());
            int count = ls.getToDoCount();
            for (int i = 1; i <= count; i++) {
                fw.write(ls.getTask(i).save());
            }
            fw.close();
        } catch (Exception e) {
        }
    }

}
