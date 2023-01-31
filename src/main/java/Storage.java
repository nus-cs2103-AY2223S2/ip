import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    static void loadData(String textDir, File file, TaskList tasks) throws IOException {
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

        static void saveData(PrintWriter pw, String textDir, TaskList tasks) throws IOException {
            pw.print("");
            pw.close();
            PrintWriter clearer = new PrintWriter(textDir);
            clearer.close();
            PrintWriter reWriter = new PrintWriter(new FileWriter(textDir, true));
            for (int i = 0; i < tasks.getSize(); i++) {
                reWriter.write(tasks.getTask(i).printRecord());
            }
            reWriter.close();
        }
}
