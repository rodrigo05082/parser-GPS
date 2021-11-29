/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parseo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Rodrigo
 */
public class TelemetriaB {

    String indice;
    int codigoEvento;
    Date fechaEvento;
    String diaSemana;
    Date horaEvento;
    char valorLat;
    Double lat;
    char valorLng;
    Double lng;
    float velocidad;
    int orientacion;

    public TelemetriaB(String cadena) throws Exception {//>REV022119483497+2097843-0896715400009312;ID=352557100342545<
        obtenerIndice(cadena.substring(1, 2));
        obtenerCodigoEvento(cadena.substring(4, 6));
        obtenerFechaEvento(cadena.substring(6, 10),obtenerDia(cadena.charAt(10)) );
        obtenerDiaSemana(cadena.charAt(10));
        obtenerHoraEvento(cadena.substring(11, 16));
        obtenerLat(cadena.substring(16, 24));
        obtenerLng(cadena.substring(24, 33));
        obtenerVelocidad(cadena.substring(33, 36));
        obtenerOrientacion(cadena.substring(36, 39));
    }

    private void obtenerIndice(String cadena) throws Exception {

        switch (cadena.charAt(0)) {
            case 'R':
                indice = "Respuesta";
                break;
            case 'Q':
                indice = "Consulta";
                break;
            case 'S':
                indice = "Configuracion";
                break;
            default:
                throw new Exception("Linea invalida-Indice");
        }

    }

    private void obtenerCodigoEvento(String cadena) {
        codigoEvento = Integer.parseInt(cadena);

    }

    private void obtenerFechaEvento(String cadena, int dia) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");//Establezco el formato dia mes anio
        Date fechaInicio = sdf.parse("06-01-1980");//Creo un Date con la fecha de inicio segun el documento.
        Calendar calendar = Calendar.getInstance();//Creo un calendar con la fecha actual
        calendar.setTime(fechaInicio);//Seteo el calendaria actual con el de fecha de inicio ese 1980
        calendar.add(Calendar.DAY_OF_YEAR,( Integer.parseInt(cadena) * 7) + dia);
        //Le agreo dias porque no tiene para agregar semanas entonces dias * 7 
        fechaEvento = calendar.getTime();//Seteo la fecha de inicio con la nueva fecha dodne aumente los dias

    }

    private int obtenerDia(char cadena) {
        int dia = 0;
        switch (cadena) {
            case '0':
                diaSemana = "Domingo";
                dia=0;
                break;
            case '1':
                diaSemana = "Lunes";
                dia=1;
                break;
            case '2':
                diaSemana = "Martes";
                dia=2;
                break;
            case '3':
                diaSemana = "Miercoles";
                dia=3;
                break;
            case '4':
                diaSemana = "Jueves";
                dia=4;
                break;
            case '5':
                diaSemana = "Viernes";
                dia=5;
                break;
            case '6':
                diaSemana = "Sabado";
                dia=6;
                break;

        }
        return dia;
    }

    private void obtenerDiaSemana(char cadena) throws Exception {

        switch (cadena) {
            case '0':
                diaSemana = "Domingo";
                break;
            case '1':
                diaSemana = "Lunes";
                break;
            case '2':
                diaSemana = "Martes";
                break;
            case '3':
                diaSemana = "Miercoles";
                break;
            case '4':
                diaSemana = "Jueves";
                break;
            case '5':
                diaSemana = "Viernes";
                break;
            case '6':
                diaSemana = "Sabado";
                break;
            default:
                throw new Exception("Linea invalida-Indice");
        }

    }

    private void obtenerHoraEvento(String cadena) throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");//Establezco el formato dia mes anio
        //Date fechaInicio = sdf.parse("06-01-1980 00:00:00");//Creo un Date con la fecha de inicio segun el documento.
        Calendar calendar = Calendar.getInstance();//Creo un calendar con la fecha actual
        calendar.setTime(fechaEvento);//Seteo el calendaria actual con el de fecha de inicio ese 1980
        calendar.add(Calendar.SECOND,Integer.parseInt(cadena));
        //Le agreo dias porque no tiene para agregar semanas entonces dias * 7 
        horaEvento = calendar.getTime();//Seteo la fecha de inicio con la nueva fecha dodne aumente los dias
    }

    private String showFechaEvent() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(fechaEvento);
        return fecha;
    }

    private String showHoraEvent() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm",  Locale.UK);
        String hora = sdf.format(horaEvento);
        return hora;
    }

    private void obtenerLat(String cadena) {

        String latitud = cadena.substring(1, 3);
        latitud += "." + cadena.substring(3);
        valorLat = cadena.charAt(0);
        lat = Double.valueOf(latitud);
    }

    private void obtenerLng(String cadena) {
        String longitud = cadena.substring(1, 4);
        longitud += "." + cadena.substring(4);
        valorLng = cadena.charAt(0);
        lng = Double.valueOf(longitud);
    }

    private void obtenerVelocidad(String cadena) {
        velocidad = (float) (Integer.parseInt(cadena) * 1.61);
    }

    private void obtenerOrientacion(String cadena) {
        orientacion = Integer.parseInt(cadena);
    }

    public String showTelemetriaB() {
        return "INDICE MENSAJE: " + indice + "\n"
                + "INDICE EVENTO: " + codigoEvento + "\n"
                + "FECHA EVENTO: " + showFechaEvent() + "\n"
                + "DIA DE LA SEMANA: " + diaSemana + "\n"
                + "HORA DEL REPORTE: " + showHoraEvent() + "\n"
                + "LATITUD: " + valorLat + lat + "\n"
                + "LONGITUD: " + valorLng + lng + "\n"
                + "VELOCIDAD: " + velocidad + " KM/H" + "\n"
                + "ORIENTACION: " + orientacion;
    }

}
