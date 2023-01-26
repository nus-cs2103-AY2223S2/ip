package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected TaskList taskList;

    public Storage (TaskList taskList) {
        this.taskList = taskList;
    }

    public void loadList() {
        try {
            File f = new File("src/main/data/task_list.txt");
            System.out.println("Loading List...");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                char type = line.charAt(0);
                String info = line.substring(2);
                switch (type) {
                case 'T':
                    Task td = new Todo(info);
                    check_add(line, td);
                    break;
                case 'D':
                    String[] deadLine = info.split(" /by ",2);
                    Task dd = new Deadline(deadLine[0], deadLine[1]);
                    check_add(line, dd);
                    break;
                case 'E':
                    String[] event = info.split(" /from ",2);
                    String[] time = event[1].split(" /to ",2);
                    Task et = new Event(event[0], time[0], time[1]);
                    check_add(line, et);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("task_list.txt Not Found");
            try {
                System.out.println("Creating the File...");
                new File("src/main/data/task_list.txt").createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void check_add(String line, Task t) {
        if (line.charAt(1) == '0') {
            t.done = 0;
        } else {
            t.done = 1;
        }
        taskList.add(t);
    }

    public void saveList() {
        try {
            FileWriter fw = new FileWriter("src/main/data/task_list.txt");
            for (Task tk : taskList.task_list) {
                fw.write(tk.getInfo().toString()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
