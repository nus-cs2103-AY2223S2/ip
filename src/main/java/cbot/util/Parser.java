package cbot.util;

import cbot.task.Deadline;
import cbot.task.Event;
import cbot.task.Task;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Parser {
    private Command c;
    private String text;
    
    public Parser(String input) throws PoorInputException {
        this.c = null;
        this.text = "";
        boolean matchFound = false;
        
        for (Command c : Command.values()) {
            if (c.match(input)) {
                this.c = c;
                this.text = c.getText(input);
                matchFound = true;
                break;
            }
        }
        
        if (!matchFound) {
            throw new PoorInputException("Sorry, I don't recognize that command :<");
        }
    }
    
    public boolean isBye() {
        return (this.c == Command.BYE);
    }
    
    public boolean needSave() {
        return this.c.needSave();
    }
    
    public void respond(TaskList tl) throws PoorInputException, DateTimeParseException {
        if (this.c.missingText(this.text)) {
            throw new PoorInputException("Command \"" + this.c.toString() + "\" needs an input");
        }
        
        switch (this.c) {
        case LIST:
            if (tl.getCount() == 0) {
                UI.say("Freedom! You have no tasks :D");
            } else {
                UI.say("Here's what you have:");
                UI.printMany(tl.listTasks());
            }
            break;
        
        case MARK:
            try {
                int num = Integer.valueOf(this.text);
                UI.say(tl.mark(num));
            } catch (PoorInputException e) {
                throw e;
            } catch (NumberFormatException e) {
                throw new BadInputException("Invalid index!");
            }
            break;
        
        case UNMARK:
            try {
                int num = Integer.valueOf(this.text);
                UI.say(tl.unmark(num));
            } catch (PoorInputException e) {
                throw e;
            } catch (NumberFormatException e) {
                throw new BadInputException("Invalid index!");
            }
            break;
            
        case DELETE:
            try {
                int num = Integer.valueOf(this.text);
                UI.say(tl.delTask(num));
            } catch (PoorInputException e) {
                throw e;
            } catch (NumberFormatException e) {
                throw new BadInputException("Invalid index!");
            }
            break;
            
        case TODO:
            UI.say(tl.addTask(new Task(this.text)));
            break;
            
        case DEADLINE:
            String BY_KEYWORD = "/by ";
            int BY_LENGTH = BY_KEYWORD.length();
        
            if (!this.text.contains(BY_KEYWORD)) {
                // no /by
                throw new PoorInputException("Missing \"/by\" keyword");
            }
            
            int byIndex = this.text.indexOf(BY_KEYWORD);

            if (byIndex == 0) {
                // no desc
                throw new BadInputException("Missing deadline description");
            } else if (byIndex + BY_LENGTH == this.text.length()) {
                // no due date
                throw new BadInputException("Missing due date");
            }
            
            String dlDesc = this.text.substring(0, byIndex);
            String dlDueStr = this.text.substring(byIndex + BY_LENGTH);
            
            LocalDateTime dlDue = TimeStuff.parseDT(dlDueStr);
            UI.say(tl.addTask(new Deadline(dlDesc, dlDue)));
            break;
            
        case EVENT:
            String FROM_KEYWORD = "/from ";
            String TO_KEYWORD = "/to ";
            int FROM_LENGTH = FROM_KEYWORD.length();
            int TO_LENGTH = TO_KEYWORD.length();
        
            if (!this.text.contains(FROM_KEYWORD)) {
                // no /from
                throw new PoorInputException("Missing \"/from\" keyword");
            } else if (!this.text.contains(TO_KEYWORD)) {
                // no /to
                throw new PoorInputException("Missing \"/to\" keyword");
            }
        
            int fromIndex = this.text.indexOf(FROM_KEYWORD);
            int toIndex = this.text.indexOf(TO_KEYWORD);
            
            if (toIndex < toIndex) {
                // /to before /from
                throw new PoorInputException("\"/to\" before \"/from\"");
            } else if (fromIndex == 0) {
                // no desc
                throw new BadInputException("Missing event description");
            } else if (fromIndex + FROM_LENGTH >= toIndex) {
                // no start
                throw new BadInputException("Missing start date");
            } else if (toIndex + TO_LENGTH == this.text.length()) {
                // no end
                throw new BadInputException("Missing end date");
            }
            
            String eDesc = this.text.substring(0, fromIndex);
            String eStartStr = this.text.substring(fromIndex + FROM_LENGTH, toIndex);
            String eEndStr = this.text.substring(toIndex + TO_LENGTH);

            LocalDateTime eStart = TimeStuff.parseDT(eStartStr);
            LocalDateTime eEnd = TimeStuff.parseDT(eEndStr);
            UI.say(tl.addTask(new Event(eDesc, eStart, eEnd)));
            break;
            
        case SORT:
            if (tl.getCount() == 0) {
                throw new PoorInputException("You have no tasks to sort :P");
            }
            
            tl.sort();
            UI.say("Okay! I've sorted your tasks by date:");
            UI.printMany(tl.listTasks());
            break;
            
        case BEFORE:
            if (tl.getCount() == 0) {
                throw new PoorInputException("Eh? You have no tasks to filter");
            }
            
            LocalDateTime bef = TimeStuff.parseDT(this.text);
            
            ArrayList<String> arrBef = tl.listFilter(t ->
                    (t.hasTime() && t.compareTo(new Deadline("", bef)) < 0));
            
            if (arrBef.isEmpty()) {
                UI.say("You don't have any tasks before " + this.text);
            } else {
                UI.say("Here are your tasks before " + this.text + ":");
                UI.printMany(arrBef);
            }
            break;
            
        case AFTER:
            if (tl.getCount() == 0) {
                throw new PoorInputException("Eh? You have no tasks to filter");
            }
            
            LocalDateTime aft = TimeStuff.parseDT(this.text);
            
            ArrayList<String> arrAft = tl.listFilter(t ->
                    (t.hasTime() && t.compareTo(new Deadline("", aft)) > 0));
            
            if (arrAft.isEmpty()) {
                UI.say("You don't have any tasks after " + this.text);
            } else {
                UI.say("Here are your tasks after " + this.text + ":");
                UI.printMany(arrAft);
            }
            break;
            
        case FILTER:
            if (tl.getCount() == 0) {
                throw new PoorInputException("Eh? You have no tasks to filter");
            }
            
            String msg = "Ok!";
            ArrayList<String> arrFilter = new ArrayList<String>();
            
            switch (this.text.toLowerCase()) {
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
                throw new PoorInputException("I'm not sure what Task type that is :(");
            }
            
            if (arrFilter.isEmpty()) {
                UI.say("You don't have any of those :/");
            } else {
                UI.say(msg);
                UI.printMany(arrFilter);
            }
            break;
            
        default:
            // catches all the BADs
            throw new BadInputException("That command needs an input");
        }
    }
}