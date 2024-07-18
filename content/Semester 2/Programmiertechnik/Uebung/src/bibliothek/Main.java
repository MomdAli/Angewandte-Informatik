package bibliothek;

public class Main {
    public static void main(String[] args) {
        Taetigkeit tk1 = new ParalleleTaetigkeit();
        tk1.add(new ElementareTaetigkeit("Linke Seitenwand montieren", 5.0));
        tk1.add(new ElementareTaetigkeit("Rechte Seitenwand montieren", 5.0));

        Taetigkeit tk2 = new ParalleleTaetigkeit();
        tk2.add(new ElementareTaetigkeit("Linke Türe montieren", 7.0));
        tk2.add(new ElementareTaetigkeit("Rechte Türe mit Griff montieren", 9.0));

        Taetigkeit schrankMontage = new SerielleTaetigkeit();
        schrankMontage.add(new ElementareTaetigkeit("Füße an Boden montieren", 6.0));
        schrankMontage.add(tk1);
        schrankMontage.add(new ElementareTaetigkeit("Decke montieren", 8.0));
        schrankMontage.add(tk2);
        System.out.println(schrankMontage.getTime() + " min"); // 28.0 min
        System.out.println(schrankMontage.getAnzahl()); // 6
        System.out.println(schrankMontage); // Test von toString
    }
}
