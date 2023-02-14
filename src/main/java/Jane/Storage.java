package jane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

    public class Storage {
    private static final String currentD = Paths.get("").toAbsolutePath().toString();
    private static final Path dirPath = Paths.get(currentD, "data");
    private static final Path filePath = Paths.get(currentD, "data", "JaneList.txt");

    public static void createDir() {
        try {
            if (Files.notExists(dirPath)) {
                Files.createDirectory(dirPath);
            }
        } catch (IOException err) {
            System.out.println("Unable to create directory");
            err.printStackTrace();
        }

    }
        /**
         * Creates JaneList.txt if it doesn't exist. Reads data from JaneList.txt otherwise
         * @return ArrayList of all Tasks in duke.txt
         * @throws IOException if JaneList.txt cannot be created
         */
    public static ArrayList<jane.task.Task> loadList() {
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException err) {
                System.out.println("unable to create list");
                err.printStackTrace();
            }
        }
        List<String> lines = null;
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException err) {
            System.out.println("cannot read the list");
            assert lines != null;
        }
        ArrayList<jane.task.Task> tasks = new ArrayList<jane.task.Task>();
        for (String s : lines) {
            //to separate each portion of the task eg D | taskname | deadline to easily see which type of task and deadline
            String[] line = s.split("\\|");
            int i = Integer.parseInt(line[1]);
            boolean b = (i == 1);
            if (line[0].equals("T")) {
                jane.task.Todo T = jane.Parser.parserT(s, tasks.size());
                T.changeState(b);
                tasks.add(T);
            } else if (line[0].equals("D")) {
                jane.task.Deadline D = jane.Parser.parserD(s, tasks.size() );
                D.changeState(b);
                tasks.add(D);
            } else if (line[0].equals("E")) {
                jane.task.Event E = jane.Parser.parserE(s, tasks.size() );
                E.changeState(b);
                tasks.add(E);
            }

        }
        return tasks;
    }
    //Writes into JaneList.txt
    public static void updateList(ArrayList<jane.task.Task> tasks) {
        ArrayList<String> list = new ArrayList<String>();
        for (jane.task.Task t : tasks) {
            list.add(t.save());
        }
        try {
            Files.write(filePath, list);
        } catch (IOException err) {
            System.out.println("cannot save list");
            err.printStackTrace();
        }
    }
}

