package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 19/02/15.
 */
@DatabaseTable(tableName ="vw_estudiante_informacion")
public class EstudianteInformacion {

    @DatabaseField(id=true,generatedId = false)
    private int CodEstudiante;

    @DatabaseField(columnName = "CCEstudiante")
    private String CCEstudiante;

    @DatabaseField
    private String Nombres;

    @DatabaseField
    private String Apellidos;

    @DatabaseField
    private String Sexo;

    @DatabaseField
    private String Movil;

    @DatabaseField
    private String Convencional;

    @DatabaseField
    private String Email;

    @DatabaseField
    private String Carrera;

    @DatabaseField
    private int HorasPasantias;

    @DatabaseField
    private int NumCreditos;

    @DatabaseField
    private int NumPracticas;

    @DatabaseField
    private int CodPasantia;

    @DatabaseField
    private int CodResponsable;

    @DatabaseField(columnName = "CCResponsable")
    private String CCResponsable;

    public EstudianteInformacion() {
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

    public String getNombres() {
        return Nombres +" "+ Apellidos;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getConvencional() {
        return Convencional;
    }

    public void setConvencional(String convencional) {
        Convencional = convencional;
    }

    public String getMovil() {
        return Movil;
    }

    public void setMovil(String movil) {
        Movil = movil;
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

    public int getHorasPasantias() {
        return HorasPasantias;
    }

    public void setHorasPasantias(int horasPasantias) {
        HorasPasantias = horasPasantias;
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

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
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

    @Override
    public String toString() {
        return "EstudianteInformacion{" +
                "CodEstudiante=" + CodEstudiante +
                ", CCEstudiante='" + CCEstudiante + '\'' +
                ", Nombres='" + Nombres + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Sexo='" + Sexo + '\'' +
                ", Convencional='" + Convencional + '\'' +
                ", Movil='" + Movil + '\'' +
                ", Email='" + Email + '\'' +
                ", Carrera='" + Carrera + '\'' +
                ", HorasPasantias=" + HorasPasantias +
                ", NumCreditos=" + NumCreditos +
                ", NumPracticas=" + NumPracticas +
                ", CodPasantia=" + CodPasantia +
                ", CodResponsable=" + CodResponsable +
                ", CCResponsable='" + CCResponsable + '\'' +
                '}';
    }
}
