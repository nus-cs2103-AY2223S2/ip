import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Duke {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        ArrayList<String> lst = new ArrayList<>();

        while (!word.equals("bye")) {
            if (word.equals("list")) {
                int curr = 1;
                Iterator<String> iter = lst.iterator();
                while (iter.hasNext()) {
                    System.out.println(curr + ". " + iter.next());
                    curr++;
                }
                word = br.readLine();
            } else {
                System.out.println("Added: " + word);
                lst.add(word);
                word = br.readLine();
            }
        }
        System.out.println("Duke: Goodbye");
    }
}
