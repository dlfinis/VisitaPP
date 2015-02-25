package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 20/02/15.
 */
@DatabaseTable(tableName = "pasantia_practica")
public class PasantiaPracticas {

    @DatabaseField(id=true,generatedId = false,columnName = "CodPasantia")
    private int CodPasantia;

    @DatabaseField
    private int CodPractica;

    @DatabaseField
    private int NumHoras;

    @DatabaseField
    private String Entidad;

    @DatabaseField
    private String FechaInicio;

    @DatabaseField
    private String FechaFin;


    public int getCodPasantia() {
        return CodPasantia;
    }

    public void setCodPasantia(int codPasantia) {
        CodPasantia = codPasantia;
    }

    public int getCodPractica() {
        return CodPractica;
    }

    public void setCodPractica(int codPractica) {
        CodPractica = codPractica;
    }

    public int getNumHoras() {
        return NumHoras;
    }

    public void setNumHoras(int numHoras) {
        NumHoras = numHoras;
    }

    public String getEntidad() {
        return Entidad;
    }

    public void setEntidad(String entidad) {
        Entidad = entidad;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "PasantiaPracticas{" +
                "CodPasantia=" + CodPasantia +
                ", CodPractica=" + CodPractica +
                ", NumHoras=" + NumHoras +
                ", Entidad='" + Entidad + '\'' +
                ", FechaInicio='" + FechaInicio + '\'' +
                ", FechaFin='" + FechaFin + '\'' +
                '}';
    }
}
