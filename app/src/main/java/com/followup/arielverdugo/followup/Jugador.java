package com.followup.arielverdugo.followup;

import android.accounts.Account;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by arielverdugo on 7/6/17.
 */

@DatabaseTable(tableName = "jugador")
public class Jugador {

    @DatabaseField(generatedId = true)
    //private int id;
    public int id;


    @DatabaseField
    private String nombre;

    @DatabaseField
    private String apellido;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Equipo equipo;


    @DatabaseField
    private String posicion;

    @DatabaseField
    private int altura;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] foto;

    private Boolean favorito;


    public Jugador(){}


    public Jugador(String nombre, String apellido, Equipo equipo, String posicion, int altura, byte[] foto)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.equipo = equipo;
        this.posicion = posicion;
        this.altura = altura;
        this.foto = foto;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public byte[] getFoto()
    {
        return foto;
    }

    public void setFoto(byte[]foto) {
        this.foto = foto;
    }

    public int getId()
    {
        return id;
    }

    public Boolean isFavorito()
    {
        return favorito;
    }

    public void setFavorito(Boolean favorito)
    {
        this.favorito = favorito;
    }

}
