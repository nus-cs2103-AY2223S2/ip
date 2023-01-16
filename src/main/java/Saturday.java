public class Saturday {
    public Saturday() {
        Utils.divider();
        System.out.println("\t Hello! I'm Saturday\n\t What can I do for you?");
        Utils.divider();
        System.out.println("");
    }

    public void entry(String text) {
        Utils.divider();
        System.out.println('\t' + text);
        Utils.divider();
        System.out.println("");
    }

    public void exit() {
        entry("Bye. Hope to see you again soon!");
    }

}
