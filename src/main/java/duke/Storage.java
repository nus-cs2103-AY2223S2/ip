package duke;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage system that read or stores information from hard disk to
 * program
 */

public class Storage {
    private String filePath;

    /**
     * Constructor for storage with a specific file path
     */
    public Storage() {
        this.filePath = "./data";
    }

    private void readTask(Scanner scanner, ArrayList<Task> toDoList) {
        String currentTaskInput = scanner.nextLine();

        String[] splitCurrentTaskInput = currentTaskInput.split("###");

        String typeOfTask = splitCurrentTaskInput[0];
        boolean isMarked = splitCurrentTaskInput[1].equals("[X]");

        Task newTask;

        switch (typeOfTask) {
        case "[E]":
            newTask = new Event(splitCurrentTaskInput[2]);

            break;
        case "[T]":
            newTask = new Todo(splitCurrentTaskInput[2]);

            break;
        case "[D]":
            newTask = new Deadline(splitCurrentTaskInput[2]);
            break;

        default:
            return;
        }

        toDoList.add(newTask);
        if (isMarked) {
            newTask.mark();
        }

        if (splitCurrentTaskInput.length > 3) {
            LocalDate deadline = LocalDate.parse(splitCurrentTaskInput[3]);
            newTask.setDeadline(deadline);
        }

    }

    /**
     * read tasks from hard disk to arraylist of tasks
     * 
     * @param toDoList ArrayList of all tasks
     */

    public void readStorage(ArrayList<Task> toDoList) {
        try {
            (new File(this.filePath)).mkdirs();
            File savedFile = new File(this.filePath + "/duke.txt");
            savedFile.createNewFile();
            Scanner scanner = new Scanner(savedFile);
            while (scanner.hasNextLine()) {
                readTask(scanner, toDoList);
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("cannot create new file");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("file missing unmarked/marked");
        }
    }

    /**
     * Stores tasks from arraylist to hard disk
     * 
     * @param toDoList ArrayList of all tasks
     */
    public void writeToFile(TaskList toDoList) {
        try {
            FileWriter saveFileWriter = new FileWriter(this.filePath + "/duke.txt", false);
            for (int i = 0; i < toDoList.size(); i++) {
                saveFileWriter.write(toDoList.get(i).getTaskFileSaveFormat() + "\n");
            }

            saveFileWriter.close();
        } catch (IOException e) {
            System.out.println("failed to store to file");
        }

    }
}
