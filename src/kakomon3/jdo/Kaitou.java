package kakomon3.jdo;

public enum Kaitou  {
	
	a("�A",1),
	i("�C",2),
	u("�E",3),
	e("�G",4);

	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}
	/**
	 * @param no the no to set
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

	private Kaitou(String name,int no) {
		setName(name);
		setNo(no);
	}
	public String toString(){
		return this.name;
	}
	public Kaitou get(int i){
		Kaitou[] kaitoes = Kaitou.values();
		
		for(Kaitou k : kaitoes){
			if(i == k.getNo())
				return k;
		}
		return null;
	}

}