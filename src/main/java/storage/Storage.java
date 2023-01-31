package storage;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Iterator;

import task.Task;
import task.Event;
import task.Deadline;
import task.ToDo;

public class Storage {
    protected final static String PATH = "duke.txt";
    protected static File data = new File(PATH);


    public static void loadData(TaskList list) throws IOException {
        data.createNewFile();
        List<String> tmp = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
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
                    String[] date = deadline[0].split("/");
                    for (int i = 0; i < date.length; i++) {
                        if (date[i].length() < 2) {
                            date[i] = "0" + date[i];
                        }
                    }
                    String newDate = date[2] + "-" + date[1] + "-" + date[0];
                    String remarks = " | " + currLine[3];
                    if (deadline.length == 1) {
                        newTask = new Deadline(description, newDate, remarks);
                    } else {
                        String[] time = deadline[1].split("");
                        String newTime = time[0] + time[1] + ":" + time[2] + time[3];
                        newTask = new Deadline(description, newDate, newTime, remarks);
                    }
                    break;
                }
                case "E": {
                    String[] start = currLine[3].split(" ");
                    String[] end = currLine[4].split(" ");
                    String[] startDate = start[0].split("/");
                    String[] endDate = end[0].split("/");
                    for (int i = 0; i < startDate.length; i++) {
                        if (startDate[i].length() < 2) {
                            startDate[i] = "0" + startDate[i];
                        }
                        if (endDate[i].length() < 2) {
                            endDate[i] = "0" + endDate[i];
                        }
                    }
                    String newStartDate = startDate[2] + "-" + startDate[1] + "-" + startDate[0];
                    String newEndDate = endDate[2] + "-" + endDate[1] + "-" + endDate[0];
                    String remarks = " | " + currLine[3] + " | " + currLine[4];
                    if (start.length > 1) {
                        String[] startTime = start[1].split("");
                        String newStartTime = startTime[0] + startTime[1] + ":" + startTime[2] + startTime[3];
                        String[] endTime = end[1].split("");
                        String newEndTime = endTime[0] + endTime[1] + ":" + endTime[2] + endTime[3];
                        newTask = new Event(description, newStartDate, newEndDate, newStartTime, newEndTime, remarks);
                    } else {
                        newTask = new Event(description, newStartDate, newEndDate, remarks);
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

    public static void saveData(TaskList list) throws IOException {
        FileWriter dukeWriter = new FileWriter(PATH, false);
        for (Task i : list.getList()) {
            if (i instanceof ToDo) {
                dukeWriter.write("T | " + i.getStatusIcon() + " | " + i.getDescription() + "\n");
            } else if (i instanceof Deadline) {
                String[] nameAndDeadline = i.toString().split("\\(by: ");
                dukeWriter.write("D | " + i.getStatusIcon() + " | " + i.getDescription() + i.getRemarks() + "\n");
            } else {
                String[] nameAndStart = i.toString().split(" \\(from: ");
                String[] startAndEnd = nameAndStart[1].split(" to: ");
                dukeWriter.write("E | " + i.getStatusIcon() + " | " + i.getDescription() + i.getRemarks() + "\n");
            }
        }
        dukeWriter.close();
    }
}