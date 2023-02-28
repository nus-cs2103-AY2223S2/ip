package red.storage;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

import red.command.AddCommand;
import red.command.Command;

import red.task.TaskList;
import red.task.DeadlineTask;
import red.task.EventTask;
import red.task.ToDoTask;

import red.ui.UI;


/**
 * This class stores the information contained in the user's TaskList.
 */
public class Storage {
    private TaskList tasks;
    private final UI ui;
    protected static File f = new File("data\\tasklist.txt");

    /**
     * The constructor for Storage that takes in the TaskList and the UI.
     *
     * @param list The current list of tasks.
     * @param ui The current ui
     */
    public Storage(TaskList list, UI ui) {
        this.tasks = list;
        this.ui = ui;
    }


    /**
     * Saves the current TaskList to the file.
     *
     * @throws IOException Throws Exception when there is an issue with the file.
     */
    public static void writeToStorage(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data\\tasklist.txt");
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }


    /**
     * Saves the current TaskList to the file.
     */
    public void saveToStorage() {
        String TaskListCopy = tasks.toString();
        try {
            this.writeToStorage(TaskListCopy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDate getDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate value = LocalDate.parse(date,dateTimeFormatter);
        return value;
    }

    /**
     * Loads the file that will store the information of the TaskList and creates a file if it does not exist
     * while copying any information contained from a previous session into the current session.
     *
     */
    public void createStorage() {
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Scanner s = null;
            try {
                s = new Scanner(f);
                s.useDelimiter(System.getProperty("line.separator"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (s.hasNext()) {
                String str = s.nextLine();
                System.out.println(str.charAt(4));
                char todo = 'T';
                char event = 'E';
                char deadline = 'D';
                if(Character.compare(str.charAt(4),todo) == 0) {
                    String[] arrOfStr= str.split("] ", 2);
                    ToDoTask currentTask = new ToDoTask(arrOfStr[1].trim());
                    Command currentCommand = new AddCommand(currentTask);
                    currentCommand.execute(this.tasks,this.ui,this);
                    
                } else if(Character.compare(str.charAt(4),event) == 0) {
                    String[] arrOfStr= str.split("] ", 2);
                    String[] arrOfStrStr= arrOfStr[1].split("\\(From: ", 2);
                    String[] arrOfStrStrStr= arrOfStrStr[1].split("To: ", 2);
                    String[] arrOfStrStrStrStr= arrOfStrStrStr[1].split("\\)", 2);

                    LocalDate startDate = getDate(arrOfStrStrStr[0].trim());
                    LocalDate endDate = getDate(arrOfStrStrStrStr[0].trim());
                    EventTask currentTask = new EventTask(arrOfStrStr[0].trim(),startDate,endDate);

                    Command currentCommand = new AddCommand(currentTask);
                    currentCommand.execute(this.tasks,this.ui,this);

                } else if(Character.compare(str.charAt(4),deadline) == 0) {
                    String[] arrOfStr= str.split("] ", 2);
                    String[] arrOfStrStr= arrOfStr[1].split("\\(Before: ", 2);
                    String[] arrOfStrStrStr= arrOfStrStr[1].split("\\)", 2);

                    LocalDate date = getDate(arrOfStrStrStr[0].trim());
                    DeadlineTask currentTask = new DeadlineTask(arrOfStrStr[0].trim(), date);


                    Command currentCommand = new AddCommand(currentTask);
                    currentCommand.execute(this.tasks,this.ui,this);

                }
            }

        }

    }

}
