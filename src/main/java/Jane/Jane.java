package jane;

import java.util.Scanner;

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
