package co.edu.materia.dominio;

public class Materia {
	private String idMateria;
	private String nmateria;
	private int creditos;
  
	
	public Materia () {
		
	}
	public Materia (String idMateria, String materia, int creditos) {
		
		super();
		this.idMateria = idMateria;
		this.nmateria =  materia;
		this.creditos= creditos;
	}
	public String getIdMaterias() {
		return idMateria;
	}
	public void setIdMaterias(String idMateria) {
		this.idMateria = idMateria;
	}
	public String getMateria() {
		return nmateria;
	}
	public void setMateria(String materia) {
		this.nmateria = materia;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
}
