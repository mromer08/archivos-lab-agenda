package com.mrojas;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Agenda {
    public static final String REGEX_NAME = "[A-Za-z0-9+_-]+([ ]*[A-Za-z0-9+_-]*)*";
    public static final String REGEX_PHONE = "(\\+?(502)?)?[ ]?[0-9]{4}[ ]?[0-9]{4}";
    public static final String REGEX_CORREO = "|([A-Za-z0-9+_.-]*[A-Za-z0-9]@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6})";
    public static final String REGEX_SOCIAL = "|((http|https)(://)(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}([-a-zA-Z0-9()@:%_\\+.~#?&//=]*))";
    private List<Contacto> contactos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void menuPrincipal(){
        boolean salir = false;
        int opcion = 0;
        while (!salir) {
            System.out.println("\n*AGENDA*");
            System.out.println("1. Nuevo Contacto\n2. Editar Contacto\n3. Mostrar Contactos\n4. Archivos\n5. Salir");
            opcion = Integer.parseInt(pedirTexto("[0-9]", "número de opción"));
            switch (opcion) {
                case 1 -> {System.out.println("*NUEVO CONTACTO*"); contactos.add(menuRegistro()); Collections.sort(contactos);}
                case 2 -> menuEdit();
                case 3 -> mostrarRegistros();
                case 4 -> menuArchivos();
                case 5 -> salir = true;
                default -> System.out.println("Ingrese una opción válida!\n");
            }
        }
    }
    public void mostrarRegistros() {
        TableGenerator table = new TableGenerator();
        System.out.println(table.tableToString(contactos));
    }

    public void cargarArchivo(File file){
        try {
            if (file != null) {
                BufferedReader brf = Files.newBufferedReader(file.toPath());
                Parser p = new Parser(new AgendaLexer(brf));
                List<Contacto> con = (ArrayList<Contacto>) p.parse().value;
                if (contactos.isEmpty()) {
                    contactos = con;
                }else{
                    contactos.addAll(contactos.size()-1, con);
                }
                Collections.sort(contactos);
                contactos = removeDuplicates(contactos);
                System.out.println("ARCHIVO CARGADO CON ÉXITO!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("EL ARCHIVO NO SE HA PODIDO CARGAR :(");
        }

    }

    public void guardarArchivo(String nombre){
        File archivo = new File(nombre);
        try (PrintWriter buffer = new PrintWriter(new FileWriter(archivo, true))){
            for (Contacto contacto : contactos) {
                buffer.print(String.format("%s,%s", contacto.getNombre(),contacto.getTelefono()));
                if (contacto.getCorreo() != null && !contacto.getCorreo().isBlank()) {
                    buffer.print(",".concat(contacto.getCorreo()));
                }
                if (contacto.getRed_social() != null && !contacto.getRed_social().isBlank()) {
                    buffer.print(",".concat(contacto.getRed_social()));
                }
                buffer.println();
            }
            System.out.println("ARCHIVO GUARDADO!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Contacto> removeDuplicates(List<Contacto> list)
    {
        List<Contacto> newList = new ArrayList<Contacto>();
        for (Contacto element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }else{
                System.out.println("El contacto " + element.getNombre() + " ya se encuentra registrado");
            }
        }
        return newList;
    }
    private boolean isRepeatContacto(String nombre){
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().compareTo(nombre.toUpperCase()) == 0) {
                return true;
            }
        }
        return false;
    }
    private Contacto menuRegistro() {
        String nombre = pedirTexto(REGEX_NAME, "NOMBRE");
        while (isRepeatContacto(nombre)) {
            System.out.println("El contacto de " + nombre.toUpperCase() + " ya existe!\n");
            nombre = pedirTexto(REGEX_NAME, "NOMBRE");
        }
        String telefono = pedirTexto(REGEX_PHONE, "TELEFONO");
        String correo = pedirTexto(REGEX_CORREO, "CORREO (Opcional, ENTER para skippear)");
        String red_social = pedirTexto(REGEX_SOCIAL, "RED SOCIAL (Opcional, ENTER para skippear)");

        return new Contacto(nombre, telefono, correo.length() == 0 ? null : correo, red_social.length() == 0 ? null : red_social);
    }

    private void menuArchivos() {
        boolean salir = false;
        int opcion = 0;
        while (!salir){
            System.out.println("\n*ARCHIVOS*");
            System.out.println("1. Cargar Archivo\n2. Guardar Archivo\n3. Regresar");
            opcion = Integer.parseInt(pedirTexto("[0-9]", "número de opción"));
            switch (opcion) {
                case 1 -> cargarArchivo(new File(pedirTexto(null, "archivo para cargar")));
                case 2 -> guardarArchivo(pedirTexto(null, "nombre para el archivo"));
                case 3 -> salir = true;
                default -> System.out.println("Ingrese una opción válida!\n");
            }
        }
    }

    private void menuEdit(){
        int opcion = 0;
        System.out.println("\n*EDITAR CONTACTO*");
        mostrarRegistros();
        while (true) {
            opcion = Integer.parseInt(pedirTexto("[0-9]{1,3}", "No. de Contacto"));
            if (opcion <= contactos.size()) break;
        }
        Contacto edit = contactos.get(opcion-1);
        System.out.println("\nEditando a " + edit.getNombre());
        edit.setTelefono(pedirTexto(REGEX_PHONE, "TELEFONO"));
        edit.setCorreo(pedirTexto(REGEX_CORREO, "CORREO (Opcional, ENTER para skippear)"));
        edit.setRed_social(pedirTexto(REGEX_SOCIAL, "RED SOCIAL (Opcional, ENTER para skippear)"));

    }

    private String pedirTexto(String regex, String tipo) {
        String texto = "";
        if (regex != null) {
            do {
                System.out.print(tipo.concat(": "));
                texto = scanner.nextLine();
                System.out.println();
                if (!Pattern.matches(regex, texto))
                    System.out.println("Ingrese un ".concat(tipo).concat(" valido!"));
            } while (!Pattern.matches(regex, texto));
        }else{
            System.out.print(tipo.concat(": "));
            texto = scanner.nextLine();
            
        }
        return texto;
    }
}