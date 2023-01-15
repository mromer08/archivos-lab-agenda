package com.mrojas;

import java.util.List;

public class TableGenerator {
    public static final char GUION = '-';
    public static final char BARRA = '|';
    public static final char MAS = '+';
    public static final int ANCHO_PHONE = 15;
    public static final int ANCHO_ID = 5;
    public String tableToString(List<Contacto> contactos){
        StringBuffer buf = new StringBuffer();
        buf.append(tableLine()+'\n');
        buf.append(headersToString()+'\n');
        buf.append(tableLine()+'\n');
        int i = 1;
        for (Contacto contacto : contactos) {
            buf.append(String.format("|%05d",i++));
            buf.append(contacto.toString()+'\n');
        }
        buf.append(MAS);
        buf.append(guiones(ANCHO_ID+ANCHO_PHONE+Contacto.getMax_nombre()+Contacto.getMax_correo()+Contacto.getMax_red_social()+7));
        buf.append(MAS+"\n");
        return buf.toString();
        
    }

    private String headersToString(){
        return String.format("|%5s|%-".concat(Integer.toString(Contacto.getMax_nombre()+1)).concat("s|%-15s|%-")
        .concat(Integer.toString(Contacto.getMax_correo()+1)).concat("s|%-").concat(Integer.toString(Contacto.getMax_red_social()+1)).concat("s|")
        ,"No. ","NOMBRE","TELÉFONO       ","CORREO","RED SOCIAL");
    }

    private String tableLine(){
        StringBuffer buf = new StringBuffer();
        buf.append(MAS);
        buf.append(guiones(ANCHO_ID));
        buf.append(MAS);
        buf.append(guiones(Contacto.getMax_nombre()+1));
        buf.append(MAS);
        buf.append(guiones(ANCHO_PHONE));
        buf.append(MAS);
        buf.append(guiones(Contacto.getMax_correo()+1));
        buf.append(MAS);
        buf.append(guiones(Contacto.getMax_red_social()+1));
        buf.append(MAS);
        return buf.toString();
    }

    private String guiones(int cantidad){
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < cantidad; i++) {
            buf.append(GUION);
        }
        return buf.toString();
    }
}
