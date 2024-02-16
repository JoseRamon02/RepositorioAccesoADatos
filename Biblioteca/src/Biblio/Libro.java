package Biblio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "titulo", "codigo", "autor", "nota", "lectores" })
public class Libro {

    // Atributos
    private String titulo;
    private int codigo;
    private String autor;
    private double nota;
    private int[] lectores;

    // Constructor vacío
    public Libro() {
    }

    // Constructor con atributos
    public Libro(String titulo, int codigo, String autor, double nota, int[] lectores) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.autor = autor;
        this.nota = nota;
        this.lectores = lectores;
    }

    // Métodos (Getter y Setter)
    @XmlElement
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @XmlElement
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @XmlElement
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @XmlElement
    public int[] getLectores() {
        return lectores;
    }

    public void setLectores(int[] lectores) {
        this.lectores = lectores;
    }

    // Método toString
    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", codigo=" + codigo + ", autor=" + autor + ", nota=" + nota + "]";
    }

}
