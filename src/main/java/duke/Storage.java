package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Storage class encapsulates
 * loading and saving operations.
 */
public class Storage {
	public static final String SAVE_LOCATION = "duke.txt";

    /**
     * Loads the save file from a fixed location
     *
     * @return Tasklist loaded from the save file
     */
    public static Tasklist load() throws FileNotFoundException {
        Tasklist listOfThings = new Tasklist();
        File saveFile = new File(SAVE_LOCATION);
        Scanner fileReader = new Scanner(saveFile);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            char taskType = data.charAt(4);
            boolean taskDone = (data.charAt(7) == 'X');
            String taskName = "";
            


    /**
     * Saves the save file to a specified location.
     *
     * @param tasklist Tasklist to be saved
     */
    public static void save(Tasklist tasklist) throws IOException {
        FileWriter fw = new FileWriter(SAVE_LOCATION);
        fw.write("");
        fw.close();
        fw = new FileWriter(SAVE_LOCATION, true); // create a FileWriter in append mode
        for (int i = 0; i < tasklist.size(); i++) {
           fw.write(i + 1 + ". " + tasklist.get(i) + System.lineSeparator());
        }
        fw.close();
    }
}
