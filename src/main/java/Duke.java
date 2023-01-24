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
        ArrayList <Task> list = new ArrayList <Task>();

        while( !(text = br.readLine()).equals("bye")) {
            StringTokenizer tk = new StringTokenizer(text);
            String action = tk.nextToken();

            if ( action.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + list.get(i));
                }
            } else if ( action.equals("mark")) {
                int index = Integer.parseInt(tk.nextToken());
                list.get(index - 1).setMark(true);
                System.out.println("Nice! I've marked this task as done: [X]" + list.get(index - 1).getValue());
            }
            else {
                if ( action.equals("todo")) {
                    String[] arr = text.split(" ", 2);
                    Todo t = new Todo( arr[1] );
                    list.add(t);
                } else if( action.equals("deadline")) {
                    String value = ""; String date = "";
                    while (tk.hasMoreTokens()) {
                        String nextString = tk.nextToken();
                        if(nextString.equals("/by") ) {
                            date = date + tk.nextToken();
                            break;
                        } else
                            value = value + nextString;
                    }
                    Deadline t = new Deadline(value,date);
                    list.add(t);

                } else if( action.equals("event")) {
                    String value = ""; String from = ""; String to = "";
                    while (tk.hasMoreTokens()) {
                        String nextString = tk.nextToken();
                        if(nextString.equals("/from") ) {
                            from = from + tk.nextToken();
                        } else if (nextString.equals("/to")) {
                            to = to + tk.nextToken();
                        } else
                            value = value + nextString;
                    }

                    Event t = new Event(value,from,to);
                    list.add(t);

                }

                System.out.println("Added : " + text);
                System.out.println("You have a total of " + list.size() + " tasks in the list");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
