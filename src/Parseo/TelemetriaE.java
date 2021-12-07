/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parseo;

public class TelemetriaE extends TelemetriaB {

    int ignicion;
    int ext_pw;
    int aceleracion;
    int idle;
    int vo;
    String IMEI = null;

    public TelemetriaE(String cadena) throws Exception {
        super(cadena);
        String[] cadenaEx = generaExtension(cadena.substring(41));
        for (String cadenaEx1 : cadenaEx) {
            generarDatos(cadenaEx1);
        }
        rellenarDatos();
    }

    private String[] generaExtension(String cadena) {
        String[] _datos = cadena.split(">");
        String[] datos = _datos[0].split(";");

        return datos;
    }

    private void generarDatos(String cadena) throws Exception {
        if (cadena.contains("IO")) {
            switch (cadena.charAt(3)) {
                case '0':
                    ignicion = 0;
                    ext_pw = 0;
                    break;
                case '1':
                    ignicion = 1;
                    ext_pw = 0;
                    break;
                case '2':
                    //10
                    ignicion = 0;
                    ext_pw = 1;
                    break;
                case '3':
                    //11
                    ignicion = 1;
                    ext_pw = 1;
                    break;
            }

        } else if (cadena.contains("AC")) {
            aceleracion = Integer.parseInt(cadena.substring(3));
        } else if (cadena.contains("CL")) {
            idle = Integer.parseInt(cadena.substring(3));
        } else if (cadena.contains("VO")) {
            vo = Integer.parseInt(cadena.substring(3));
        } else if (cadena.contains("ID")) {
            IMEI = cadena.substring(3, cadena.length() - 1);
        }
    }

    public String showTelemetriaE() {
        return showTelemetriaB() + "\n"
                + "IGNICION: " + ignicion + "\n"
                + "FUENTE DE ALIMENTACION: " + ext_pw + "\n"
                + "ACELERACION: " + aceleracion + "\n"
                + "CONTADOR DE IDLE: " + idle + "\n"
                + "ODOMETRO: " + vo + "\n"
                + "IMEI: " + IMEI + "\n";

    }

    private void rellenarDatos() {
        if (IMEI == null) {
            IMEI = "N/A";
        }
    }
   //T
}
