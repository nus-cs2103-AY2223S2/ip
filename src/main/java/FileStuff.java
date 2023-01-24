import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStuff {
    private static final String PATH = "../data/cbot_save.txt";
    
    static boolean pathExists() {
        File f = new File(PATH);
        return f.getParentFile().exists();
    }
    
    static boolean fileExists() {
        File f = new File(PATH);
        return f.exists();
    }
    
    static void saveFile(TaskList tl) throws IOException {
        File f = new File(PATH);
        f.getParentFile().mkdir();
        f.createNewFile();
        
        FileWriter fw = new FileWriter(f);
        fw.write(tl.makeFileFriendly());
        fw.close();
    }
    
    static TaskList loadFile() throws FileNotFoundException {
        Scanner s = new Scanner(new File(PATH));
        
        ArrayList<Task> tdl = new ArrayList<Task>();
        
        while (s.hasNext()) {
            String[] a = s.nextLine().split(Task.SEP);
            
            // type SEP done SEP desc SEP due/from SEP to
            
            boolean isDone = a[1].equals(Task.DONE_TRUE);
            String desc = a[2];
            
            switch (a[0]) {
            case "T":
                tdl.add(new Task(desc, isDone));
                break;
                
            case "D":
                tdl.add(new Deadline(desc, isDone, LocalDateTime.parse(a[3])));
                break;
                
            case "E":
                tdl.add(new Event(desc, isDone, LocalDateTime.parse(a[3]), LocalDateTime.parse(a[4])));
                break;
            }
        }
        
        return new TaskList(tdl);
    }
}