package kakomon3.jdo;

public enum Sentaku {

	a("ア", 1), i("イ", 2), u("ウ", 3), e("エ", 4);

	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}

	/**
	 * @param no
	 *            the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}

	private String name;
	private int no;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Sentaku(String name, int no) {
		setName(name);
		setNo(no);
	}

	public String toString() {
		return this.name;
	}

	public static Sentaku get(int i) {
		Sentaku[] kaitoes = Sentaku.values();

		for (Sentaku k : kaitoes) {
			if (i == k.getNo())
				return k;
		}
		return null;
	}

}