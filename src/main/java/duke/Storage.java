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
     * Update the text file whenever a task is added, removed or modified.
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

                String note[] = task.getNote().split("[\\r\\n]+");
                String concatedNote = note[0];

                for (int j = 1; j < note.length; j++) {
                    concatedNote = concatedNote + "/&" + note[j];
                }

                if (type.equals("D")){
                    String time = task.getTime();

                    pw.println(type + "-" + isMark + "-" + name + "-" + time + "-" + concatedNote);
                } else if (type.equals("E")) {
                    String time = task.getTime();
                    String startEnd [] = time.split("-", 2);

                    pw.println(type + "-" + isMark + "-" + name + "-" + startEnd[0] + "-" + startEnd[1] + "-" + concatedNote);
                } else {
                    pw.println(type + "-" + isMark + "-" + name + "-" + concatedNote);
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
                    String nameNote [] = lines[2].split("-");
                    task = new Task(nameNote[0], lines[0]);

                    if(checkToAddNote(nameNote[1])) {
                        task.addNote(setNoteFormat(nameNote[1]));
                    }

                } else if (lines[0].equals("D")) {
                    String nameTimeNote[] = lines[2].split("-", 3);

                    SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date date = converterDate.parse(nameTimeNote[1]);
                    task = new Deadline(nameTimeNote[0], lines[0], date, nameTimeNote[1]);

                    if(checkToAddNote(nameTimeNote[2])) {
                        task.addNote(setNoteFormat(nameTimeNote[2]));
                    }

                } else {
                    String nameStartEndNote[] = lines[2].split("-", 4);
                    SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date date1 = converterDate.parse(nameStartEndNote[1]);
                    Date date2 = converterDate.parse(nameStartEndNote[2]);
                    task = new Event(nameStartEndNote[0], lines[0], date1, date2, nameStartEndNote[1],
                            nameStartEndNote[2]);

                    if (checkToAddNote(nameStartEndNote[3])) {
                        task.addNote(setNoteFormat(nameStartEndNote[3]));
                    }

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
            ui.printText("Unable to load file. Please check your that the item's date where it should be in the form of" +
                    " dd/MM/yyyy HH:mm");
        }

        return entireList;
    }

    /**
     * Check if the note is blank
     * @param note is to be checked
     * @return true if note is blank
     */
    public boolean checkToAddNote(String note) {
        if (note.equals("blank")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Format the note store in the file by seperating /& and replacing them with new line
     * @param notes to be formatted
     * @return formatted notes
     */
    public String setNoteFormat(String notes) {
        String concatedNote[] = notes.split("/&");
        String note = concatedNote[0];
        for(int i = 1; i < concatedNote.length; i ++){
            note = note + "\n" + concatedNote[i];
        }
        return note;
    }

}

