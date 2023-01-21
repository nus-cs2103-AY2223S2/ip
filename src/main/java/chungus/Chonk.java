package chungus;

class Chonk {
    public static String chonkify(String s) {
        return s.length() + " " + s;
    }

    /**
     * Tries to identify a chonk starting at some index of a string.
     */
    public static Pair<String, Integer> dechonkify(String s, int idx) {
        int chonkLen = 0;
        for (; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (c == ' ') {
                idx++;
                break;
            }
            if (c < '0' || c > '9') {
                return null;
            }
            chonkLen *= 10;
            chonkLen += c - '0';
        }
        if (idx + chonkLen > s.length()) {
            return null;
        }
        String v = s.substring(idx, idx + chonkLen);
        return new Pair<>(v, idx + chonkLen);
    }
}

class Pair<S, T> {
    private S first;
    private T second;

    public Pair(S _first, T _second) {
        first = _first;
        second = _second;
    }

    public S first() {
        return first;
    }

    public T second() {
        return second;
    }
}
