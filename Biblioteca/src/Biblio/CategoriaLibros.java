package Biblio;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class CategoriaLibros {
    
    // Attributes for different book categories
    private List<Libro> librosLiteratura = new ArrayList<>();
    private List<Libro> librosConsultayReferencia = new ArrayList<>();
    private List<Libro> librosInfantiles = new ArrayList<>();
    private List<Libro> librosDivulgativos = new ArrayList<>();
    private List<Libro> librosTecnicos = new ArrayList<>();
    
    // Getters and setters for book categories
    @XmlElement(name = "librosLiteratura")
    public List<Libro> getLibrosLiteratura() {
        return librosLiteratura;
    }

    public void setLibrosLiteratura(List<Libro> librosLiteratura) {
        this.librosLiteratura = librosLiteratura;
    }

    @XmlElement(name = "librosConsultayReferencia")
    public List<Libro> getLibrosConsultayReferencia() {
        return librosConsultayReferencia;
    }

    public void setLibrosConsultayReferencia(List<Libro> librosConsultayReferencia) {
        this.librosConsultayReferencia = librosConsultayReferencia;
    }

    @XmlElement(name = "librosInfantiles")
    public List<Libro> getLibrosInfantiles() {
        return librosInfantiles;
    }

    public void setLibrosInfantiles(List<Libro> librosInfantiles) {
        this.librosInfantiles = librosInfantiles;
    }

    @XmlElement(name = "librosDivulgativos")
    public List<Libro> getLibrosDivulgativos() {
        return librosDivulgativos;
    }

    public void setLibrosDivulgativos(List<Libro> librosDivulgativos) {
        this.librosDivulgativos = librosDivulgativos;
    }

    @XmlElement(name = "librosTecnicos")
    public List<Libro> getLibrosTecnicos() {
        return librosTecnicos;
    }

    public void setLibrosTecnicos(List<Libro> librosTecnicos) {
        this.librosTecnicos = librosTecnicos;
    }

    // Constructors
    
    // Constructor with parameters
    public CategoriaLibros(List<Libro> librosLiteratura, List<Libro> librosConsultayReferencia,
            List<Libro> librosInfantiles, List<Libro> librosDivulgativos, List<Libro> librosTecnicos) {
        this.librosLiteratura = librosLiteratura;
        this.librosConsultayReferencia = librosConsultayReferencia;
        this.librosInfantiles = librosInfantiles;
        this.librosDivulgativos = librosDivulgativos;
        this.librosTecnicos = librosTecnicos;
    }
    
    // Default constructor
    public CategoriaLibros() {
        
    }

}
