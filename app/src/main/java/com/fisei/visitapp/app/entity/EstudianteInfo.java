package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 19/02/15.
 */
@DatabaseTable(tableName ="estudiante_info")
public class EstudianteInfo {

    @DatabaseField(id=true,generatedId = false)
    private int CodEstudiante;

    @DatabaseField(columnName = "CC")
    private String CC;

    @DatabaseField
    private String Nombre;

    @DatabaseField
    private String Apellido;

    @DatabaseField
    private String Celular;

    @DatabaseField
    private String Email;

    @DatabaseField
    private String Carrera;

    @DatabaseField
    private int NumHoras;

    @DatabaseField
    private int NumCreditos;

    @DatabaseField
    private int NumPracticas;

    @DatabaseField
    private int CodPasantia;


    public EstudianteInfo() {
    }

    public int getCodEstudiante() {
        return CodEstudiante;
    }

    public void setCodEstudiante(int codEstudiante) {
        CodEstudiante = codEstudiante;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getNombre() {
        return Nombre+" "+Apellido;
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

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String carrera) {
        Carrera = carrera;
    }

    public int getNumHoras() {
        return NumHoras;
    }

    public void setNumHoras(int numHoras) {
        NumHoras = numHoras;
    }

    public int getNumCreditos() {
        return NumCreditos;
    }

    public void setNumCreditos(int numCreditos) {
        NumCreditos = numCreditos;
    }

    public int getCodPasantia() {
        return CodPasantia;
    }

    public void setCodPasantia(int codPasantia) {
        CodPasantia = codPasantia;
    }

    public int getNumPracticas() {
        return NumPracticas;
    }

    public void setNumPracticas(int numPracticas) {
        NumPracticas = numPracticas;
    }

    @Override
    public String toString() {
        return "EstudianteInfo{" +
                "CodEstudiante=" + CodEstudiante +
                ", CC='" + CC + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Celular='" + Celular + '\'' +
                ", Email='" + Email + '\'' +
                ", Carrera='" + Carrera + '\'' +
                ", NumHoras=" + NumHoras +
                ", NumCreditos=" + NumCreditos +
                ", NumPracticas=" + NumPracticas +
                ", CodPasantia=" + CodPasantia +
                '}';
    }
}
