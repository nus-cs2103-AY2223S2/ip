package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String PATHNAME;

    Storage(String path, TaskList taskList) {
        PATHNAME = path;
        try {
            Scanner sc = new Scanner(new File(PATHNAME));
            while (sc.hasNextLine()) {
                String[] tokens = sc.nextLine().split(",");
                taskList.parseEventFromFile(tokens);
            }
        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
        }
    }

    void updateData(TaskList tasks) {
        File file = new File(PATHNAME);
        file.getParentFile().mkdirs();
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                sb.append(task.asTokens()).append('\n');
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
