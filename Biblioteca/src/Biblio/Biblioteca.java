package Biblio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Biblioteca {
	
	private CategoriaLibros libros;
    
    
    // Métodos (Getter y Setter)
    @XmlElement(name = "libros")
	public CategoriaLibros getLibros() {
		return libros;
	}
	public void setLibros(CategoriaLibros libro) {
		this.libros = libro;
	}
	
	
	// CONSTRUCTORES
	
	// Constructor con atributos	
	public Biblioteca(CategoriaLibros libro) {
		this.libros=libro;
	
	}
	
	// Constructor vacío
	public Biblioteca() {
		
	}
	
    
}