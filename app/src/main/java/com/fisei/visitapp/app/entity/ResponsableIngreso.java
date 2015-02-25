package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 20/02/15.
 */
@DatabaseTable(tableName ="responsable_ingreso")
public class ResponsableIngreso {

    @DatabaseField(id=true,generatedId = false,columnName = "CodResponsable")
    private int CodResponsable;

    @DatabaseField(columnName = "Cedula")
    private String Cedula;

    @DatabaseField(columnName = "Nombre")
    private String Nombre;

    @DatabaseField(columnName = "Apellidos")
    private String Apellidos;

    @DatabaseField(columnName = "Clave")
    private String Clave;

    public int getCodResponsable() {
        return CodResponsable;
    }

    public void setCodResponsable(int codResponsable) {
        CodResponsable = codResponsable;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    @Override
    public String toString() {
        return "ResponsableIngreso{" +
                "CodResponsable=" + CodResponsable +
                ", Cedula='" + Cedula + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Clave='" + Clave + '\'' +
                '}';
    }
}
