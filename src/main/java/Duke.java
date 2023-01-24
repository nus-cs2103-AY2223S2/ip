import java.util.Scanner;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    public static MyDuke duke = new MyDuke();
    public static Scanner sc = new Scanner(System.in);
    public static Path FILEPATH = Paths.get("../../../data/");

    public static void main(String[] args) throws InvalidCommandException, IOException, ClassNotFoundException {
        duke.init();

        try {
            load();
        } catch (FileNotFoundException p) {
            System.out.println("Nothing to load");
        }

        processCommands(sc);
    }

    public static void processCommands(Scanner sc) throws InvalidCommandException, IOException {
        boolean bye = false;
        showPrompt();
        while (!bye) {
            String[] tokens = tokenise(sc);
            bye = handle(tokens);
            if (!bye) {
                showPrompt();
            }
        }
        save();
    }

    private static void showPrompt() {
        System.out.print("MyDuke >  ");
    }

    private static void showReply() {
        System.out.print("|     ");
    }

    private static String[] tokenise(Scanner sc) {
        String[] tokens = sc.nextLine().split(" ");
        return tokens;
    }

    private static boolean handle(String[] tokens) throws InvalidCommandException {
        String cmd = tokens[0];
        if (cmd.equals("\n")) {  return false;    }
        else if (cmd.equals("bye")) {   showReply(); duke.quit(); return true; }
        else {  showReply(); duke.exec(tokens); } 
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

        Files.createDirectories(Paths.get("../../../data/"));
        FileOutputStream out = new FileOutputStream("../../../data/duke.txt");
        ObjectOutputStream o = new ObjectOutputStream(out);

        o.writeObject(allTasks);
        System.out.println("saved");
        o.close();
        out.close();
    }
}
