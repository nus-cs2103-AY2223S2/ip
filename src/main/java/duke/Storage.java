package duke;

import java.io.File;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Storage {
    protected String home = System.getProperty("user.home");
    protected Path path;
    protected File file;

    Storage() {
        path = Paths.get(home,"data","duke.txt");
        try {
            file = new File(path.toString());
            file.getParentFile().mkdirs();
            if (file.createNewFile())
                System.out.println("file created");
            else {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Fail to create file");
            e.printStackTrace();
        }
    }

    /**
     * Write task to hard copy
     * @param t
     */
    public void write(Task t) {
        int isDone = 0;
        if(t.getIsDone())
            isDone = 1;
        String description = t.type + " | " + isDone + " | " + t.getDescriptionAndTime() + System.lineSeparator();
        try {
            Files.writeString(path,description,StandardCharsets.UTF_8,StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("File doesn't exist");
        }
    }

    /**
     * Update hard copy by rewriting everything
     * Only happen when a task is marked/unmarked/deleted.
     * @param list
     */
    public void update(ArrayList<Task> list) {
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Fail to create file");
            e.printStackTrace();
        }
        Iterator<Task> it = list.iterator();
        while (it.hasNext()) {
            int isDone = 0;
            Task t = it.next();
            if (t.getIsDone())
                isDone = 1;
            String description = t.type + " | " + isDone + " | " + t.getDescriptionAndTime() + System.lineSeparator();
            try {
                Files.writeString(path,description,StandardCharsets.UTF_8,StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("File doesn't exist");
            }
        }
    }

}
