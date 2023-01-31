package duke;

// A class to store global variable values.
public final class Values {
    // Logo art generated on https://patorjk.com/software/taag/#p=display&f=Graffiti&t=PixlBot
    /** String for PixlBot Logo. */
    public static final String LOGO = "__________.__       .__ __________        __   \n"
            + "\\______   \\__|__  __|  |\\______   \\ _____/  |_ \n"
            + " |     ___/  \\  \\/  /  | |    |  _//  _ \\   __\\\n"
            + " |    |   |  |>    <|  |_|    |   (  <_> )  |  \n"
            + " |____|   |__/__/\\_ \\____/______  /\\____/|__|  \n"
            + "                   \\/           \\/             ";

    /** String for a horizontal line. */
    public static final String HLINE = "--------------------";

    // Color support obtained from https://stackoverflow.com/a/45444716
    /** ANSI value for color purple. */
    public static final String COLOR_PURPLE = "\033[0;35m"; // PURPLE
    /** ANSI value for color red. */
    public static final String COLOR_RED = "\033[0;31m"; // RED
    /** ANSI value for color reset. */
    public static final String COLOR_RESET = "\033[0m"; // Text Reset

    /** Single whitespace character for print usage. */
    public static final String SPACE = " ";

    /** Regex for splitting strings by spaces */
    public static final String SPACEX = "\\s+";
}
