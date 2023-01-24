
import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws EmptyDescriptionException, UnknownInputException {
        Scanner scanner = new Scanner(System.in);
        FileOutputStream fos;
        FileInputStream fis;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        DukeList dukeList = new DukeList();

        try {
            if ((new File("./data/DukeList.ser")).exists()) {
                fis = new FileInputStream("./data/DukeList.ser");
                ois = new ObjectInputStream(fis);
                try {
                    dukeList = (DukeList) ois.readObject();

                } catch (IOException e) {
                    System.out.println("Creating new save");
                }
            }


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
                    fos = new FileOutputStream("./data/DukeList.ser");
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(dukeList);
                    oos.close();
                    oos.flush();

                    fos.close();
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
                } else if (first.equals("delete")) {
                    try {
                        dukeList.delete(Integer.parseInt(array[1]));
                    }  catch (TaskOutOfRangeException e) {
                        System.out.println(e);
                    }
                } else {
                    if (array.length != 2) {
                        if (first.equals("todo") || first.equals("deadline") || first.equals("event")) {
                            throw new EmptyDescriptionException("Sorry, your " + first + " task description is missing.");
                        } else {
                            throw new UnknownInputException("Hmm, I'm not sure what you're saying man.");
                        }
                    }
                    try {
                        dukeList.add(array[0], array[1]);
                    } catch (DateTimeParseException e) {
                        System.out.println("Hey, I can't see what date that is man.");
                    }                }
            }



        } catch (FileNotFoundException e) {
            System.out.println("Hey sorry man, something's off bout you're save data.");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Yo something's up with your I/O, gotta get it checked before doing this.");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Hey I can't find your object class.");
            System.out.println(e);
        }
    }
}

