package aiss.model;

import java.util.Comparator;

public class Card implements Comparable<Card>{

	private String id;
	private String name;
	private String introduction;
	private String image;
	private String qr;

	public Card() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	@Override
	public int compareTo(Card o) {
		
		return name.compareTo(o.name);
	}


}