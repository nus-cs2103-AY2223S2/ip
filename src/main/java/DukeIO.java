import exceptions.DukeException;
import Task.TaskMaster;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
public class DukeIO extends PrintWriter {

    // To look into placing into Document/Appdata like locations instead of this directory
    private final static Path LOCAL_SETTINGS_DIR = Paths.get(System.getProperty("user.dir"), "data");
    private final static Path LOCAL_SAVE = Paths.get(LOCAL_SETTINGS_DIR.toString(), "data.csv");
    private BufferedReader SAVE_FILE;
    private BufferedReader bf;

    public DukeIO(){
        super(new BufferedOutputStream(System.out));
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readln() {
        String userInput = "";
        try{
            userInput = bf.readLine().trim();
        } catch (IOException e){
            System.err.printf("DUKEIO : %s%n", e.getMessage());
        }
        return userInput;
    }

    public int extractIndexParams(String userIn) throws DukeException {
        String[] userInSplit = userIn.split(" ",3);
        if (userInSplit.length < 2) {
            throw new DukeException.Missing.Parameter(userInSplit[0]);
        }
        try {
            int ind = Integer.parseInt(userInSplit[1]);
            return ind - 1; // Count starting from 0
        } catch (NumberFormatException e) {
            throw new DukeException.Invalid.Input(String.format("%s is not an integer", userInSplit[1]));
        }
    }
    public String[] extractTaskParams(String userIn, Duke.SWITCHTYPERELATED desire) throws DukeException {

        switch (desire) {
            case TODO: {
                // Check for descriptor
                String[] LHS = userIn.split(" ", 2);
                if (LHS.length < 2) {
                    throw new DukeException.Missing.Description(LHS[0]);
                }
                // Return descriptor
                return new String[]{ LHS[1].trim() };
            }

            case EVENT: {
                // Removal of 'event'
                String[] userInSplit = userIn.split(" ", 2);
                if (userInSplit.length < 2) {
                    throw new DukeException.Missing.Parameter(userInSplit[0]);
                }

                // Check for /from keyword
                String[] fromSplit = userInSplit[1].split("/from",3);
                if (fromSplit.length < 2) {
                    throw new DukeException.Missing.Parameter(userInSplit[0]);
                } else if (fromSplit.length > 2) {
                    throw new DukeException.Invalid.Input("Multiple `/from` detected, only one is allowed, please try again.");
                }

                // Check for descriptor
                if (fromSplit[0].isEmpty()) {
                    throw new DukeException.Missing.Description(userInSplit[0]);
                }

                // Check from /to keyword
                String[] toSplit = fromSplit[1].split("/to",3);
                if (toSplit.length < 2) {
                    throw new DukeException.Missing.Parameter(userInSplit[0]);
                } else if (toSplit.length > 2) {
                    throw new DukeException.Invalid.Input("Multiple `/to` detected, only one is allowed, please try again.");
                }

                return new String[]{ fromSplit[0].trim() , toSplit[0].trim(), toSplit[1].trim() };
            }

            case DEADLINE: {
                    // Check for /by keyword
                    String[] userInSplit = userIn.split("/by",3);
                    if (userInSplit.length < 2) {
                        throw new DukeException.Missing.Parameter(userInSplit[0]);
                    } else if (userInSplit.length > 2) {
                        throw new DukeException.Invalid.Input("Multiple `/by` detected, only one is allowed, please try again.");
                    }

                    // Check for descriptor
                    String[] LHS = userInSplit[0].split(" ", 2);
                    if (LHS.length < 2) {
                        throw new DukeException.Missing.Description(userInSplit[0]);
                    }
                    return new String[]{ LHS[1].trim() , userInSplit[1].trim()};
                }

            default:
                throw new DukeException.Invalid.Input("DIO - extractTaskParams - used unexpectedly");
        }

    }

    public void lb(){
        this.println("____________________________________________________________");
    }

    private static BufferedReader readFileBR(Path filePath) {
        BufferedReader READ_FILE = null;
        try {
            READ_FILE = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        } catch (java.nio.file.NoSuchFileException e) {
            System.err.println("File you were trying to read does not exist!");
        } catch (IOException e) {
            System.err.println(e);
        }
        return READ_FILE;
    }
    private static BufferedWriter writeFileBW(Path filePath) {
        BufferedWriter WRITE_FILE = null;
        if (Files.notExists(filePath)) {
            createFile(filePath);
        }

        try {
            WRITE_FILE = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e);
        }

        return WRITE_FILE;
    }
    public static void createFile(Path filePath) {
        if (Files.notExists(filePath.getParent())) {
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                System.err.println(e.getCause());
            }
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println(e.getCause());
        }
    }

    protected static void readSave(TaskMaster tm) {
        BufferedReader FILE = readFileBR(LOCAL_SAVE);
        try {
            String curLine;
            while ((curLine = FILE.readLine()) != null ) {
                String task[] = curLine.split(",");
                switch (task[0]) {
                    case "T":
                        tm.addToDo(task[1], Boolean.valueOf(task[2]));
                        break;
                    case"D":
                        tm.addDeadLine(task[1], Boolean.valueOf(task[2]), task[3]);
                        break;
                    case "E":
                        tm.addEvent(task[1], Boolean.valueOf(task[2]), task[3], task[4]);
                        break;
                    default:
                        // new throw wrong input type
                        break;
                }

             System.out.println(curLine);
            }
        } catch (IOException e) {
            System.err.printf("SYSERR %s\n", e.getMessage());
        } catch (DukeException.Invalid.Input e) {
            throw new RuntimeException(e);
        }
    }

    protected static void writeSave(TaskMaster tm) {
        System.out.println(LOCAL_SAVE);
        BufferedWriter FILE = writeFileBW(LOCAL_SAVE);
        try {
            System.out.println(tm.export());
            FILE.write(tm.export());
            FILE.close();
        } catch (IOException e) {
            System.err.printf("SYSERR %s\n", e.getMessage());
        }
    }

}
