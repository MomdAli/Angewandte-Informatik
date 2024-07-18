package aufgabe1;

/**
 * @author Mohammed Ali Al-Saiaf
 */
public abstract class AbstractFrequencyTable implements FrequencyTable {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
    public void add(String w) {
		add(w, 1);
    }

	@Override
	public void addAll(FrequencyTable fq) {
		for (int i = 0; i < fq.size(); i++)
			add(fq.get(i).getWord(), fq.get(i).getFrequency());
	}

	@Override
	public void collectNMostFrequent(int n, FrequencyTable fq) {
		FrequencyTable temp = new ArrayFrequencyTable();
		n = Math.min(n, size());
		for (int i = 0; i < n; i++) {
			temp.add(get(i).getWord(), get(i).getFrequency());
		}

		fq.clear();
		fq.addAll(temp);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");

		for (int i = 0; i < size(); i++) {
			s.append(get(i).toString()).append(", ");
		}

		s.append("} size = ").append(size());

		return s.toString();
	}
}