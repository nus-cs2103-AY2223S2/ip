package jarvis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {

    private String dataPath;

    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    public void saveTasks(ToDoList todolist) throws IOException {
        FileWriter fw = new FileWriter(dataPath);

        for (int i = 0; i < todolist.getCount(); i++) {
            fw.write(todolist.getTask(i) + System.lineSeparator());
        }
        fw.close();
    }

    public void loadTasks(ToDoList todolist) throws FileNotFoundException {
        System.out.println("\tTasks from the previous session:\n");
        System.out.println("\t--------------------------");
        File f = new File(dataPath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String curr = s.nextLine();
            String taskType = curr.substring(1, 2);
            System.out.println("\t" + curr);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

            switch (taskType) {
            case "T":
                todolist.add(curr.substring(7));
                break;
            case "D":
                int firstColonD = curr.indexOf(":");
                int firstBracketD = curr.indexOf("(");
                String taskD = curr.substring(7, firstBracketD - 1);
                String time = curr.substring(firstColonD + 2, curr.length() - 1);

                LocalDate startTimeParsed = LocalDate.parse(time, formatter);
                todolist.add(taskD, startTimeParsed);
                break;
            case "E":
                int firstColonE = curr.indexOf(":");
                int secondColonE = curr.substring(firstColonE + 1).indexOf(":") + firstColonE + 1;
                int firstBracketE = curr.indexOf("(");
                String startTime = curr.substring(firstColonE + 2, secondColonE - 6);
                String endTime = curr.substring(secondColonE + 2, curr.length() - 1);
                String taskE = curr.substring(7, firstBracketE - 1);

                LocalDate startTimeParsedE = LocalDate.parse(startTime, formatter);
                LocalDate endTimeParsed = LocalDate.parse(endTime, formatter);
                todolist.add(taskE, startTimeParsedE, endTimeParsed);
                break;
            }
        }
        System.out.println("\t--------------------------");
    }
}
