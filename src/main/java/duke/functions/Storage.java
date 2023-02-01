package duke.functions;

import duke.ToDoList;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

import java.io.File;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private Path path;

    public Storage(String pathStr) {
        String home = System.getProperty("user.dir");
        this.path = Paths.get(home, pathStr);
    }

    public ToDoList load() {
        try {
            if (!Files.exists(path)) {
                if (!Files.exists(path.getParent())) {
                    Files.createDirectories(path.getParent());
                }
                Files.createFile(path);
            }
            File f = new File(path.toString());
            ToDoList list = new ToDoList();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] inputs = sc.nextLine().split(Pattern.quote("/+/"));
                String command = inputs[0];
                Task task;
                switch (command) {
                case "TODO":
                    task = new ToDoTask(inputs[2]);
                    if (inputs[1].equals("DONE")) {
                        task.markDone();
                    }
                    list.add(task);
                    break;
                case "DEADLINE":
                    task = new DeadlineTask(inputs[2], inputs[3]);
                    if (inputs[1].equals("DONE")) {
                        task.markDone();
                    }
                    list.add(task);
                    break;
                case "EVENT":
                    task = new EventTask(inputs[2], inputs[3], inputs[4]);
                    if (inputs[1].equals("DONE")) {
                        task.markDone();
                    }
                    list.add(task);
                    break;
                }
            }
            return list;
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    public void save(ToDoList list)  {
        try {
            FileWriter fw = new FileWriter(path.toString());
            int count = list.getToDoCount();
            for (int i = 1; i <= count; i++) {
                fw.write(list.getTask(i).save());
            }
            fw.close();
        } catch (Exception e) {
        }
    }

}
