public class Ui {
    /**
     * Prints out a response, wrapped with 2 hyphenated lines above and below
     * @param response
     */
    public static void print(String response) {
        System.out.println("----------------------------------");
        System.out.println(response.trim());
        System.out.println("----------------------------------");
    }
}
