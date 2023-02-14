package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.*;

public class Database {
    private File savedFile;

    public Database(File savedFile) {
        this.savedFile = savedFile;
    }

    public ArrayList<Task> populateTasks() throws IOException {
        Scanner sc;
        ArrayList<Task> tasks = new ArrayList<>();
        String command;
        String instr;
        String extendedInstr;
        String[] instrSplit;
        Task task;
        boolean completed;

        try {
            sc = new Scanner(savedFile);
            while (sc.hasNextLine()) {
                extendedInstr = sc.nextLine();
                // first character in instruction shows if task is completed
                completed = Integer.parseInt(extendedInstr.substring(0, 1)) == 1 ? true : false;
                instr = extendedInstr.substring(2);
                instrSplit = instr.split(" ");
                command = instrSplit[0];

                if (command.equals("todo")) {
                    task = new Todo(instr);
                } else if (command.equals("deadline")) {
                    task = new Deadline(instr);
                } else {
                    task = new Event(instr);
                }
                tasks.add(task);

                if (completed) {
                    task.setDone();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            savedFile.createNewFile();
        }
        return tasks;
    }

    public void saveTasks(String encodedTasks) {
        try {
            FileWriter fw = new FileWriter(savedFile);
            fw.write(encodedTasks);
            fw.flush();
            fw.close();
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                System.out.print("............");
            }
            System.out.print("\n");
            Thread.sleep(500);
            System.out.println("Your tasks have been successfully saved! :-)");
        } catch (IOException e) {
            System.out.println("Sorry, unable to save your tasks right now.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}