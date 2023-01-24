package cbot.util;

import cbot.task.Deadline;
import cbot.task.Event;
import cbot.task.Task;


import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStuff {
    private final File file;
    
    public FileStuff(String path) {
        this.file = new File(path);
    }
    
    public String getPath() {
        return this.file.getAbsolutePath();
    }
    
    public boolean pathExists() {
        return this.file.getParentFile().exists();
    }
    
    public boolean fileExists() {
        return this.file.exists();
    }
    
    public void makeFile() throws IOException  {
        this.file.getParentFile().mkdir();
        this.file.createNewFile();
    }
    
    public void saveFile(TaskList tl) throws IOException {
        if (!fileExists()) {
            makeFile();
        }
        
        FileWriter fw = new FileWriter(this.file);
        fw.write(tl.makeFileFriendly());
        fw.close();
    }
    
    public TaskList loadFile() throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        
        ArrayList<Task> tdl = new ArrayList<Task>();
        
        while (s.hasNext()) {
            String[] taskStr = s.nextLine().split(Task.SEP);
            
            // type SEP done SEP desc SEP due/from SEP to
            
            boolean isDone = taskStr[1].equals(Task.DONE_TRUE);
            String desc = taskStr[2];
            
            switch (taskStr[0]) {
            case "T":
                tdl.add(new Task(desc, isDone));
                break;
                
            case "D":
                tdl.add(new Deadline(desc, isDone, LocalDateTime.parse(taskStr[3])));
                break;
                
            case "E":
                tdl.add(new Event(desc, isDone, LocalDateTime.parse(taskStr[3]), LocalDateTime.parse(taskStr[4])));
                break;
            }
        }
        
        return new TaskList(tdl);
    }
}