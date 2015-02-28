package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Arrays;

/**
 * Created by diegoztc on 25/02/15.
 */
@DatabaseTable(tableName = "vw_visita_practica")
public class VisitaPractica {


    @DatabaseField(id = true,columnName = "CodVisita")
    private int CodVisita;

    @DatabaseField(columnName = "CodPractica")
    private int CodPractica;

    @DatabaseField(columnName = "CCResponsable")
    private String CCResponsable;

    @DatabaseField(defaultValue = "Ninguna")
    private String Observaciones;

    @DatabaseField(defaultValue = "''")
    private String FechaVisita;

    @DatabaseField(defaultValue = "f")
    private boolean Opcion1;
    @DatabaseField(defaultValue = "f")
    private boolean Opcion2;
    @DatabaseField(defaultValue = "f")
    private boolean Opcion3;
    @DatabaseField(defaultValue = "f")
    private boolean Opcion4;
    @DatabaseField(defaultValue = "f")
    private boolean Opcion5;
    @DatabaseField(defaultValue = "f")
    private boolean Opcion6;
    @DatabaseField(defaultValue = "f")
    private boolean Opcion7;
    @DatabaseField(defaultValue = "f")
    private boolean Opcion8;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] Imagen;

    public int getCodVisita() {
        return CodVisita;
    }

    public void setCodVisita(int codVisita) {
        CodVisita = codVisita;
    }

    public int getCodPractica() {
        return CodPractica;
    }

    public void setCodPractica(int codPractica) {
        CodPractica = codPractica;
    }

    public String getCCResponsable() {
        return CCResponsable;
    }

    public void setCCResponsable(String CCResponsable) {
        this.CCResponsable = CCResponsable;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public String getFechaVisita() {
        return FechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        FechaVisita = fechaVisita;
    }

    public boolean isOpcion1() {
        return Opcion1;
    }

    public void setOpcion1(boolean opcion1) {
        Opcion1 = opcion1;
    }

    public boolean isOpcion2() {
        return Opcion2;
    }

    public void setOpcion2(boolean opcion2) {
        Opcion2 = opcion2;
    }

    public boolean isOpcion3() {
        return Opcion3;
    }

    public void setOpcion3(boolean opcion3) {
        Opcion3 = opcion3;
    }

    public boolean isOpcion4() {
        return Opcion4;
    }

    public void setOpcion4(boolean opcion4) {
        Opcion4 = opcion4;
    }

    public boolean isOpcion5() {
        return Opcion5;
    }

    public void setOpcion5(boolean opcion5) {
        Opcion5 = opcion5;
    }

    public boolean isOpcion6() {
        return Opcion6;
    }

    public void setOpcion6(boolean opcion6) {
        Opcion6 = opcion6;
    }

    public boolean isOpcion7() {
        return Opcion7;
    }

    public void setOpcion7(boolean opcion7) {
        Opcion7 = opcion7;
    }

    public boolean isOpcion8() {
        return Opcion8;
    }

    public void setOpcion8(boolean opcion8) {
        Opcion8 = opcion8;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }

    @Override
    public String toString() {
        return "Visita{" +
                "CodVisita=" + CodVisita +
                ", CodPractica=" + CodPractica +
                ", Observaciones='" + Observaciones + '\'' +
                ", FechaVisita='" + FechaVisita + '\'' +
                ", Opcion1=" + Opcion1 +
                ", Opcion2=" + Opcion2 +
                ", Opcion3=" + Opcion3 +
                ", Opcion4=" + Opcion4 +
                ", Opcion5=" + Opcion5 +
                ", Opcion6=" + Opcion6 +
                ", Opcion7=" + Opcion7 +
                ", Opcion8=" + Opcion8 +
                ", Imagen=" + Arrays.toString(Imagen) +
                '}';
    }
}
