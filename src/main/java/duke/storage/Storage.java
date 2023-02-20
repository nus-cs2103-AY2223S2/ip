package duke.storage;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Iterator;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.parser.Parser;

public class Storage {
    private static String path;
    private static File data;

    private static File dataFolder;

    public Storage(String path, String folderPath) {
        this.path = path;
        this.data = new File(path);
        this.dataFolder = new File(folderPath);
    }


    public static TaskList loadData() throws IOException {
        Parser logic = new Parser();
        TaskList list = new TaskList();
        if (!dataFolder.exists()) {
            System.out.println("The data folder is not found, creating new data folder");
            dataFolder.mkdir();
            System.out.println("Folder created successfully");
            File f = new File(dataFolder, "duke.txt");
            try {
                f.createNewFile();
                System.out.printf("file created successfully\n");
            } catch (IOException e) {
                throw new DukeException("Error creating file: " + e.getMessage());
            }
        } else if(!data.exists()) {
            System.out.println("file does not exist, creating new file");
            File f = new File(dataFolder, "duke.txt");
            try {
                f.createNewFile();
                System.out.printf("file create successfully");
            } catch (IOException e) {
                throw new DukeException("Error creating file: " + e.getMessage());
            }
        } else {
            List<String> tmp = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            Iterator<String> itr = tmp.iterator();
            Task newTask;
            while (itr.hasNext()) {
                String curr = itr.next();
                String[] currLine = curr.split(" \\| ");
                String type = currLine[0];
                String description = currLine[2];
                switch (type) {
                    case "D": {
                        String[] deadline = currLine[3].split(" ");
                        String date = logic.formatDate(deadline[0].split("/"));
                        String remarks = " | " + currLine[3];
                        if (deadline.length == 1) {
                            newTask = new Deadline(description, date, remarks);
                        } else {
                            String time = logic.formatTime(deadline[1].split(""));
                            newTask = new Deadline(description, date, time, remarks);
                        }
                        break;
                    }
                    case "E": {
                        String[] start = currLine[3].split(" ");
                        String[] end = currLine[4].split(" ");
                        String startDate = logic.formatDate(start[0].split("/"));
                        String endDate = logic.formatDate(end[0].split("/"));
                        String remarks = " | " + currLine[3] + " | " + currLine[4];
                        if (start.length > 1) {
                            String startTime = logic.formatTime(start[1].split(""));
                            String endTime = logic.formatTime(end[1].split(""));
                            newTask = new Event(description, startDate, endDate, startTime, endTime, remarks);
                        } else {
                            newTask = new Event(description, startDate, endDate, remarks);
                        }
                        break;
                    }
                    default: {
                        newTask = new ToDo(description);
                    }
                }
                if (currLine[1].equals("X")) {
                    newTask.mark();
                }
                list.addFind(description, newTask);
                list.add(newTask);
            }
        }
        return list;
    }

    public static void saveData(TaskList list) throws IOException {
        FileWriter dukeWriter = new FileWriter(path, false);
        for (Task i : list.getList()) {
            if (i instanceof ToDo) {
                dukeWriter.write("T | " + i.getStatusIcon() + " | " + i.getDescription() + "\n");
            } else if (i instanceof Deadline) {
                dukeWriter.write("D | " + i.getStatusIcon() + " | " + i.getDescription() + " " + i.getRemarks() + "\n");
            } else {
                dukeWriter.write("E | " + i.getStatusIcon() + " | " + i.getDescription() + " " + i.getRemarks() + "\n");
            }
        }
        dukeWriter.close();
    }
}