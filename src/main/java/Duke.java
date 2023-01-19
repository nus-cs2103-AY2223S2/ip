import java.util.Optional;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeList dukeList = new DukeList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(new TextBorder("Waddup the name's Duncan. Sorry but Duke couldn't make it, had a pretty bad stomach-ache."));
        System.out.println(new TextBorder("So what do you need bro?"));
        while (true) {
            String currWord = scanner.nextLine();
            String[] array = currWord.split(" ", 2);
            String first = array[0];
            if (currWord.equals("bye") ) {
                System.out.println(new TextBorder("Sweet, seeya."));
                break;
            } else if (currWord.equals("list")) {
                if (dukeList.isEmpty()) {
                    System.out.println(new TextBorder("Yo there's nothing in the list."));
                } else {
                    System.out.println(new TextBorder(dukeList.toString()));
                }
            } else if (first.equals("mark")) {
                String inputTask = array[1];
                dukeList.findAndMark(inputTask, true);
            } else if (first.equals("unmark")) {
                String inputTask = array[1];
                dukeList.findAndMark(inputTask, false);
            } else{
                dukeList.add(array[0], array[1]);
            }
        }
    }
}
