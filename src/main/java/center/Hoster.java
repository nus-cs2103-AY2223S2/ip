package center;

import javafx.application.Application;
/**
 * Hosts the Duke Class
 */
public class Hoster {

    /*
       For reasons currently unknown, JavaFX applications on this project are unable to run when the class that extends
       Application contains a main method that launches itself.
       This class was created to mitigate this issue, launching the Duke class that serves as the central hub for
       the chatbot.
    */

    /**
     * Launches Duke.
     * @param args the parameters for the main method
     */
    public static void main(String[] args) {
        Application.launch(Duke.class,args);
    }






}
