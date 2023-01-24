import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Cbot {
    private static final String[] DFORMS = {"y-M-d", "d/M/y", "d MMM y", "MMM d y"};
    private static final String[] TFORMS = {
            "y-M-d Hmm", "y-M-d H:m", "y-M-d ha", "y-M-d h a",
            "y-M-d h.ma", "y-M-d h.m a", "y-M-d h:ma", "y-M-d h:m a",
            "d/M/y Hmm", "d/M/y H:m", "d/M/y ha", "d/M/y h a",
            "d/M/y h.ma", "d/M/y h.m a", "d/M/y h:ma", "d/M/y h:m a",
            "d MMM y Hmm", "d MMM y H:m", "d MMM y ha", "d MMM y h a",
            "d MMM y h.ma", "d MMM y h.m a", "d MMM y h:ma", "d MMM y h:m a",
            "MMM d y Hmm", "MMM d y H:m", "MMM d y ha", "MMM d y h a",
            "MMM d y h.ma", "MMM d y h.m a", "MMM d y h:ma", "MMM d y h:m a"
    };
    
    public static LocalDateTime parseDT(String dtStr) throws DateTimeParseException {
        String str = dtStr.trim();
        
        for (String df : DFORMS) {
            try {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(df)).atStartOfDay();
            } catch (DateTimeParseException e) {}
        }
        
        for (String tf : TFORMS) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(tf));
            } catch (DateTimeParseException e) {}
        }
        
        return LocalDateTime.parse(str);
    }        
    
    public static void printMany(ArrayList<String> arr) {
        String BLANK = "        ";
        
        for (String s : arr) {
            System.out.println(BLANK + s);
        }
    }
    
    public static void main(String[] args) throws IOException {
        String dukeLogo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        
        String cbotLogo = "  _____  _            _\n"
                + " /  ___]| |   ^-^   _| |_\n"
                + "|  / :D | |___  ___[_ + _]\n"
                + "|  \\___ | / . \\/ . \\ | |\n"
                + " \\_____]|_,_*_/\\_*_/ |_/\n";
        
        // Frequently Used Strings
        String INDENT   = "      ~ ";
        String WARNING  = "      ! ";
        String PROMPT   = "\n v v\n";
        String STALL    = "\n   o\n   o\n   o\n\n";
        String DATETIME_ERROR = WARNING + "<Error> Sorry, I don't know how to interpret that datetime\n"
                + "        Try something in the form: yyyy-MM-dd HH:mm";
        
        System.out.println(STALL + INDENT + "Hey! D:< I'm not\n" + dukeLogo);
        System.out.println(INDENT + "I'm\n" + cbotLogo);
        
        TaskList tl = new TaskList();
        
        if (FileStuff.fileExists()) {
            tl = FileStuff.loadFile();
        }
        
        System.out.println(STALL + INDENT
                + "How can I help you today?");
        
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        boolean doSave = true;
        
        while (loop) {
            System.out.println(PROMPT);
            String userInput = sc.nextLine();
            System.out.println();
            
            if (userInput.contains(Task.SEP)) {
                System.out.println(WARNING + "<Error> Please avoid using: \""
                        + Task.SEP + "\"");
                continue;
            }
            
            Command com = Command.NOMATCH;
            String userText = userInput;
            
            for (Command c : Command.values()) {
                if (c.match(userInput)) {
                    userText = c.getText(userInput);
                    com = c;
                    break;
                }
            }
            
            if (com.missingText(userInput)) {
                System.out.println(WARNING + "<Error> That command needs an input");
                continue;
            }
            
            switch (com) {
            case BYE:
                loop = false;
                doSave = false;
                break;
            
            case LIST:
                doSave = false;
                
                if (tl.getCount() == 0) {
                    System.out.println(INDENT + "Freedom! You have no tasks :D");
                    break;
                }
                
                System.out.println(INDENT + "Here's what you have:");
                printMany(tl.listTasks());
                break;
            
            case MARK:
                try {
                    int num = Integer.valueOf(userText);
                    System.out.println(INDENT + tl.mark(num));
                } catch (NumberFormatException ex) {
                    System.out.println(WARNING + "<Error> Invalid index!");
                }
                break;
            
            case UNMARK:
                try {
                    int num = Integer.valueOf(userText);
                    System.out.println(INDENT + tl.unmark(num));
                } catch (NumberFormatException ex) {
                    System.out.println(WARNING + "<Error> Invalid index!");
                }
                break;
            
            case DELETE:
                try {
                    int num = Integer.valueOf(userText);
                    System.out.println(INDENT + tl.delTask(num));
                } catch (NumberFormatException ex) {
                    System.out.println(WARNING + "<Error> Invalid index!");
                }
                break;
            
            case TODO:
                System.out.println(INDENT + tl.addTask(new Task(userText)));
                break;
            
            case DEADLINE:
                String BY_KEYWORD = "/by ";
                int BY_LENGTH = BY_KEYWORD.length();
            
                if (userText.contains(BY_KEYWORD)) {
                    int byIndex = userText.indexOf(BY_KEYWORD);
                    
                    if (byIndex == 0) {
                        // no desc
                        System.out.println(WARNING + "<Error> Missing deadline description");
                    } else if (byIndex + BY_LENGTH == userText.length()) {
                        // no due date
                        System.out.println(WARNING + "<Error> Missing due date");
                    } else {
                        // all's good
                        String dlDesc = userText.substring(0, byIndex);
                        String dlDueStr = userText.substring(byIndex + BY_LENGTH);
                        
                        try {
                            LocalDateTime dlDue = parseDT(dlDueStr);
                            System.out.println(INDENT + tl.addTask(new Deadline(dlDesc, dlDue)));
                        } catch (DateTimeParseException e) {
                            System.out.println(DATETIME_ERROR);
                        }
                    }
                } else {
                    // no /by
                    System.out.println(WARNING + "<Error> Missing \"/by\" keyword");
                }
                break;
            
            case EVENT:
                String FROM_KEYWORD = "/from ";
                String TO_KEYWORD = "/to ";
                int FROM_LENGTH = FROM_KEYWORD.length();
                int TO_LENGTH = TO_KEYWORD.length();
            
                if (userText.contains(FROM_KEYWORD)) {
                    int fromIndex = userText.indexOf(FROM_KEYWORD);
                    
                    if (userText.contains(TO_KEYWORD)) {
                        int toIndex = userText.indexOf(TO_KEYWORD);
                        
                        if (toIndex < toIndex) {
                            // /to before /from
                            System.out.println(WARNING + "<Error> \"/to\" before \"/from\"");
                        } else if (fromIndex == 0) {
                            // no desc
                            System.out.println(WARNING + "<Error> Missing event description");
                        } else if (fromIndex + FROM_LENGTH >= toIndex) {
                            // no start
                            System.out.println(WARNING + "<Error> Missing start date");
                        } else if (toIndex + TO_LENGTH == userText.length()) {
                            // no end
                            System.out.println(WARNING + "<Error> Missing end date");
                        } else {
                            // all's good
                            String eDesc = userText.substring(0, fromIndex);
                            String eStartStr = userText.substring(fromIndex + FROM_LENGTH, toIndex);
                            String eEndStr = userText.substring(toIndex + TO_LENGTH);
                            
                            try {
                                LocalDateTime eStart = parseDT(eStartStr);
                                LocalDateTime eEnd = parseDT(eEndStr);
                                System.out.println(INDENT + tl.addTask(new Event(eDesc, eStart, eEnd)));
                            } catch (DateTimeParseException e) {
                                System.out.println(DATETIME_ERROR);
                            }
                        }
                    } else {
                        // no /to
                        System.out.println(WARNING + "<Error> Missing \"/to\" keyword");
                    }
                } else {
                    // no /by
                    System.out.println(WARNING + "<Error> Missing \"/from\" keyword");
                }
                break;
            
            case SORT:
                if (tl.getCount() == 0) {
                    System.out.println(INDENT + "You have no tasks to sort :P");
                    break;
                }
                
                tl.sort();
                System.out.println(INDENT + "Okay! I've sorted your tasks by date:");
                printMany(tl.listTasks());
                break;
            
            case BEFORE:
                if (tl.getCount() == 0) {
                    System.out.println(INDENT + "Eh? You have no tasks to filter");
                    break;
                }
                
                try {
                    LocalDateTime bef = parseDT(userText);
                } catch (DateTimeParseException e) {
                    System.out.println(DATETIME_ERROR);
                    break;
                }
                
                LocalDateTime bef = parseDT(userText);
                ArrayList<String> arrBef = tl.listFilter(t ->
                        (t.hasTime() && t.compareTo(new Deadline("", bef)) < 0));
                
                if (arrBef.isEmpty()) {
                    System.out.println(INDENT + "You don't have any tasks before " + userText);
                } else {
                    System.out.println(INDENT + "Here are your tasks before " + userText + ":");
                    printMany(arrBef);
                }
                break;
            
            case AFTER:
                if (tl.getCount() == 0) {
                    System.out.println(INDENT + "Eh? You have no tasks to filter");
                    break;
                }
                
                try {
                    LocalDateTime aft = parseDT(userText);
                } catch (DateTimeParseException e) {
                    System.out.println(DATETIME_ERROR);
                    break;
                }
                
                LocalDateTime aft = parseDT(userText);
                ArrayList<String> arrAft = tl.listFilter(t ->
                        (t.hasTime() && t.compareTo(new Deadline("", aft)) > 0));
                
                if (arrAft.isEmpty()) {
                    System.out.println(INDENT + "You don't have any tasks after " + userText);
                } else {
                    System.out.println(INDENT + "Here are your tasks after " + userText + ":");
                    printMany(arrAft);
                }
                break;
            
            case FILTER:
                if (tl.getCount() == 0) {
                    System.out.println(INDENT + "Eh? You have no tasks to filter");
                    break;
                }
                
                String msg = "Ok!";
                ArrayList<String> arrFilter = new ArrayList<String>();
                boolean stopFilter = false;
                
                switch (userText.toLowerCase()) {
                case "todo":
                case "td":
                case "t":
                    msg = "Ok! These are on your ToDo list:";
                    arrFilter = tl.listFilter(t -> t.getSymbol().equals("T"));
                    break;
                
                case "deadline":
                case "dl":
                case "d":
                    msg = "Ok! Here are your Deadlines:";
                    arrFilter = tl.listFilter(t -> t.getSymbol().equals("D"));
                    break;
                
                case "event":
                case "ev":
                case "e":
                    msg = "Ok! Here are your Events:";
                    arrFilter = tl.listFilter(t -> t.getSymbol().equals("E"));
                    break;
                
                case "complete":
                case "done":
                case "completed":
                case "X":
                    msg = "Ok! Here are the Tasks you've completed:";
                    arrFilter = tl.listFilter(t -> t.getStatus().equals(Task.DONE_TRUE));
                    break;
                
                case "incomplete":
                case "not done":
                case "!done":
                case "undone":
                    msg = "Ok! Here are the Tasks you haven't completed yet:";
                    arrFilter = tl.listFilter(t -> !t.getStatus().equals(Task.DONE_TRUE));
                    break;
                
                default:
                    System.out.println(WARNING + "<Error> I'm not sure what Task type that is :(");
                    stopFilter = true;
                }
                
                if (stopFilter) {
                    break;
                }
                
                if (arrFilter.isEmpty()) {
                    System.out.println(INDENT + "You don't have any of those :/");
                } else {
                    System.out.println(INDENT + msg);
                    printMany(arrFilter);
                }
                break;
            
            case NOMATCH:
                doSave = false;
                System.out.println(INDENT + "Sorry, I don't recognize that command :<");
                break;
            
            default:
                // catches all the BADs
                doSave = false;
                System.out.println(WARNING + "<Error> That command needs an input");
            }
            
            if (doSave) {
                FileStuff.saveFile(tl);
            } else {
                doSave = true;
            }
        }
        
        System.out.println(STALL + INDENT + "See you again!");
    }
}