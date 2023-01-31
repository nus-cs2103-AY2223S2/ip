import java.io.*;

public class Storage {
    static void loadData() throws IOException {
        String textDir = System.getProperty("user.dir")+"/duke.txt";
        File file = new File(textDir);
        TaskList tasks = new TaskList();
        if (!file.exists()) {
            System.out.println(Ui.formatStr("Oh dear! There is no save file. Let me create one for you."));
            System.out.println("........CREATING.......");
            file.createNewFile();
        }
        PrintWriter pw = new PrintWriter(new FileWriter(textDir, true));
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while (br.ready()) {
            line = br.readLine();
            tasks.addLine(line);
        }
    }
}
