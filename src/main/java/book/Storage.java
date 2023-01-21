package book;

import book.exception.LoadException;
import book.exception.SaveException;

import book.task.Deadline;
import book.task.Event;
import book.task.Task;
import book.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File bookSave;
    private File saveDir;
    public Storage(Path filePath) {
        this.bookSave = filePath.toFile();
        this.saveDir = this.bookSave.getParentFile();
        if (!saveDir.exists()) {
            try {
                saveDir.mkdir();
            } catch (SecurityException exception) {
                System.out.println("Book.Book had an issue with the save directory.");
            }
        }
        if (!bookSave.exists()) {
            try {
                bookSave.createNewFile();
            } catch (IOException exception) {
                System.out.print("Book.Book had an issue loading the history book.\n");
            }
        }
    }
    public void save(TaskList list) throws SaveException {
        try {
            FileWriter writeToFile = new FileWriter(this.bookSave);
            for (int i = 0; i < list.listSize(); i++) {
                writeToFile.write(list.get(i).saveString() + "\n");
            }
            writeToFile.close();
        } catch (IOException exception) {
            throw new SaveException("There was an issue recording down your Book.Book.");
        }
    }
    public ArrayList<Task> load() throws LoadException {
        ArrayList<Task> list = new ArrayList<Task>(100);
        try {
            Scanner fileReader = new Scanner(this.bookSave);
            while (fileReader.hasNextLine()) {
                String taskLine = fileReader.nextLine();
                String[] task = taskLine.split(";", 5);
                switch (task[0]) {
                case "T":
                    list.add(new ToDo(task[2]));
                    break;
                case "D":
                    list.add(new Deadline(task[2], Parser.parseDateTime(task[3])));
                    break;
                case "E":
                    list.add(new Event(task[2], Parser.parseDateTime(task[3]),
                            Parser.parseDateTime(task[4])));
                    break;
                default:
                    throw new LoadException("The saved Book.Book is corrupted.");
                }
                if (task[1].equals("true")) {
                    list.get(list.size() - 1).mark();
                }
            }
            return list;
        } catch (FileNotFoundException exception) {
            throw new LoadException("There was an issue finding the saved Book.Book.");
        }
    }

}
