import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TunaBot {
    private static final Scanner s = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static boolean toExit = false;
    public static void main(String[] args) {
        Path savePath = Paths.get("data", "save.txt");
        Storage storage = new Storage(savePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("BLUB! Problem with save file!");
        }
        String LINE = "------------------------------";
        System.out.println(LINE);
        System.out.println("    Hello! I'm TunaBot\n" +
                "    What can I do for you?");
        System.out.println(LINE);
        while (!toExit) {
            String input = s.nextLine();
            System.out.println(LINE);
            try {
                toExit = Parser.parse(input, tasks);
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("BLUB! Please use the format dd/mm/yy-hhmm with time in 24H format! eg. 29/12/23-1854");
            }
            System.out.println(LINE);
        }
        storage.save(tasks);
    }
}
