package dictionary;

import java.util.Scanner;

public class Regex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Regulärer Ausdruck eingeben: ");
        String regex = scanner.nextLine();

        System.out.print("Eingabe eingeben: ");
        String input = scanner.nextLine();

        System.out.println(input.matches(regex) ? "True" : "False");

        scanner.close();
    }
}
