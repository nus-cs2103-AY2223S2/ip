import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static final String SEPARATOR = " \\| ";
    private Path path;
    private File file;
    private List<String> loadErrors = null;

    Storage(Path path) throws IOException {
        this.path = path;
        this.file = new File(path.toString());

        // creates storage directory or data if they don't exist
        this.file.getParentFile().mkdirs();
        if (!Files.exists(path)) {
            this.file.createNewFile();
        }
    }

    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line = br.readLine();
            int taskCounter = 0;
            while (line != null) {
                list.add(getTaskFromStorage(line));
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    private Task getTaskFromStorage (String s) throws InvalidStorageException {
        /*
        Coverts a single line in the storage into a Task
        It is assumed that a line in storage follows the format specified in docs.

        <task symbol> | <isDone> | <desc> | <addtl-arg1>:<values> | <addtl-arg2>:<value> ...

        For example, a project meeting Event from 1pm to 3pm marked done:
        E | 1 | project meeting | from:1pm | to:3pm
         */

        List<String> args = Arrays.asList(s.split(SEPARATOR));
        // get compulsory arguments for task
        try {
            String symbol = args.get(0);
            String isDone = args.get(1);
            String desc = args.get(2);
            return new ToDo(desc, isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidStorageException();
        }

    }

    private String writeTaskToStorage (Task t) {
        return null;
    }
/*
    public void writeData() {
        // create data String
        StringBuilder data = new StringBuilder("");
        for (Task t: this.list) {
            data.append(t.getDataFormat() + "\n");
        }
        String output = data.toString();

        // check if ../data folder exists - if not, create it
        Path dirPath = Paths.get("..", "data");
        File dir = new File(dirPath.toString());
        if (!Files.exists(dirPath)) {
            dir.mkdir();
        }

        // enumerate the number of txt files
        File[] dirList = dir.listFiles();
        int fileCounter = 0;
        if (dirList != null) {
            for (File f : dirList) {
                if (f.getPath().endsWith(".txt")) {
                    fileCounter++;
                }
            }
        }

        File dataFile = new File(dir.toString(), "log-" + fileCounter);
        PrintWriter p = null;
        try {
            p = new PrintWriter(dataFile);
        } catch(Exception e) {
            e.getStackTrace();
        }

        try {
            out = new PrintWriter("../data/result.txt");
        } catch (FileNotFoundException e) {
            // create file in data folder if it does not exist
            File file = new File("../result")
        } finally {
            if (Objects.isNull(out)) {
                out.close();
            }
        }
    }

 */
}
