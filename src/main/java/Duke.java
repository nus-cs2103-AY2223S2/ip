import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static final String filePath = "./data";
    protected static final String database = "duke.txt";
    protected static ArrayList<String> commandList = new ArrayList<>(Arrays.asList
            ("todo", "deadline", "event", "mark", "unmark", "list", "bye", "delete"));
    public static void main(String[] args) {
        Duke d = new Duke();
        DukeList list = new DukeList();
        d.readDatabase(list);
        d.greet();
        d.run(list);
    }

    public void run(DukeList records) {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            try {
                String input = userInput.nextLine();
                //Check for empty input (pressing enter)
                if (input == "") {
                    throw new EmptyInputException();
                }
                String[] inputArr = input.split(" ", 2);
                //Check for invalid commands (first word)
                boolean validCommand = commandList.contains(inputArr[0]);
                if (!validCommand) {
                    throw new InvalidCommandException();
                }
                handleInput(input, records);
            }catch (EmptyInputException | InvalidCommandException exception) {
                System.out.println("Input Error, please input a command within the list:" +
                        "todo, deadline, event, mark, unmark, list, delete, bye.");
            }
        }
    }

    private void readDatabase(DukeList dl) {
        File f = new File(String.valueOf(Paths.get(Duke.filePath, Duke.database)));
        System.out.println(f);
        try {
            Scanner fileReader = new Scanner(f);
            System.out.println("Database loaded.");
            int index = -1;
            while (fileReader.hasNextLine()) {
                String input = fileReader.nextLine();
                String[] split = input.split("\\|");
                String cmd = split[0];
                String status = split[1];
                String taskName = split[2];
                index++;
                switch (cmd) {
                    case "T":
                        dl.insertToDo(taskName, true);
                        if (status.equals("X")) {
                            dl.mark(index);
                        }
                        break;
                    case "D":
                        String deadline = "a";
                        dl.insertDeadline(taskName, deadline, true);
                        if (status.equals("X")) {
                            dl.mark(index);
                        }
                        break;
                    case "E":
                        String time = split[3];
                        dl.insertEvent(taskName, time, true);
                        if (status.equals("X")) {
                            dl.mark(index);
                        }
                        break;
                    default:
                        break;
                }
            }
            fileReader.close();
            dl.toString();
        } catch (FileNotFoundException e) {
            this.initialiseDatabase();
        }
    }


    private void initialiseDatabase() {
        File f = new File(String.valueOf(Paths.get(Duke.filePath, Duke.database)));
        File dir = new File(Duke.database);
        dir.mkdir();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating database.");
        }
    }


    /**
     *  Handles the logic flow direction from user input.
     *  1. When a '/' is present, there are 3 arguments to the input.
     *  The first loop section handles by splitting them into command, task name and deadline,
     *  and passes them down to handleThreeInputs(command, name, time, d);
     *  2. Else, it is a double argument input, split by first empty space " ",
     *  with split[1] being task name if command is todo, else split[1] is index for CRUD commands.
     *  3. Otherwise, handle single inputs with the function.
     * @param input User input from terminal.
     * @param d Initialised DukeList data structure
     *
     */
    public void handleInput(String input, DukeList d) {
        if (input.contains("/")) {
            String[] split = input.split("/");
            String[] secondSplit = split[0].split(" ", 2);

            String command = secondSplit[0];
            String name = secondSplit[1];
            String time = split[1];
            handleThreeInputs(command, name, time, d);
        } else {
            String[] split = input.split(" ", 2);
            String command = split[0];
            if ("todo".equalsIgnoreCase(command)) {
                d.insertToDo(split[1]);
            } else if (split.length > 1) {
                Integer index = Integer.parseInt(split[1]);
                handleTwoInputs(command, index, d);
            } else {
                handleSingleInput(command, d);
            }
        }
    }

    public void handleSingleInput(String command, DukeList d) {
        switch (command) {
        case "bye":
            exit();
            break;
        case "list":
            System.out.println(d.toString());
            break;
        default:
            d.insert(command);
        }
    }

    public void handleTwoInputs(String command, Integer index, DukeList d) {
        switch (command) {
        case "mark":
            d.mark(index);
            break;
        case "unmark":
            d.unMark(index);
            break;
        case "delete":
            d.deleteTask(index);
            break;
        }
    }

    public void handleThreeInputs(String command, String name, String time, DukeList d) {
        switch (command) {
        case "deadline":
            d.insertDeadline(name, time);
            break;
        case "event":
            d.insertEvent(name, time);
            break;
        case "todo":
            d.insertToDo(name);
            break;
        }
    }

    public static String format(String input) {
        return "____________________________________________________________\n" +
                input +
                "\n____________________________________________________________";
    }

    public void greet() {
        String greeting = format("if it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins."
                + "\n Ready to go on a self-exploration adventure?");
        System.out.println(greeting);
    }

    public void exit(){
        String exitMsg = format("Bye. Come back soon!");
        System.out.println(exitMsg);
        System.exit(1);
    }
}
