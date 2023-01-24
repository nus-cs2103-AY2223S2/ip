package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.*;

import entities.*;
import exceptions.DukeFileNotFoundException;
import exceptions.InvalidInputException;

public class Storage {
    private final File file;
    private final String home;
    private boolean directoryExists;
    private TaskList list;

    public Storage(String homeName, TaskList list) {
        this.home = System.getProperty(homeName);
        this.list = list;
        Path path = Paths.get(home, "Desktop", "cs2103 IP", "data", "duke.txt");
        this.directoryExists = java.nio.file.Files.exists(path);
        this.file = path.toFile();

    }

    public void connect() throws DukeFileNotFoundException {
        try {
            if (!directoryExists) {
                throw new DukeFileNotFoundException("An error occurred when connecting to the database!");
            }
            System.out.println("Duke has connected to the storage!");
        } catch (DukeFileNotFoundException e) {
            System.out.println(e);
        }

    }

    public void load() throws DukeFileNotFoundException {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(" \\| ");
                switch (input[0]) {
                case ("T"):
                    Todo todo = new Todo(input[2]);
                    if (input[1].equals("1")) {
                        todo.setDone();
                    } else {
                        todo.setUndone();
                    }
                    list.addTask(todo);
                    break;
                case ("D"):
                    Deadline deadline = new Deadline(input[2], input[3]);
                    if (input[1].equals("1")) {
                        deadline.setDone();
                    } else {
                        deadline.setUndone();
                    }
                    list.addTask(deadline);
                    break;
                case ("E"):
                    Event event = new Event(input[2], input[3], input[4]);
                    if (input[1].equals("1")) {
                        event.setDone();
                    } else {
                        event.setUndone();
                    }
                    list.addTask(event);
                    break;
                case (" "):
                    break;
                default:
                    throw new InvalidInputException("Sorry! I can't seem to understand what was the input!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(e.toString());
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
    }

    public void save() {
        try {
            FileWriter myWriter = new FileWriter("data\\duke.txt");
            for (int i = 0; i < list.getSize(); i++) {
                String line = list.getTask(i).toSave();
                myWriter.write(line);

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error! I cannot write to the file!");
        }
    }
}
