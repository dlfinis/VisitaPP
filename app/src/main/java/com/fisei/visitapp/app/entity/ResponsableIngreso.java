package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 20/02/15.
 */
@DatabaseTable(tableName ="vw_responsable_ingreso")
public class ResponsableIngreso {

    @DatabaseField(id=true,generatedId = false,columnName = "CodResponsable")
    private int CodResponsable;

    @DatabaseField(columnName = "CCResponsable")
    private String CCResponsable;

    @DatabaseField(columnName = "Nombres")
    private String Nombres;

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

    public String getCCResponsable() {
        return CCResponsable;
    }

    public void setCCResponsable(String CCResponsable) {
        this.CCResponsable = CCResponsable;
    }

    public String getNombres() {
        return Nombres;
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
                ", CCResponsable='" + CCResponsable + '\'' +
                ", Nombres='" + Nombres + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Clave='" + Clave + '\'' +
                '}';
    }
}
