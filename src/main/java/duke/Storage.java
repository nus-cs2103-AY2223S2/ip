package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    private TaskList list;

    public Storage(TaskList list) {
        this.list = list;
    }

    public void initalizeList() {
        try {
            File yourFile = new File("duke.txt");
            yourFile.createNewFile();
            try (BufferedReader br = new BufferedReader(new FileReader("duke.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    char taskType = line.charAt(1);

                    boolean isMarked = false;
                    Character markedChar = line.charAt(4);

                    String description = line.substring(7);
                    if (markedChar.equals('X')) {
                        isMarked = true;
                    }
                    switch (taskType) {
                        case 'T':
                            Todo todoTask = new Todo(description, isMarked);
                            list.add(todoTask);
                            break;
                        case 'E':
                            String modifiedDescription = description.split(" \\(from: ")[1];
                            LocalDate fromDate = LocalDate.parse(modifiedDescription.split(" to: ")[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                            LocalDate toDate = LocalDate.parse(modifiedDescription.split(" to: ")[1].split("\\)")[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                            Event eventTask = new Event(description.split(" \\(from: ")[0], isMarked, fromDate, toDate);
                            list.add(eventTask);
                            break;
                        case 'D':
                            LocalDate byDate = LocalDate.parse(description.split(" \\(by: ")[1].split("\\)")[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                            Deadline deadlineTask = new Deadline(description.split(" \\(by: ")[0], isMarked, byDate);
                            list.add(deadlineTask);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("duke.txt"));
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
