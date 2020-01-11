/*
 * Fast Scanner
 */

package classes;

import java.io.*;
import java.util.InputMismatchException;

public class Scanner {

    private static final  String encoding = "utf-8";
    
    private BufferedReader br;
    private int pos;
    private int len;
    private boolean EOF = false;
    private boolean isClose = false;
    private final char[] buffer = new char[4096];

    public Scanner(InputStream is) {
        try {
            br = new BufferedReader(new InputStreamReader(is, encoding));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    public Scanner(InputStream is, String enc) {
        try {
            br = new BufferedReader(new InputStreamReader(is, enc));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    public Scanner(String inp) {
        try {
            InputStream is = new FileInputStream(inp);
            br = new BufferedReader(new InputStreamReader(is, encoding));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    public Scanner(String inp, String enc) {
        try {
            InputStream is = new FileInputStream(inp);
            br = new BufferedReader(new InputStreamReader(is, enc));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readBuffer() {
        try {
            len = br.read(buffer);
            while (len == 0) {
                len = br.read(buffer);
            }
            if (len == -1) {
                EOF = true;
            }
            pos = 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public char nextchar() {
        if (pos >= len) {
            readBuffer();
        }
        return buffer[pos++];
    }

    public boolean hasNextChar() {
        nextchar();
        pos--;
        return !EOF;
    }

    public boolean hasNextLine() {
        nextchar();
        pos--;
        return !EOF;
    }

    public boolean hasNextInt() {
        if (Character.isDigit(nextchar())) {
            pos--;
            return true;
        } else {
            pos--;
            return false;
        }
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextchar();
            if (c == ' ' || c == '\n') {
                break;
            }
            if (c != '\r') {
                sb.append(c);
            }
        }
        return sb.toString();   
    }

    public String nextWord() {
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextchar();
            if (Character.getType(c) != Character.DASH_PUNCTUATION && c != '\'' && !Character.isLetter(c)) {
                break;
            }
            if (c != '\r') {
                sb.append(c);
            }
        }
        return sb.toString();     }

    public String nextLine() {
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextchar();
            if (c == '\n') {
                break;
            }
            if (c != '\r') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public int nextInt() {
        skipBlank();
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextchar();
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                if (!Character.isWhitespace(c)) {
                    throw new InputMismatchException("");
                }
                break;
            }
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    private void skipBlank() {
        while (true) {
            if (!hasNextChar() || !Character.isWhitespace(nextchar())) break;
        }
        pos--;
    }

    public void close() {
        try {
            if (!isClose) {
                br.close();
                isClose = true;
            } else {
                System.out.println("Classes.Scanner is already close");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
