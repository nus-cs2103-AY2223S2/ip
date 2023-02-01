package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> loadContents() throws FileNotFoundException {
//        File folder = new File("data");
//        if (!folder.exists()) {
//            throw new FileNotFoundException("Folder does not exist!");
//        }
//        File f = new File("data/duke.txt");
        File f = new File(this.filePath);
        if (!f.exists()) {
            throw new FileNotFoundException("File does not exist!");
        }
        Scanner sc = new Scanner(f);
        ArrayList<Task> storeTasks = new ArrayList<Task>();
        int numElem = 0;
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            String[] arrOfDetails = currentLine.split("\\|");
            String type = arrOfDetails[0];
            char marker = arrOfDetails[2].charAt(0);
            boolean isMarked = (marker=='X') ? true : false;
            String desc = arrOfDetails[1];
            switch (type) {
                case "T":// T|desc|X
                    storeTasks.add(new Todo(desc));
                    break;
                case "D": //D|desc|X|byWhen
                    LocalDate byWhen = LocalDate.parse(arrOfDetails[3]);
                    storeTasks.add(new Deadline(desc,byWhen));
                    break;
                case "E": //D|desc|X|from|to
                    LocalDate from = LocalDate.parse(arrOfDetails[3]);
                    LocalDate to = LocalDate.parse(arrOfDetails[4]);
                    storeTasks.add(new Event(desc,from,to));
                    break;
            }
            if (isMarked) {
                storeTasks.get(numElem).markAsDone();
            }
            numElem++;
        }
        return storeTasks;
    }

    public void saveTasks(ArrayList<Task> storeTasks) {
        try {
//            FileWriter fw = new FileWriter("data/duke.txt");
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
//            fw = new FileWriter("data/duke.txt", true);
            fw = new FileWriter(filePath, true);
            for (Task t: storeTasks) {
                String type = t.getType();
                String content = "";
                switch (type) {
                    case "T": // T|desc|X
                        content = String.format("%s|%s|%s",t.getType(),t.getDesc(),t.getStatusIcon());
                        break;
                    case "D": //D|desc|X|from
                        Deadline deadlineTask = (Deadline) t;
                        content = String.format("%s|%s|%s|%s",t.getType(),t.getDesc(),t.getStatusIcon(),deadlineTask.getByWhen());
                        break;
                    case "E": //D|desc|X|from|to
                        Event eventTask = (Event) t;
                        content = String.format("%s|%s|%s|%s|%s",t.getType(),t.getDesc(),t.getStatusIcon(),eventTask.getFrom(),eventTask.getTo());
                        break;
                }
                fw.write(content + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error with saving TODO duke.task");
        }
    }
}
