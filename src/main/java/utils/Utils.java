package utils;

import java.util.Arrays;

public final class Utils {
  public static String joiner(String[] args, int start, int end) {
    return String.join(" ", Arrays.copyOfRange(args, start, end));
  }

  public static String joiner(String[] args, int start) {
    return joiner(args, start, args.length);
  }
}
