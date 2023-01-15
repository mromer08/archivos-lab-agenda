package com.mrojas;

import static picocli.CommandLine.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine;

/**
 * Hello world!
 *
 */
@Command(name = "agenda", mixinStandardHelpOptions = true, version = "0.0.1",
description = "Agenda simple de contactos")
public class App implements Callable<Integer>
{
    @CommandLine.Option(names = { "-f", "--file" }, description = "Archivo a leer", required = false)
    private File file;
    
    @Override
    public Integer call() throws Exception {
        Agenda a = new Agenda();
        if (file != null) {
            a.cargarArchivo(file);
        }
        a.menuPrincipal();
        return 0;
    }

    public static void main( String[] args )
    {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
