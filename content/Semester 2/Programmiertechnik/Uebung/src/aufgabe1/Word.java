package aufgabe1;

/**
 * Klasse für Wörter mit ihren Häufigkeiten.
 * @author oliverbittel
 * @since 31.07.2023
 */
public class Word {
	final private String word;
	private int frequency;
	
	/**
	 * Konstruktor.
	 * @param word Wort
	 * @param f H&auml;ufgkeit
	 */
	public Word(String word, int f) {
		this.word = word;
		this.frequency = f;
	}

	/**
	 * Liefert Wort zur&uuml;ck.
	 * @return Wort
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Liefert H&auml;ufgkeit zur&uuml;ck.
	 * @return H&auml;ufgkeit
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * Addiert zur H&auml;ufgkeit f dazu.
	 * @param f H&auml;ufgkeits&auml;nderung.
	 */
	public void addFrequency(int f) {
		frequency += f;
	}

	@Override
	public String toString() {
		return word + ":" + frequency;
	}
}