package uwuke.controller;

/**
 * Advises users on potential solutions when no matching command is found.
 * 
 * TODO: Add more functionality to the class in the future, advise is not that helpful currently.
 */
public class Advisor {
    /**
     * Advise users on potential fixes
     * 
     * @param input faulty input string given by user
     * @return advise based on faulty input
     */
    public static String adviseUser(String input) {
        if (input.contains("deadline") && !input.contains("/by")) {
            return "Please add a description for deadline using the \"/by\" keyword. :c\nExample: deadline a /by b";
        } else if (input.contains("event") && !(input.contains("/from") && input.contains("/to"))) {
            return "Please use both the \"/from\" and \"/to\" keywords to add a new event. :c\nExample: event a /from b /to c";
        } else if (input.contains("todo")) {
            return "The description of a todo cannot be empty :c";
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :c";
        }
    }
}