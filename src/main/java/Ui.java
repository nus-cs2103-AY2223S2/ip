import java.util.ArrayList;

public class Ui {

    /**
     * iterates through the arraylist and prints out the elements inside
     */
    public void printList(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += " Here are the tasks in your list:";
        for (int i = 0; i < arrList.size(); i++) {
            totalString += "\n     " + (i+1) + "." + arrList.get(i).toString();
        }
        this.printWithLines(totalString);
    }

    /**
     * prints out the text with lines on top and below
     * @param text the content that is to be printed out
     */
    public void printWithLines(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________________________________________");
    }

    public void greetings() {
        this.printWithLines(" Hello! I'm Duke!\n     What can I do for you today?");

    }

    public void markResponse(Task t) {
        String str = " Nice! I've marked this task as done!:\n" + "       " + t.toString();
        this.printWithLines(str);
    }

    public void unmarkResponse(Task t) {
        String str = " OK, I've marked this task as not done yet:\n" + "       " + t.toString();
        this.printWithLines(str);
    }

    public void addItemResponse(Task t, ArrayList<Task> arr) {
        String str = "  " + t.toString();
        str = " Got it. I've added this task:\n     " + str;
        this.printWithLines(str + listUpdate(arr));

    }

    public void goodBye() {
        this.printWithLines(" Bye! Hope to see you again soon!");
    }

    public void deleteItemResponse(Task t, ArrayList<Task> arr) {
        String str = " Noted. I'm removing this task:\n       " + t.toString();
        this.printWithLines(str + listUpdate(arr));

    }

    /**
     *
     * @return the string that updates the number of elements left in the string
     */
    public String listUpdate(ArrayList<Task> arr) {
        String plural = "";
        if (arr.size() > 1) {
            plural = "s";
        }
        return "\n     Now you have " + arr.size() + " task" + plural + " in the list.";
    }


}
