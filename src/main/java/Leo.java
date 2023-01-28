<<<<<<< Updated upstream
import java.util.*;
=======
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import leo.task.LeoTaskException;
import leo.task.TaskList;

>>>>>>> Stashed changes

public class Leo {

    private TaskList taskList;
    public static void main(String[] args) throws LeoTaskException {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Argentina, I'm Leo!\n" + "What can I do for you?");
        new Leo().start();
    }

    public void start() throws LeoTaskException {
        //Get saved state

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine(); // reads in command fed by user
<<<<<<< Updated upstream

=======
        readFile();

        if (taskList == null) {
            taskList = new TaskList();
        }
        
>>>>>>> Stashed changes
        while (!cmd.equals("bye")) {
            taskList.processRequest(cmd);
            cmd = sc.nextLine();
        }
        writeObjectToFile(taskList);
        System.out.println("It was nice talking, see you soon!");
        sc.close();
    }

    public void writeObjectToFile(Object obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream("taskList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in taskList.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readFile() {
        try {
            FileInputStream fileIn = new FileInputStream("taskList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            taskList = (TaskList) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException i) {
            taskList = new TaskList();
            writeObjectToFile(taskList);
            return;
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("TaskList class not found");
            c.printStackTrace();
            return;
        }
    }
}
