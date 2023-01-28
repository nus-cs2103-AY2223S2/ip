package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DukeTest {
    @Test
    public void allCommandTest() throws DukeException {
        String[] commands = {
                "todo shut Suigen up",
                "deadline kill Suigen /by 28/01/2023 2300",
                "event destroy evidence /from 28/01/2023 2301 /to 30/01/2023 0000",
                "list",
                "mark 1",
                "mark 2",
                "unmark 1",
                "list",
                "bye"
        };
        Storage store = new Storage(Paths.get(System.getProperty("user.dir"), "data", "Duke.txt"));
        TaskList taskList = new TaskList(store.load());

        for (String command : commands){
            Parser parse = new Parser(command);
            Command cmd = parse.parseArgs();
            cmd.execArgs(taskList);
        }
    }


    @Test
    public void fileEditTest() throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "Duke.txt");
        String string1 = "The seller is located in Bali right now!\n";
        String string2 = "Suigen says NOOOOOOOOOO!\n";
        String string3 = "Chris be like hah get good noob\n";
        List<String> inputLines = new ArrayList<>(3);

        Files.write(filePath, inputLines);

        List<String> testLines = Files.readAllLines(filePath);
        for (int i = 0; i < testLines.size(); i++){
            assertEquals(testLines.get(i), inputLines.get(i));
        }
    }
}
