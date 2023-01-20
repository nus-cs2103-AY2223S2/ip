import java.util.Scanner;

public class Cbot {
    public static void main(String[] args) {
        TaskList tl = new TaskList();
        
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
        String PROMPT   = " v v\n";
        String STALL    = "\n   o\n   o\n   o\n\n";
        
        System.out.println(STALL + INDENT + "Hey! D:< I'm not\n" + dukeLogo);
        System.out.println(INDENT + "I'm\n" + cbotLogo);
        System.out.println(STALL + INDENT
                + "How can I help you today?\n");
        
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        
        while (loop) {
            System.out.println(PROMPT);
            String userInput = sc.nextLine();
            
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
                break;
            
            case LIST:
                if (tl.getCount() == 0) {
                    System.out.println(INDENT + "Freedom! You have no tasks :D");
                } else {
                    System.out.println(INDENT + "Here's what you have:");
                    tl.printTasks();
                    System.out.println();
                }
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
                        String dlDue = userText.substring(byIndex + BY_LENGTH);
                        
                        System.out.println(INDENT + tl.addTask(new Deadline(dlDesc, dlDue)));
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
                            String eStart = userText.substring(fromIndex + FROM_LENGTH, toIndex);
                            String eEnd = userText.substring(toIndex + TO_LENGTH);
                        
                            System.out.println(INDENT + tl.addTask(new Event(eDesc, eStart, eEnd)));
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
            
            case NOMATCH:
                System.out.println(INDENT + "Sorry, I don't recognize that command :<");
                break;
            
            default:
                // catches all the BADs
                System.out.println(WARNING + "<Error> That command needs an input");
            }
        }
        
        System.out.println(STALL + INDENT + "See you again!");
    }
}