import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        ArrayList<String> lst = new ArrayList<>();
        lst.add(word);

        while (!word.equals("bye")) {
            System.out.println("Duke: " + word);
            word = br.readLine();
        }
        System.out.println("Duke: Goodbye");
    }
}
