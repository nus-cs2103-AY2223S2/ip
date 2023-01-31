import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Page {

    private Scanner scan;
    private ArrayList<Quest> quests;

    public Page() {
        this.scan = new Scanner(System.in);
        this.quests = new ArrayList<>();
    }

    private void greet() {
        String welcome = "Greetings! 'Tis I, Page, thy medieval assistant.\n" +
                "Type 'help' for the list of available commands.";
        System.out.println(welcome);

        File f = new File("data/questlog.txt");
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] splitLine = line.split(" ", 3);
                String questType = splitLine[0];
                String questCompletion = splitLine[1];
                String restOfLine = splitLine[2];
                Quest q;
                if (questType.equals("[T]")) {
                    q = new Todo(restOfLine);
                } else if (questType.equals("[D]")) {
                    String[] splitByBy = restOfLine.split(" by: ", 2);
                    q = new Deadline(splitByBy[0], splitByBy[1]);
                } else if (questType.equals("[E]")) {
                    String[] splitByFromTo = restOfLine.split(" from: | to: ", 3);
                    q = new Event(splitByFromTo[0], splitByFromTo[1], splitByFromTo[2]);
                } else {
                    System.out.println("oops, something wrong with the Quest Log :(");
                    break;
                }
                if (questCompletion.equals("[X]")) {
                    q.markComplete();
                }
                quests.add(q);
            }
            log();
        } catch (FileNotFoundException e) {
            System.out.println("Starting a new Quest Log.\n");
        }
    }

    private void listen() {
        String input = scan.nextLine();
        switch (input) {
            case "bye":
                bye();
                return;
            case "log":
                log();
                break;
            case "help":
                help();
                break;
            default:
                String[] splitBySpace = input.split(" ", 2);
                String firstWord = splitBySpace[0];
                String restOfInput;
                if (splitBySpace.length == 1) {
                    restOfInput = "";
                } else {
                    restOfInput = splitBySpace[1];
                }
                switch (firstWord) {
                    case "complete":
                        try {
                            int questNum = Integer.parseInt(restOfInput);
                            if (questNum > quests.size()) {
                                System.out.println("Sorry, you only have " + quests.size() + " quests!");
                            } else {
                                quests.get(questNum - 1).markComplete();
                                System.out.println("Quest Complete! Hooray!\n" + quests.get(questNum - 1).toString());
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Sorry, that's not a number!");
                        }
                        break;
                    case "incomplete":
                        try {
                            int questNum = Integer.parseInt(restOfInput);
                            if (questNum > quests.size()) {
                                System.out.println("Sorry, you only have " + quests.size() + " quests!");
                            } else {
                                quests.get(questNum - 1).markIncomplete();
                                System.out.println("Quest Incomplete, the realm is in mortal danger!\n" + quests.get(questNum - 1).toString());
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Sorry, that's not a number!");
                        }
                        break;
                    case "delete":
                        try {
                            int questNum = Integer.parseInt(restOfInput);
                            if (questNum > quests.size()) {
                                System.out.println("Sorry, you only have " + quests.size() + " quests!");
                            } else {
                                quests.remove(questNum - 1);
                                System.out.println("Quest deleted! " + quests.size() + " quests remain.");
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Sorry, that's not a number!");
                        }
                        break;
                    case "todo":
                        if (!restOfInput.equals("")) {
                            addTodo(restOfInput);
                        } else {
                            System.out.println("Sorry, you must include a task description!");
                        }
                        break;
                    case "deadline":
                        String[] splitByBy = restOfInput.split(" /by ", 2);
                        if (splitByBy.length == 2) {
                            addDeadline(splitByBy[0], splitByBy[1]);
                        } else {
                            System.out.println("Sorry, you must include a deadline time!");
                        }
                        break;
                    case "event":
                        String task;
                        String from;
                        String to;
                        String[] splitByFrom = restOfInput.split(" /from ", 2);
                        if (splitByFrom.length == 2) {
                            task = splitByFrom[0];
                            String[] splitByTo = splitByFrom[1].split(" /to ", 2);
                            if (splitByTo.length == 2) {
                                from = splitByTo[0];
                                to = splitByTo[1];
                                addEvent(task, from, to);
                            } else {
                                System.out.println("Sorry, you must include an end time!");
                            }
                        } else {
                            System.out.println("Sorry, you must include a start time!");
                        }
                        break;
                    default:
                        System.out.println("My apologies, I cannot decipher your arcane incantations.\n" +
                                "Type 'help' for the list of commands I can understand.");
                }
                break;
        }
        listen();
    }

    private void help() {
        String helptext =
                "type 'help' to show this help text!\n" +
                "type 'todo someTask' to add the task to the Quest Log.\n" +
                "type 'deadline someDeadline /by someTime' to add a task with deadline someTime.\n" +
                "type 'event someEvent /from startTime /to endTime' to schedule an event lasting from startTime to endTime.\n" +
                "type 'log' to show the current Quest Log.\n" +
                "type 'complete 1' to mark the 1st quest as complete.\n" +
                "type 'incomplete 2' to mark the 2nd quest as incomplete.\n" +
                "type 'bye' to exit.";
        System.out.println(helptext);
    }

    private void addTodo(String input) {
        Todo t = new Todo(input);
        quests.add(t);
        System.out.println("Added to Quest Log: " + t.toString());
    }

    private void addDeadline(String input, String to) {
        Deadline d = new Deadline(input, to);
        quests.add(d);
        System.out.println("Added to Quest Log: " + d.toString());
    }

    private void addEvent(String input, String from, String to) {
        Event e = new Event(input, from, to);
        quests.add(e);
        System.out.println("Added to Quest Log: " + e.toString());
    }

    private void log() {
        System.out.println("Quest Log:");
        for (int i = 0; i < quests.size(); i++) {
            Quest q = quests.get(i);
            System.out.println((i + 1) + ": " + q.toString());
        }
    }

    private void bye() {
        System.out.println("Farewell, my liege.");
        File f = new File("data/questlog.txt");
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter("data/questlog.txt", false);
            for (Quest q : quests) {
                fw.write(q.toString() + "\n");
            }
            fw.close();
            System.out.println(f.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error: failed to save Quest Log :(" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Page page = new Page();
        page.greet();
        page.listen();
    }
}

