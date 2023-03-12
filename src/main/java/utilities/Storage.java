package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import command.Command;
import exceptions.SundayException;

/**
 * The Storage class is responsible for managing file I/O operations for the Sunday task manager.
 * It provides methods for creating, reading from, and writing to a data file that stores the list of tasks.
 *
 * @author Tan Yan-Hao Joshua
 */
public class Storage {

    /**
     * Retrieves the file path of the "sunday.txt" file in the "data" directory.
     * If the "data" directory does not exist, it will be created.
     *
     * @return The file path of the "sunday.txt" file
     * @throws IOException If the "data" directory failed to be created.
     */
    private static String getFilepath() throws IOException {
        Path dataDir = Paths.get(System.getProperty("user.dir"), "data");
        if (!Files.exists(dataDir)) {
            Files.createDirectory(dataDir);
        }
        Path saveFilepath = Paths.get(dataDir.toString(), "sunday.txt");
        return saveFilepath.toString();
    }

    /**
     * Creates a new data file at the specified file path.
     *
     * @return True if the file was created successfully, false otherwise.
     * @throws SundayException If there was an error creating the file.
     */
    public static boolean createDataFile() throws SundayException {
        try {
            String filepath = getFilepath();
            File dataFile = new File(filepath);
            return dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new SundayException("ERROR: Unable to initialise data file");
        }
    }

    /**
     * Reads data from a file at the specified file path and executes relevant
     * commands based on the data read.
     *
     * @throws SundayException If there was an error reading the file or executing the commands.
     */
    public static void readFromDataFile() throws SundayException {
        try {
            String filepath = getFilepath();
            File dataFile = new File(filepath);
            Scanner reader = new Scanner(dataFile);
            Ui.setDummyStream();

            int itemIndex = 1;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] strArr = data.split("]");

                switch (strArr[0].charAt(1)) {
                case 'T':
                    Command.TODO.execute(strArr[2]);
                    break;
                case 'D':
                    Command.DEADLINE.execute(strArr[2].substring(0, strArr[2].length() - 1));
                    break;
                case 'E':
                    Command.EVENT.execute(strArr[2].substring(0, strArr[2].length() - 1));
                    break;
                default:
                    throw new SundayException("ERROR 1: unable to read file");
                }

                switch (strArr[1].length()) {
                case 1:
                    Command.UNMARK.execute(" " + itemIndex);
                    break;
                case 2:
                    Command.MARK.execute(" " + itemIndex);
                    break;
                default:
                    throw new SundayException("ERROR 2: unable to read file");
                }
                itemIndex++;
            }
            Ui.setDefaultStream();
            reader.close();
        } catch (IOException e) {
            Ui.setDefaultStream();
            System.out.println(e.getMessage());
            throw new SundayException("ERROR: Unable to read from data file");
        }
    }

    /**
     * Writes a line of data to the specified file path.
     *
     * @param line The line of data to write to the file.
     * @return True if write was successful, false otherwise.
     * @throws SundayException If there was an error writing to the file.
     */
    public static boolean writeToDataFile(String line) throws SundayException {
        try {
            String filepath = getFilepath();
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(line);
            myWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new SundayException("ERROR: Unable to write to data file");
        }
    }
}
