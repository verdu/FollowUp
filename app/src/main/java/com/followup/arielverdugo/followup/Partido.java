package com.followup.arielverdugo.followup;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.query.In;

import java.sql.Date;

/**
 * Created by arielverdugo on 6/2/18.
 */

public class Partido {

    @DatabaseField(generatedId = true)
    //private int id;
    public int id;

    @DatabaseField
    private Date fecha;

    @DatabaseField
    private String rival;

    @DatabaseField
    private Integer minutos;

    @DatabaseField
    private Integer puntos;

    @DatabaseField
    private Integer goles_campo_hechos;

    @DatabaseField
    private Integer goles_campo_intentados;

    @DatabaseField
    private Float porcentaje_goles_campo;

    @DatabaseField
    private Integer triples_hechos;

    @DatabaseField
    private Integer triples_intentados;

    @DatabaseField
    private Float porcentaje_triples;

    @DatabaseField
    private Integer libres_hechos;

    @DatabaseField
    private Integer libres_intentados;

    @DatabaseField
    private Float porcentaje_libres;

    @DatabaseField
    private Integer rebotes;

    @DatabaseField
    private Integer rebotes_defensivos;

    @DatabaseField
    private Integer rebotes_ofensivos;

    @DatabaseField
    private Integer asistencias;

    @DatabaseField
    private Integer robos;

    @DatabaseField
    private Integer bloqueos;

    @DatabaseField
    private Integer perdidas;

    @DatabaseField
    private Integer valoracion;

    public Partido(){}



    public Partido(Date fecha, String rival, Integer minutos, Integer puntos, Integer goles_campo_hechos, Integer goles_campo_intentados,
                   Float porcentaje_goles_campo, Integer triples_hechos, Integer triples_intentados, Float porcentaje_triples,
                   Integer libres_hechos, Integer libres_intentados, Float porcentaje_libres, Integer rebotes, Integer rebotes_defensivos,
                   Integer rebotes_ofensivos, Integer asistencias, Integer robos, Integer bloqueos, Integer perdidas, Integer valoracion)
    {
        this.fecha = fecha;
        this.rival = rival;
        this.minutos = minutos;
        this.puntos = puntos;
        this.goles_campo_hechos = goles_campo_hechos;
        this.goles_campo_intentados = goles_campo_intentados;
        this.porcentaje_goles_campo = porcentaje_goles_campo;
        this.triples_hechos = triples_hechos;
        this.triples_intentados = triples_intentados;
        this.porcentaje_triples = porcentaje_triples;
        this.libres_hechos = libres_hechos;
        this.libres_intentados = libres_intentados;
        this.porcentaje_libres = porcentaje_libres;
        this.rebotes = rebotes;
        this.rebotes_defensivos = rebotes_defensivos;
        this.rebotes_ofensivos = rebotes_ofensivos;
        this.asistencias = asistencias;
        this.robos = robos;
        this.bloqueos = bloqueos;
        this.perdidas = perdidas;
        this.valoracion = valoracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRival() {
        return rival;
    }

    public void setRival(String rival) {
        this.rival = rival;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getGoles_campo_hechos() {
        return goles_campo_hechos;
    }

    public void setGoles_campo_hechos(Integer goles_campo_hechos) {
        this.goles_campo_hechos = goles_campo_hechos;
    }

    public Integer getGoles_campo_intentados() {
        return goles_campo_intentados;
    }

    public void setGoles_campo_intentados(Integer goles_campo_intentados) {
        this.goles_campo_intentados = goles_campo_intentados;
    }

    public Float getPorcentaje_goles_campo() {
        return porcentaje_goles_campo;
    }

    public void setPorcentaje_goles_campo(Float porcentaje_goles_campo) {
        this.porcentaje_goles_campo = porcentaje_goles_campo;
    }

    public Integer getTriples_hechos() {
        return triples_hechos;
    }

    public void setTriples_hechos(Integer triples_hechos) {
        this.triples_hechos = triples_hechos;
    }

    public Integer getTriples_intentados() {
        return triples_intentados;
    }

    public void setTriples_intentados(Integer triples_intentados) {
        this.triples_intentados = triples_intentados;
    }

    public Float getPorcentaje_triples() {
        return porcentaje_triples;
    }

    public void setPorcentaje_triples(Float porcentaje_triples) {
        this.porcentaje_triples = porcentaje_triples;
    }

    public Integer getLibres_hechos() {
        return libres_hechos;
    }

    public void setLibres_hechos(Integer libres_hechos) {
        this.libres_hechos = libres_hechos;
    }

    public Integer getLibres_intentados() {
        return libres_intentados;
    }

    public void setLibres_intentados(Integer libres_intentados) {
        this.libres_intentados = libres_intentados;
    }

    public Float getPorcentaje_libres() {
        return porcentaje_libres;
    }

    public void setPorcentaje_libres(Float porcentaje_libres) {
        this.porcentaje_libres = porcentaje_libres;
    }

    public Integer getRebotes() {
        return rebotes;
    }

    public void setRebotes(Integer rebotes) {
        this.rebotes = rebotes;
    }

    public Integer getRebotes_defensivos() {
        return rebotes_defensivos;
    }

    public void setRebotes_defensivos(Integer rebotes_defensivos) {
        this.rebotes_defensivos = rebotes_defensivos;
    }

    public Integer getRebotes_ofensivos() {
        return rebotes_ofensivos;
    }

    public void setRebotes_ofensivos(Integer rebotes_ofensivos) {
        this.rebotes_ofensivos = rebotes_ofensivos;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public Integer getRobos() {
        return robos;
    }

    public void setRobos(Integer robos) {
        this.robos = robos;
    }

    public Integer getBloqueos() {
        return bloqueos;
    }

    public void setBloqueos(Integer bloqueos) {
        this.bloqueos = bloqueos;
    }

    public Integer getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(Integer perdidas) {
        this.perdidas = perdidas;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
