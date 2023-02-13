package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;


public class Storage {
    protected String filePath;
    public Storage(String pathName) {
        filePath = pathName;
    }

    /**
     * Update the text file with the given list
     * @param entireList A list in which all tasks are stored
     */
    public void updateFile(ArrayList<Task> entireList) throws FileNotFoundException {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));

            for (int i = 0; i < entireList.size(); i++) {
                Task task = entireList.get(i);

                boolean isMark = task.getComplete();
                String type = task.getTypes();
                String name = task.getItem();

                if (type.equals("D")){
                    String time = task.getTime();

                    pw.println(type + "-" + isMark + "-" + name + "-" + time);
                } else if (type.equals("E")) {
                    String time = task.getTime();
                    String startEnd [] = time.split("-", 2);

                    pw.println(type + "-" + isMark + "-" + name + "-" + startEnd[0] + "-" + startEnd[1]);
                } else {
                    pw.println(type + "-" + isMark + "-" + name);
                }

            }
            pw.close();

        }catch(IOException i) {
            System.out.println("Your file cannot be saved! I did you warned from the start :>");
        }
    }

    /**
     * Loads the text of the file into an array.
     * @param entireList A list in which all tasks are stored
     */

    public ArrayList<Task> loadFile(ArrayList<Task> entireList, Ui ui) throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String oneline;

            while ( (oneline = br.readLine() )!= null) {
                String lines [] = oneline.split("-", 3);
                Task task;

                if(lines[0].equals("T")) {
                    task = new Task(lines[2], lines[0]);
                } else if (lines[0].equals("D")) {
                    task = convertStringToDeadline(lines);
                } else {
                    task = convertStringToEvent(lines);
                }

                if(lines[1].equals("true")) {
                    task.mark();
                }

                entireList.add(task);
            }

            assert entireList.size() >= 0: "Something went wrong when loading the task in Storage";

        } catch (IOException i) {
            ui.printText("File not found! Proceeding without a saved file.\nPlease note that you will not be able" +
                    " to save your progress either but you can still use me as you wish :>\n");

        } catch (ParseException e) {
            ui.printText("Unable to load file. Please check your that the item's date where it should be in the " +
                    "form of dd/MM/yyyy HH:mm");
        }

        return entireList;
    }


    /**
     * Converts string into deadline task
     * @param lines to be converted into deadline task
     * @return deadline task
     * @throws ParseException
     */
    public Deadline convertStringToDeadline(String lines[]) throws ParseException {
        String nameTime[] = lines[2].split("-", 2);

        SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = converterDate.parse(nameTime[1]);

        return new Deadline(nameTime[0], lines[0], date, nameTime[1]);
    }


    /**
     * Converts string into event task
     * @param lines to be converted into event task
     * @return event task
     * @throws ParseException
     */
    public Event convertStringToEvent(String lines[]) throws ParseException {
        String nameStartEnd[] = lines[2].split("-", 3);
        SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = converterDate.parse(nameStartEnd[1]);
        Date date2 = converterDate.parse(nameStartEnd[2]);

        return new Event(nameStartEnd[0], lines[0], date1, date2, nameStartEnd[1], nameStartEnd[2]);
    }
}
