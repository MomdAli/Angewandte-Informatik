package bibliothek;

public interface Taetigkeit {
    /**
     * Gibt die Zeit in Sekunden zur&uuml;ck.
     * @return Die Zeit in Sekunden
     */
    public double getTime();

    /**
     * Fuegt eine neue Taetigkeit hinzu.
     * @param tk Die neue Taetigkeit
     */
    public void add(Taetigkeit tk);

    /**
     * Entfernt eine Taetigkeit.
     * @param tk Die zu entfernende Taetigkeit
     */
    public void remove(Taetigkeit tk);

    /**
     * Gibt die Anzahl der gespeicherten Taetigkeiten
     * @return Die Anzahl
     */
    public int getAnzahl();
}
