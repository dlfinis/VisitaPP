package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 20/02/15.
 */
@DatabaseTable(tableName = "vw_estudiante_pasantias_informacion ")
public class PasantiaPracticas {

    @DatabaseField(id=true,generatedId = false,columnName = "CodPractica")
    private int CodPractica;

    @DatabaseField(columnName = "CodPasantia")
    private int CodPasantia;

    @DatabaseField(columnName = "CodEst")
    private int CodEst;

    @DatabaseField
    private String CodResponsable;

    @DatabaseField
    private String CCResponsable;

    @DatabaseField
    private String Entidad;


    @DatabaseField
    private int HorasPracticas;

    @DatabaseField
    private String Estado;


    @DatabaseField
    private String FechaInicio;

    @DatabaseField
    private String FechaFin;

    public int getCodPractica() {
        return CodPractica;
    }

    public void setCodPractica(int codPractica) {
        CodPractica = codPractica;
    }

    public int getCodPasantia() {
        return CodPasantia;
    }

    public void setCodPasantia(int codPasantia) {
        CodPasantia = codPasantia;
    }

    public int getCodEst() {
        return CodEst;
    }

    public void setCodEst(int codEst) {
        CodEst = codEst;
    }

    public String getCodResponsable() {
        return CodResponsable;
    }

    public void setCodResponsable(String codResponsable) {
        CodResponsable = codResponsable;
    }

    public String getCCResponsable() {
        return CCResponsable;
    }

    public void setCCResponsable(String CCResponsable) {
        this.CCResponsable = CCResponsable;
    }

    public String getEntidad() {
        return Entidad;
    }

    public void setEntidad(String entidad) {
        Entidad = entidad;
    }

    public int getHorasPracticas() {
        return HorasPracticas;
    }

    public void setHorasPracticas(int horasPracticas) {
        HorasPracticas = horasPracticas;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
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
                "CodPractica=" + CodPractica +
                ", CodPasantia=" + CodPasantia +
                ", CodEst=" + CodEst +
                ", CodResponsable='" + CodResponsable + '\'' +
                ", CCResponsable='" + CCResponsable + '\'' +
                ", Entidad='" + Entidad + '\'' +
                ", HorasPracticas=" + HorasPracticas +
                ", Estado='" + Estado + '\'' +
                ", FechaInicio='" + FechaInicio + '\'' +
                ", FechaFin='" + FechaFin + '\'' +
                '}';
    }
}
