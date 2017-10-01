package com.leonus96.joseph.tablistview;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joseph on 11/11/16.
 */

public class Multa {

    private String codigo;
    private String infraccion;
    private String tipo;
    private float pMonto;
    private float conDescuento;
    private String sancion;
    private int puntos;
    private String medidaPreventiva;

    public Multa(int puntos, String infraccion, String tipo, float pMonto, float conDescuento, String sancion, String codigo) {
        this.puntos = puntos;
        this.infraccion = infraccion;
        this.tipo = tipo;
        this.pMonto = pMonto;
        this.conDescuento = conDescuento;
        this.sancion = sancion;
        this.codigo = codigo;
    }

    public Multa(JSONObject jsonObject) throws JSONException {
        this.codigo = jsonObject.getString("codigo");
        this.infraccion = jsonObject.getString("infraccion");
        this.tipo = jsonObject.getString("tipo");
        this.pMonto = Float.parseFloat(jsonObject.getString("pMonto"));
        this.conDescuento = Float.parseFloat(jsonObject.getString("con_descuento"));
        this.sancion = jsonObject.getString("sancion");
        this.puntos = jsonObject.getInt("puntos");
        this.medidaPreventiva = jsonObject.getString("medida-preventiva");
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(String infraccion) {
        this.infraccion = infraccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getpMonto() {
        return pMonto;
    }

    public void setpMonto(float pMonto) {
        this.pMonto = pMonto;
    }

    public float getConDescuento() {
        return conDescuento;
    }

    public void setConDescuento(float conDescuento) {
        this.conDescuento = conDescuento;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String multa) {
        this.sancion = multa;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getMedidaPreventiva() {
        return medidaPreventiva;
    }

    public void setMedidaPreventiva(String medidaPreventiva) {
        this.medidaPreventiva = medidaPreventiva;
    }
}