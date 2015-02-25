package com.fisei.visitapp.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by diegoztc on 19/02/15.
 */
@DatabaseTable(tableName ="test")
public class Test {
    @DatabaseField(columnName = "pk",generatedId = true,allowGeneratedIdInsert = true)
    private int pk=0;
    @DatabaseField(defaultValue ="123" , canBeNull = true)
    private String value;

    @DatabaseField
    private String imageuri="NN";


    public Test(){}


    public Test(int pk,String value) {
        this.pk = pk;
        this.value=value;

    }

    public Test(int pk,String value,String imageuri) {
        this.pk = pk;
        this.value=value;
        this.imageuri = imageuri;
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

    public String getImage() {
        return imageuri;
    }

    public void setImage(String imageUri) {
        this.imageuri = imageUri;
    }

    @Override
    public String toString() {
        return pk+" / "+value+" / "+imageuri;
    }
}
