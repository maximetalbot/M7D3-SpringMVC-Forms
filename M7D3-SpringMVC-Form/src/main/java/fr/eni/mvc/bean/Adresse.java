package fr.eni.mvc.bean;

public class Adresse {

	private String codePostal;
	private String ville;
	
	public Adresse() {
		this("","");
	}

	public Adresse(String codePostal, String ville) {
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Adresse [codePostal=" + codePostal + ", ville=" + ville + "]";
	}

}
