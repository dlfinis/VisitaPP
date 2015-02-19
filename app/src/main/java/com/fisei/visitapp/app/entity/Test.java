package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by diegoztc on 19/02/15.
 */
@DatabaseTable(tableName ="test")
public class Test implements Serializable{
    @DatabaseField(generatedId = true)
    private int pk;
    @DatabaseField(index=true,canBeNull = true)
    private String value;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] image;


    public Test(){}


    public Test(int pk,String value) {
        this.pk = pk;
        this.value=value;

    }

    public Test(int pk,String value,byte[] imagen) {
        this.pk = pk;
        this.value=value;
        this.image=image;
    }


    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
