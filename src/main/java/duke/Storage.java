package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String path;
    File file;
    Parser parser;
    
    public Storage() {
        this.path = null;
        this.file = null;
        this.parser = null;
    }
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
        this.parser = new Parser();
        
        if (! file.exists()) {
            if (! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create new database file.");
            }
        }
    }
    
    public TaskList loadData() {
        Scanner s;
        try {
            s = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read database.");
            return new TaskList();
        }
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            taskList.add(this.parser.parseTask(s.nextLine()));
        }
        return taskList;
    }
    
    public void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        
        for (Task task : taskList.getTasks()) {
            fw.write(task.formatTask() + System.lineSeparator());
        }
        fw.close();
    }
    

}
