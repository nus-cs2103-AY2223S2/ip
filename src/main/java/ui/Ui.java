package ui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Ui {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    final String logo = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * Prints Duke's greeting to the user
     */
    public void greetUser() {
        printResponse("Hello from\n" + logo + "\n Please wait while Duke loads your previous list...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignore) { }
    }

    public String getUserInput() throws IOException {
        return br.readLine();
    }
    /**
     * Standardises Duke's response printing format by enclosing the response within 2 lines
     * 
     * @param response Duke's response to User
     */
    public void printResponse(String response) {
        System.out.println("\n----------------------------------\n");
        System.out.println(response);
        System.out.println("\n----------------------------------\n");
    }

    public void printException(Exception e) {
        printResponse(e.getMessage());
    }

    public void endSession() throws IOException {
        printResponse("Bye! Hope you enjoyed using Duke! \n Your list awaits your return!");
        br.close();
    }
}
