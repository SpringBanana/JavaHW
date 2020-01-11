import java.util.Scanner;

class LongestPalinSubstring {
    public static int longestPalindromeSubseq(String s) {
        int maxl = 0;
        int n = s.length();
        int table[] = new int[26];
        for (int i = 0; i < n; i++) {

        }
        return maxl;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        System.out.println(longestPalindromeSubseq(inp));
    }
}