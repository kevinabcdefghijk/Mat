package co.edu.materia.repositorio;


import co.edu.materia.dominio.Materia;

public interface ItransaccionMateria {
	
	
	public Materia consultar(String idMateria);
	public int ingresa( Materia materia);
    public int actualizar (Materia materia);
	public int eliminar (Materia materia);
}


