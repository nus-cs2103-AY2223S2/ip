import java.util.*;
import java.io.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = "";
        ArrayList <tasks> list = new ArrayList <tasks>();

        while( !(text = br.readLine()).equals("bye")) {
            if ( text.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    char c = ' ';
                    if(list.get(i).isMark()) {
                        c = 'X';
                    }
                    System.out.println(j + ".[" + c + "]" + list.get(i).getValue());
                }
            } else if ( text.contains("mark")) {
                StringTokenizer tk = new StringTokenizer(text);
                tk.nextToken();
                int index = Integer.parseInt(tk.nextToken());
                list.get(index - 1).setMark(true);
                System.out.println("Nice! I've marked this task as done: [X]" + list.get(index - 1).getValue());
            }
            else {
                tasks t = new tasks(false,text);
                list.add(t);
                System.out.println("Added : " + text);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
