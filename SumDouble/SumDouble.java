/*
 * Print sum of all floats from cmd arguments
 */

public class SumDouble {
    public static void main(String[] args) {
        double result = 0;
        int pointer = 0;
        for (int i = 0; i < args.length; i++) {
            pointer = 0;
            for(int j = 0; j <= args[i].length(); j++) {
                if (j == args[i].length() || Character.isWhitespace(args[i].charAt(j))) {
                    if (pointer > 0) {
                        result += Double.parseDouble(args[i].substring(j - pointer, j));
                    }
                    pointer = 0;
                } else {
                    pointer++;
                }
            }
        }
        System.out.println(result);
    }
}