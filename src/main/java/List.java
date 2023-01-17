public class List {
    private String[] content = new String[100];
    private int counter = 0;

    public void add(String input) {
        if (input.isEmpty()) {
            System.out.println("Please enter a command.");
        } else {
            content[counter] = input;
            System.out.println(input + " added to list!");
            counter++;
        }
    }

    public void display() {
        String allElements = "";
        for (int i = 0; i < 101; i++) {
            if (content[i] != null) {
                allElements = allElements + (i + 1) + ". " + content[i] + "\n";
            } else {
                break;
            }
        }
        if (allElements == "") {
            System.out.println("No items in list!");
//
        } else {
            System.out.println(allElements);
        }
    }
}
