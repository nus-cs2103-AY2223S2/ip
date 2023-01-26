package duke;

import duke.task.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;

public class Storage {
    java.nio.file.Path filePath;
    public Storage(java.nio.file.Path filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner scanner = new Scanner(filePath.toFile());
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (scanner.hasNextLine()) {
                String cur = scanner.nextLine();
//                System.out.println(cur);
                String[] temp = cur.split(" \\| ");
//                for(int i = 0; i < temp.length; i++) {
//                    System.out.println(temp[i]);
//                }
                if (temp[0].equals("T")) {
                    tasks.add(new Todo(temp[2], parseBoolean(temp[1])));
                } else if (temp[0].equals("D")) {
                    tasks.add(new Deadline(temp[2], temp[3], parseBoolean(temp[1])));
                } else if (temp[0].equals("E")) {
                    tasks.add(new Event(temp[2], temp[4], temp[3], parseBoolean(temp[1])));
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading Error\n");
        }
    }

    public void refresh(TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
            String res = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task cur = tasks.get(i);
                res += cur.getSymbol() + " | ";
                res += ((cur.getStatusIcon() == "X") ? "true" : "false") + " | ";
                res += cur.getDetailedDescription();
                res += "\n";
            }
            writer.write(res);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file.\n");
        }
    }
}
