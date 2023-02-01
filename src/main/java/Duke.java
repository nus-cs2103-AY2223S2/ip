import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner usrInput = new Scanner(System.in);
        String input = "";
        File data = new File("data.txt");
        try {
            boolean createdFile = data.createNewFile();
            if (createdFile){
                System.out.println("Data file successfully created!");
            } else {
                System.out.println("Data file already exists!");
            }
        } catch (IOException e) {
            System.err.println("Failed to create file :(");
            e.printStackTrace();
        }

        TaskList taskList = new TaskList("data.txt");
        CommandHandler commandHandler = new CommandHandler(taskList);

        while (true) {
            input = usrInput.nextLine();
            try {

                commandHandler.execute(input);
            } catch (IllegalInputException | IllegalCommandException e) {

                System.out.println(e.toString());
            }
        }

    }
}
