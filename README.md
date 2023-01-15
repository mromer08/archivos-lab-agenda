# Agenda
Agenda sencilla para el laboratorio de Manejo e implementación de Archivos 2022 USAC.

## Funciones de la Agenda
La agenda cuenta con 4 opciones principales:
```
*AGENDA*
1. Nuevo Contacto
2. Editar Contacto
3. Mostrar Contactos
4. Archivos
5. Salir
```

### 1. Nuevo contacto
Permite crear un contacto totalemente desde consola con los siguientes datos:

- Nombre

- Teléfono

- Correo (Opcional, ENTER para skippear)

- Red Social (Opcional, ENTER para skippear)

Si el nombre ya se encuentra en la lista de contactos, se le hará saber para que indique uno diferente.
### 2. Editar contacto
Permite editar la información del contacto salvo por el nombre. Se le desplegarán los contactos guardados y se deberá seleccionar el que se desea editar en base al número que lo representa.

### 3. Mostrar Contactos
Muestra los contactos almacenados en forma de tabla por consola. Por ejemplo:
```
+-----+-----------+---------------+----------------------------------+----------------------------+
| No. |NOMBRE     |TELÉFONO       |CORREO                            |RED SOCIAL                  |
+-----+-----------+---------------+----------------------------------+----------------------------+
|00001|LUIS       |+502 5369 8745 |luis.killer@gmail.com             |https://fb.com/luis         |
|00002|MANUEL     |+502 4265 9845 |202030799manuelrojas@cunoc.edu.gt |https://fb.com/manuel.rojas |
|00003|MARIA      |+502 3365 4589 |marialop@hotmail.com              |https://fb.com/maria        |
|00004|OSCAR      |+502 5523 9687 |oscar1236@gmail.com               |https://www.twitch.tv/os    |
+-------------------------------------------------------------------------------------------------+
```
### 4. Archivos
Permite la manipulación de archivos, contiene 2 opciones principales
1. Cargar Archivo
2. Guardar Archivo

Para ambos casos es necesario indicar la ruta del archivo, de lo contrario no se ejecutará de forma correcta y regresará al menú de Archivos.
## Uso de la aplicación
Ya sea a través del jar o el script `agenda`, se puede enviar un archivo directamente con el argumento `-f` o `--file` más el archivo a cargar. Por ejemplo
> ./agenda -f tests/test1.txt

Luego de haber procesado el archivo, se le mostrará el menú principal de la agenda.
Si no se desea iniciar directamente con un archivo, puede solo ejecutarse el jar o script para acceder a las distintas funciones con normalidad.
## Uso del archivo Makefile
El archivo Makefile incluido presenta algunas tareas útiles que pueden ser invocadas directamente en la línea de comandos.

Para compilar el programa ejecute

> make compile

Para generar un script `agenda` que ejecute la aplicación, ejecute

> make agenda

Para limpiar todo el proyecto ejecute:

> make clean

Así mismo existen tres fases adicionales que ejecutan en secuencia varios pasos para una prueba completa del funcionamiento de la aplicación

La primera es `make test1` la cual limpia el proyecto, ejecuta la compilación, genera la app agenda y lee el archivo `test1.txt` para luego mostrar en consola los registros válidos.

La segunda es `make test2` la cual limpia el proyecto, ejecuta la compilación, genera la app agenda y lee el archivo `test2.txt` para luego mostrar en consola los registros válidos.

La tercera es `make test3` la cual limpia el proyecto, ejecuta la compilación, genera la app agenda y ejecuta una serie de pasos para la carga y guardado de archivos.