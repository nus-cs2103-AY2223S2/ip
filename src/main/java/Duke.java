import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static MyDuke duke = new MyDuke();
    private static Path FILEPATH = Paths.get("../../../data/");

    public static void main(String[] args) 
            throws InvalidCommandException, IOException, ClassNotFoundException {
        
        duke.init();
        processCommands();


        try {
            load();
        } catch (FileNotFoundException p) {
            System.out.println("Nothing to load");
        }

        
    }

    public static void processCommands() 
            throws InvalidCommandException, IOException {
        boolean isBye = false;
        DukeIO.showPrompt();
        while (!isBye) {
            String[] tokens = DukeIO.tokenise(sc);
            isBye = handle(tokens);
            if (!isBye) {
                DukeIO.showPrompt();
            }
        }
        save();
    }

    private static boolean handle(String[] tokens) throws InvalidCommandException {
        String cmd = tokens[0];
        if (cmd.length()==0) {  
            return false;    
        } else if (cmd.equals("bye")) {   
            duke.quit(); 
            return true; 
        } else {   
            duke.exec(tokens); 
        }

        return false;
    }
    
    private static void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("../../../data/duke.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        @SuppressWarnings("unchecked")
        ArrayList<Task> loadedTasks = (ArrayList<Task>) ois.readObject();
        MyDuke.loadTask(loadedTasks);

        ois.close();
        System.out.println("loaded");
    }
    
    private static void save() throws IOException {
        ArrayList<Task> allTasks = MyDuke.getAllTasks();

        // creates directory if it doesnt exist        
        Files.createDirectories(Paths.get("../../../data/"));

        FileOutputStream out = new FileOutputStream("../../../data/duke.txt");
        ObjectOutputStream o = new ObjectOutputStream(out);

        o.writeObject(allTasks);
        
        o.close();
        out.close();
    }
}
