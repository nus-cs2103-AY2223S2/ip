public class Utilities {
    public static void lineBreak(){
        String line = "-";
        String res = space() + line.repeat(80);
        System.out.println(res);
    }

    public static String space() {
        String space = " ";
        return space.repeat(5);
    }

    public static void printFormattedString(String str) {
        lineBreak();
        System.out.println(str);
        lineBreak();
    }
}
