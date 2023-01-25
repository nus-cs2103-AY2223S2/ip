package duke;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        this.filePath = "./data";
    }

    public void readStorage(ArrayList<Task> toDoList) {
        try {
            (new File(this.filePath)).mkdirs();
            File savedFile = new File(this.filePath + "/duke.txt");
            savedFile.createNewFile();
            Scanner scanner = new Scanner(savedFile);
            while (scanner.hasNextLine()) {
                String currentTask = scanner.nextLine();
                String typeOfTask = currentTask.substring(0, 3);
                boolean isMarked = currentTask.substring(3, 6).equals("[X]");
                switch (typeOfTask) {
                    case "[E]":
                        Task newEvent = new Event(currentTask.substring(7));
                        toDoList.add(newEvent);
                        if (isMarked) {
                            newEvent.mark();
                        }
                        break;
                    case "[T]":
                        Task newTodo = new Todo(currentTask.substring(7));
                        toDoList.add(newTodo);
                        if (isMarked) {
                            newTodo.mark();
                        }
                        break;
                    case "[D]":
                        Task newDeadline = new Deadline(currentTask.substring(7));
                        toDoList.add(newDeadline);
                        if (isMarked) {
                            newDeadline.mark();
                        }

                        break;
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("cannot create new file");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("file missing unmarked/marked");
        }
    }

    public void writeToFile(TaskList toDoList) {

        try {
            FileWriter saveFileWriter = new FileWriter(this.filePath + "/duke.txt", false);

            for (int i = 0; i < toDoList.size(); i++) {
                saveFileWriter.write(toDoList.get(i).toString() + "\n");
            }

            saveFileWriter.close();
        } catch (IOException e) {
            System.out.println("failed to store to file");
        }

    }
}
