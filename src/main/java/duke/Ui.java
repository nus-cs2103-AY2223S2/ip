package duke;

import java.util.ArrayList;

public class Ui {

    static String divider = "    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══";
    static String line = "      _____________________________________________________________________";

    public static void greet() {
        System.out.println("  ───── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───── \n Hello! I'm Broccoli the dinosaur \n           <|°▿▿▿▿°|/ \n      What can I do for you? \n   ──── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────");
    }

    public static void bye() {
        System.out.println("    ──────── ⋅ ∙ ∘ ☽ ༓ ☾ ∘ ⋅ ⋅ ─────────");
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("\n   ‧˚₊꒷꒦︶︶︶︶︶꒷꒦︶︶︶︶︶꒦꒷‧₊˚⊹");
        System.exit(0);
    }

    public static void displayList(ArrayList<Task> array) {
        System.out.println(divider);
        for (int j = 0; j < array.size(); j++) {
            System.out.println("      " + (j+1) + "." + array.get(j).toString());
        }
        System.out.println(divider);
    }

    static void addTask(ArrayList<Task> array, Task t) {
        System.out.println(divider);
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + t.toString());
        System.out.println("     Now you have " + array.size() + " tasks in the list.");
        System.out.println(divider);
    }

    static void removeTask(ArrayList<Task> array, String[] splitInput) {
        System.out.println(divider);
        System.out.println("      Noted. I've removed this task:");
        System.out.println("      "+ array.get((Integer.parseInt(splitInput[1]) - 1)).toString());
        System.out.println("      Now you have " + array.size() + " tasks in the list.");
        System.out.println(divider);
    }

    static void markTask(ArrayList<Task> array, String[] splitInput) {
        System.out.println(divider);
        System.out.println("      Nice! I've marked this task as done:");
        System.out.println("      " + array.get((Integer.parseInt(splitInput[1]) - 1)).toString());
        System.out.println(divider);
    }

    static void unmarkTask(ArrayList<Task> array, String[] splitInput) {
        System.out.println(divider);
        System.out.println("      OK, I've marked this task as not done yet:");
        System.out.println("      " + array.get((Integer.parseInt(splitInput[1]) - 1)).toString());
        System.out.println(divider);
    }
}

