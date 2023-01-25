package Duke;

import Duke.Command.Command;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e); // or e.getMessage()
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}

//    public static String filePath = "duke.txt";
//
//    public static void main(String[] args) throws EmptyCommandException, InvalidCommandException, EmptyArgumentException {
//
//        ArrayList<Task> arrL = new ArrayList<>();
//
//        try {
//            populateArray(arrL, filePath);
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        }
//
//        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
//
//        Scanner bucky = new Scanner(System. in);
//
//        while (true) {
//            String str = bucky.nextLine();
//
//            if (str.equals("")) { throw new EmptyCommandException();}
//
//            String s[] = str.split(" ", 2);
//            String firstWord = s[0];
//
//            if (str.equals("bye")) {
//                System.out.println("Bye. Hope to see you again soon!");
//                try {
//                    save(arrL, filePath);
//                } catch (IOException e) {
//                    System.out.println("Something went wrong: " + e.getMessage());
//                }
//                break;
//            }
//
//
//            if (str.equals("list")) {
//                System.out.println("Here are the tasks in your list:");
//                for (int i = 0 ; i < arrL.size() ; i++) {
//                    System.out.println((i+1) + ". " + arrL.get(i));
//                }
//            } else if (firstWord.equals("mark")) {
//                if (s.length <= 1) { throw new EmptyArgumentException();}
//                int num = Integer.parseInt(s[1]) - 1;
//                arrL.get(num).markAsDone();
//                System.out.println("Nice! I've marked this task as done:\n" + arrL.get(num));
//            } else if (firstWord.equals("unmark")) {
//                if (s.length <= 1) { throw new EmptyArgumentException();}
//                int num = Integer.parseInt(s[1]) - 1;
//                arrL.get(num).markAsDone();
//                System.out.println("OK, I've marked this task as not done yet:\n" + arrL.get(num));
//            } else if (firstWord.equals("delete")) {
//                if (s.length <= 1) { throw new EmptyArgumentException();}
//                int num = Integer.parseInt(s[1]) - 1;
//                Task t = arrL.remove(num);
//                System.out.println("Noted. I've removed this task:\n" + t +
//                        "\nNow you have " + arrL.size() + " tasks in the list.");
//            } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")){
//                if (s.length <= 1) { throw new EmptyArgumentException();}
//                if (firstWord.equals("todo")) {
//                    arrL.add(new ToDo(s[1], false));
//                }
//                else if (firstWord.equals("deadline")) {
//                    String st[] = s[1].split(" /by ", 2);
//                    arrL.add(new Deadline(st[0], false, LocalDate.parse(st[1])));
//                } else if (firstWord.equals("event")) {
//                    String st[] = s[1].split(" /from ", 2);
//                    String stt[] = st[1].split(" /to ", 2);
//                    arrL.add(new Event(st[0], false, stt[0], stt[1]));
//                }
//                System.out.println("Got it. I've added this task:\n" + arrL.get(arrL.size()-1)
//                    + "\nNow you have " + arrL.size() + " tasks in the list.");
//            } else {
//                throw new InvalidCommandException();
//            }
//        }
//
//    }
//
//
//
//    public static void populateArray(List<Task> l, String filePath) throws FileNotFoundException {
//        File f = new File(filePath); // create a File for the given file path
//        try {
//            if(!f.exists()) {
//                f.createNewFile();
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        Scanner s = new Scanner(f); // create a Scanner using the File as the source
//
//        while (s.hasNext()) {
//            String str = s.nextLine();
//            String parts[] = str.split("~", 5);
//
//            switch (parts[0]) {
//                case "T":
//                    l.add(new ToDo(parts[2], parts[1].equals("1")));
//                    break;
//                case "D":
//                    l.add(new Deadline(parts[2], parts[1].equals("1"), LocalDate.parse(parts[3])));
//                    break;
//                case "E":
//                    l.add(new Event(parts[2], parts[1].equals("1"), parts[3], parts[4]));
//                    break;
//            }
//        }
//    }
//
//
//    public static void save(List<Task> l, String filePath) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        for (Task i : l) {
//            fw.write(i.saveFormat() + "\n");
//        }
//        fw.close();
//    }
//}