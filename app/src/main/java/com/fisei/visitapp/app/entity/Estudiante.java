package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 19/02/15.
 */
@DatabaseTable(tableName ="lista_estudiantes")
public class Estudiante {

    @DatabaseField(id=true,generatedId = false)
    private int CodEstudiante;

    @DatabaseField(columnName = "CCEstudiante")
    private String CCEstudiante;

    @DatabaseField
    private int CodResponsable;

    @DatabaseField
    private String CCResponsable;

    @DatabaseField
    private String Nombre;

    @DatabaseField
    private String Apellido;

    public Estudiante() {
    }

    public int getCodEstudiante() {
        return CodEstudiante;
    }

    public void setCodEstudiante(int codEstudiante) {
        CodEstudiante = codEstudiante;
    }

    public String getCCEstudiante() {
        return CCEstudiante;
    }

    public void setCCEstudiante(String CCEstudiante) {
        this.CCEstudiante = CCEstudiante;
    }

    public int getCodResponsable() {
        return CodResponsable;
    }

    public void setCodResponsable(int codResponsable) {
        CodResponsable = codResponsable;
    }

    public String getCCResponsable() {
        return CCResponsable;
    }

    public void setCCResponsable(String CCResponsable) {
        this.CCResponsable = CCResponsable;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "CodEstudiante=" + CodEstudiante +
                ", CCEstudiante='" + CCEstudiante + '\'' +
                ", CodResponsable=" + CodResponsable +
                ", CCResponsable='" + CCResponsable + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                '}';
    }
}
