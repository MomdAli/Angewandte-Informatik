/*
 * class Evaluator
 *
 * Ihr Name: Mohammed Ali Al-Saiaf
 * Datum: 15.04.2024
 */
package expressionevaluation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Scanner;
import static expressionevaluation.Tokenizer.*;

/**
 * Klasse zum Auswerten von arithmetischen Ausdrücken.
 */
public class Evaluator {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static Object[] stack = new Object[10];		// Stack
    private static int top = -1;					// Index des obersten Kellerelements
    private static Object token;					// Aktuelles Token
    private static Tokenizer tokenizer;				// Zerlegt String-Eingabe in Tokens

    /**
     * Wertet expr als arithmetischen Ausdruck aus.
     *
     * @param expr Arthmetischer Ausdruck als String
     * @return Wert des Ausdrucks oder null, falls der Ausdruck fehlerhaft ist.
     */
    public static Double eval(String expr) {
        if (expr.isEmpty())
            return 0.0;

        // Dollar in leeren Stack ablegen:
        top = -1;
        stack[++top] = DOLLAR;

        // expr in Tokens zerlegen und erstes Token abholen:
        tokenizer = new Tokenizer(expr);
        token = tokenizer.nextToken();

        while (token != null) {
            if (isOp(stack[top]) && isOp(token)) {
                System.err.println("Operator Error!");
                return null;
            }

            if (shift())
                continue;
            if (reduce())
                continue;
            if (accept())
                return (Double) stack[top];

            token = tokenizer.nextToken();
        }
        return null;
    }

    private static boolean shift() {
        if ((stack[top] == DOLLAR || isOp(stack[top]) || stack[top] == KL_AUF)
            && (token == KL_AUF || isVal(token))) {		// Regel 1,2 und 3 der Parser-Tabelle
            doShift();
            return true;
        } else if (isVal(stack[top]) && isOp(token)
                && (stack[top - 1] == DOLLAR || stack[top - 1] == KL_AUF)) {  // Regel 6 der Parser-Tabelle
            doShift();
            return true;
        } else if (isVal(stack[top]) && (isOp(token) || token == KL_ZU)
        && (stack[top - 1] == DOLLAR || stack[top - 1] == KL_AUF)) {  // Regel 7 der Parser-Tabelle
            doShift();
            return true;
        }

        return false;
    }

    private static void doShift() {
        if (top >= stack.length - 1)
            stack = Arrays.copyOf(stack, stack.length * 2);

        stack[++top] = token;
        token = tokenizer.nextToken();
    }

    private static boolean isOp(Object o) {
        return (o == PLUS || o == MULT || o == POWER || o == DIV);
    }

    private static boolean isVal(Object o) {
        return o instanceof Double;
    }

    private static boolean reduce() {
        if (top >= 2 && stack[top - 2] == KL_AUF && isVal(stack[top - 1]) && stack[top] == KL_ZU
            && (token == KL_ZU || isOp(token) || token == DOLLAR)) { // Regel 4 der Parser-Tabelle

            doReduceKlValKl();
            return true;
        } else if (top >= 2 && isVal(stack[top - 2]) && isOp(stack[top - 1]) && isVal(stack[top])
            && (token == KL_ZU || token == DOLLAR)) {   // Regel 8 der Parser-Tabelle

            doReduceValOpVal();
            return true;
        } else if (top >= 2 && isVal(stack[top - 2]) && isOp(stack[top - 1]) && isVal(stack[top])
            && isOp(token)) {   // Regel 9 der Parser-Tabelle

            if (checkPriority(stack[top - 1].toString(), token.toString()))
                doReduceValOpVal();
            else
                doShift();

            return true;
        }

        return false;
    }

    public static boolean checkPriority(String op1, String op2) {
        return (op1 == MULT && op2 == PLUS)
            || (op1 == POWER && op2 == MULT)
            || (op1 == POWER && op2 == PLUS)
            || (op1 == MULT && op2 == DIV)
            || (op1 == POWER && op2 == DIV)
            || (op1 == DIV && op2 == PLUS);
    }

    private static void doReduceKlValKl() {
        stack[top - 2] = stack[top - 1];
        stack[top - 1] = null;
        stack[top] = null;

        top -= 2;
    }

    private static void doReduceValOpVal() {
        BigDecimal zahl1 = new BigDecimal(stack[top - 2].toString());
        BigDecimal zahl2 = new BigDecimal(stack[top].toString());

        String op = stack[top - 1].toString();

        switch (op) {
            case PLUS:
                stack[top - 2] = zahl1.add(zahl2).doubleValue();
                break;
            case MULT:
                stack[top - 2] = zahl1.multiply(zahl2).doubleValue();
                break;
            case DIV:
                stack[top - 2] = zahl1.divide(zahl2, new MathContext(16)).doubleValue();
                break;
            case POWER:
                stack[top - 2] = Math.pow(Double.parseDouble(stack[top-2].toString()),
                        Double.parseDouble(stack[top].toString()));
                break;
            default:
                System.err.println("FEHLER 02");
                break;
        }

        top -= 2;
    }

    private static boolean accept() {
        if (token == DOLLAR && stack[top - 1] == DOLLAR && isVal(stack[top])) {
            return true;
        }

        return false;
    }

    /**
     * Liest von der Konsole eine Folge von Zeilen, wertet jede Zeile als
     * Ausdruck aus und gibt seinen Wert aus. (repl = read-evaluate-print-loop).
     */
    public static void repl() {
        Scanner in = new Scanner(System.in);
        System.out.print(ANSI_BLUE + ">> ");

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.toLowerCase().equals("end")) {
                System.out.println("\033[0m" + "Bye!\n");
                break;
            }

            Double t = eval(line);

            System.out.println(t == null ? "" : t);
            System.out.print(ANSI_BLUE + ">> ");
        }
        in.close();
    }

    /**
     * Testprogramm.
     *
     * @param args wird nicht benutzt.
     */
    public static void main(String[] args) {
        // Zum Testen, später auskommentieren:

        System.out.println(ANSI_BLUE);

  		String s1 = "2*2*(2+3)+2";
		String s2 = "2^10+5";
		String s3 = "5+2^10";
        String s4 = "(2+3*4+4)^2";
        String s5 = "(2+3*4+4))*2";
        String s6 = "2+3**4";
        String s7 = "2^3^2";
        String s8 = "2^2*5";
        String s9 = "1+(2+(3+(4+(5+6))))";
        String s10 = "1+2+3+4+5+6";

		System.out.println(eval(s1));	// 3.0
		System.out.println(eval(s2));	// 1029.0
        System.out.println(eval(s3));	// 1029.0
        System.out.println(eval(s4));	// 324.0
        System.out.println(eval(s5));	// null; Syntaxfehler
        System.out.println(eval(s6));	// null; Syntaxfehler
        System.out.println(eval(s7));	// 512.0
        System.out.println(eval(s8));	// 20.0
        System.out.println(eval(s9));	// 21.0
        System.out.println(eval(s10));	// 21.0

        repl();
    }
}