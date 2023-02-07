package jane;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.Exception;




public class Jane {
    public static void main(String[] args) throws JaneException {
        jane.Ui.start();
        Storage.createDir();
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList(Storage.loadList());
        while (in.hasNext()) {
            String output = in.nextLine();
            tasks.useCommand(output);
            if (output.equals("bye")) {
                break;
            }
        }
    }
}