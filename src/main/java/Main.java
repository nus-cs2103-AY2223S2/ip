import exception.TreeBotException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            TreeBot treeBot = new TreeBot();
            treeBot.start();
        } catch (IOException e) {
            System.out.println("storage could not be instantiated");
        }
    }
}
