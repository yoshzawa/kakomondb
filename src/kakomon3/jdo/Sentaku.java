package kakomon3.jdo;

import java.util.ArrayList;

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
		for (Sentaku k : values()) {
			if (i == k.getNo())
				return k;
		}
		return null;
	}

	public static ArrayList<String[]> getStringList() {
		ArrayList<String[]> kotaeList = new ArrayList<String[]>();
		for (Sentaku s : values()) {
			String[] data = new String[2];
			data[0] = s.getNo() + "";
			data[1] = s.toString();
			kotaeList.add(data);
		}
		return kotaeList;
	}
}