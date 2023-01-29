package duke.duke;

import java.util.Scanner;


/**
 * A user-interface for Duke.
 * Reads in user-inputs and displays information.
 */
public class Ui {
    private static String logo;
    private static String welcome;
    private final Scanner scanner;
    private static String divider;

    Ui() {
        this.logo = "....................*******************************...................\n"
                + "...................V:V**.......................:VV:V..................\n"
                + "...................V:V*:.......................:V*:V..................\n"
                + "................V**M...***VFV************VFV***V...$**V...............\n"
                + "..............V*M..V:..F.*F:*M..........IV:FV..V...M..I*V.............\n"
                + "..............V.I..V:..I:*IFIV::::::::::*IFI*::I...M..F.V:............\n"
                + "..............F*M..V:..:***********************:...M..I*F:............\n"
                + "................F**M:..............................$**I...............\n"
                + "..................*F...F**M**M***M**VV**M***M**F...I*.................\n"
                + ".................V:....M**M**N***M**FF**N***M**M....:V................\n"
                + ".................V:....V**I**I***I**VV**I***I**V....:V................\n"
                + ".................V:.................................:V................\n"
                + ".................V:.................................:V................\n"
                + ".................:************I*********VV***********:................\n"
                + ".......................:::::::N*********VV::::::......................\n"
                + "................:*F***IV::::::::::::::::::::::::N***I**...............\n"
                + "...............*V.V...V*.*:::::::::::::::::::*..I...V.**..............\n"
                + "...............**.V...V*.*:::::::::::::::::::V..I...V.:V..............\n"
                + "................**N:..V*.*...................*..M...N**...............\n"
                + "...............:*I**N*V*.*:::::::::::::::::::V..M*N*:I**..............\n"
                + ".............:**V:.VF.**.*:::::::::::::::::::V..V.VI:.V***............\n"
                + "............***V:.VV:.**.*...................*..V..FV:.V**V...........\n"
                + "...........V**V:.FV*..**.:****************:**:..V..*VF:.V**V..........\n"
                + ".........::**M::MV:...**.*:.V**************:.V..V...:*M:.IV*::........\n"
                + "......:***:MV*VI......**..**:*:::::::::::*:**:..V......VF*VN::***.....\n"
                + ".....**.***VV*VF......:V**VVVVVVV******VVVVVV***V......VF*VI***::*....\n"
                + ".....V..V...:I.**........:V.....**....V:.....F.........V.F:...**.V....\n"
                + ".....:**:.:***.V:........:F*****V*....V******I.........V.****.:***....\n"
                + "..........V..**:.........:V.....**....V......V.........:**:.V*........\n"
                + "..........***:...........:F*****V*....V******I............***:........";
        this.scanner = new Scanner(System.in);
        this.welcome = "Hello! I'm GPT0.01!\nWhat can I do for you?";
        this.divider = "\n____________________________________________________________\n";

    }

    /**
     * Reads user input and converts into a more readable form for the parser.
     * @return a processed form of the input.
     *
     */
    public String[] readLine() {
        return this.scanner.nextLine().split(" ");
    }

    /**
     * Shows the logo.
     */
    public void showLogo() {
        System.out.println(this.logo);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        display(this.welcome);
    };

    /**
     * Displays text on the screen.
     * @param str
     */
    public void display(String str) {
        System.out.println(this.divider + str + this.divider);
    }

    /**
     * Displays error messages.
     * @param err
     */
    public void showError(Exception err) {
        display(err.getMessage());
    }
}
