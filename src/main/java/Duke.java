import java.io.*;
public class Duke {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = "Welcome hooman!\n" +
                "Wat u want today?\n";
        System.out.println(divider + "Hello from\n" + logo + welcome + divider);
        String in = br.readLine();
        String bye = "bye";
        while(!in.equalsIgnoreCase(bye)){
            bw.write(divider + in + "\n" + divider + "\n");
            bw.flush();
            in = br.readLine();
        }
        bw.write(divider + "Ah..... okkkk lo nehmind. GO. BYE. :)\n" + "\n");
        bw.flush();
    }
}
