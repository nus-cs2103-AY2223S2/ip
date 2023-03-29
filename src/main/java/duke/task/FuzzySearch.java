package duke.task;

/**
 *      File name: FuzzySearch.java
 *      @author: Jerome Neo
 *      Description: A class for matching a string with a list of strings.
 */
public class FuzzySearch {

    /**
     * Given an input String object, the fuzzy search will match the Task to options which is a TaskList.
     * The threshold is chosen such that the smaller the value, the stricter the matching.
     * @param input String object
     * @param options TaskList object
     * @param threshold a non-negative integer, range from 0 to length of longer string compared.
     * @return a closest matched Task
     */
    public static Task fuzzySearch(String input, TaskList options, int threshold) {
        Task closestMatch = null;
        int closestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < options.size(); i++) {
            Task option = options.getTaskAtIndex(i);
            String optionDescription = option.getDescription();
            int distance = levenshteinDistance(input, optionDescription);
            if (distance <= threshold && distance < closestDistance) {
                closestMatch = option;
                closestDistance = distance;
            }
        }
        return closestMatch;
    }

    /**
     * This code was taken from the Levenshtein distance algorithm to compare strings.
     * Referenced from:
     * https://www.geeksforgeeks.org/java-program-to-implement-levenshtein-distance-computing-algorithm/
     * The algorithm was first published by Vladimir Levenshtein in 1965.
     * @param s1 first string
     * @param s2 second string
     * @return a value 0 to max{s1.length, s2.length}
     */
    private static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int insertion = dp[i][j - 1] + 1;
                    int deletion = dp[i - 1][j] + 1;
                    int substitution = dp[i - 1][j - 1];
                    if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                        substitution++;
                    }
                    dp[i][j] = Math.min(insertion, Math.min(deletion, substitution));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
