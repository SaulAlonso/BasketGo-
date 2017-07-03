BasketGo!
==========
Todo lo que necesitas como seguidor de este deporte.

## Descripción Temática de la Web:

Esta aplicación se trata de una herramienta total para tu equipo, con ella se puede gestionar todos los aspectos del tu equipo asi como de la liga en la que te encuentras, organizar torneos... todo lo necesario para la practica de este deporte.

* Parte Pública: 
  + Se podrá consultar tanto calendarios como clasificaciones.
  + Datos de los jugadores de cada equipo (Edad, Nombre, Altura, Número de ficha).
  + Información relativa a cada equipo (Nombre, Partidos jugados, Victorias, Derrotas, Número de jugadores).
  + Detalles de cada partido (Equipos enfrentados, Liga a la que pertenecen, Resultado, Fecha).

* Parte Privada: 
  + Cada jugador puede darse de alta en la web, y un usuario puede crear un equipo que represente a diferentes jugadores, este será el      usuario entrenador del equipo, se encargara de la inscripcion de jugadores o de inscribir al equipo en torneos. 
  + Se podrán crear equipos a partir de jugadores existentes, y las ligas se crearan a partir de estos equipos.
  + Existira tambien un usuario administrador que creará los torneos y se encargará de su administración.
 
## Entidades Principales:
* Liga: En el que jugarán todos los equipos teniendo enfrentamientos entre cada uno de ellos, se podrán inscribir un maximo de jugadores elegido por el administrador de la liga.
* Clasificación: Listado ordenado de equipos según su puntuación.
* Equipo: Poseerá un nombre único, asi como sus victorias y derrotas y los jugadores inscritos.
* Usuario: Existiendo dos tipos de usuarios como son el entrenador que será el encargado de crear el equipo asi como inscribir a cada uno de los jugadores en dicho equipo y el usuario administrador que encargará de la creación de liga.
* Jugador: Contendrá las caracteristicas especificas como son nombre, altura edad y numero de ficha.
* Partido: Se encarga de almacenar tanto los equipos, la fecha, el resultado y su liga.

## Servicio de la web:
Permitirá descargar la clasificación de la liga seleccionada en formato PDF para su lectura offline.
https://github.com/SaulAlonso/Backend-pdf-basketgo

## Capturas de pantalla
Página de inicio/log
===
<a href="http://es.tinypic.com?ref=2a6o36c" target="_blank"><img src="http://i66.tinypic.com/2a6o36c.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página registrar usuario nuevo
===
<a href="http://es.tinypic.com?ref=2pzxlef" target="_blank"><img src="http://i67.tinypic.com/2pzxlef.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página post-login
===
<a href="http://es.tinypic.com?ref=330zfw3" target="_blank"><img src="http://i64.tinypic.com/330zfw3.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página principal 
===
<a href="http://es.tinypic.com?ref=eb7hwp" target="_blank"><img src="http://i68.tinypic.com/eb7hwp.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página de jugadores (Logueado) 
===
<a href="http://es.tinypic.com?ref=2uj2xkg" target="_blank"><img src="http://i66.tinypic.com/2uj2xkg.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página buscar jugadores
===
<a href="http://es.tinypic.com?ref=1e9lkx" target="_blank"><img src="http://i65.tinypic.com/1e9lkx.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página editar jugador (Logueado) 
===
<a href="http://es.tinypic.com?ref=1giog5" target="_blank"><img src="http://i66.tinypic.com/1giog5.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página para buscar equipo
===
<a href="http://es.tinypic.com?ref=332wj1g" target="_blank"><img src="http://i64.tinypic.com/332wj1g.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página para la gestion del equipo
===
<a href="http://es.tinypic.com?ref=2u9ubd3" target="_blank"><img src="http://i67.tinypic.com/2u9ubd3.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página registrar equipo
===
<a href="http://es.tinypic.com?ref=200ptzm" target="_blank"><img src="http://i63.tinypic.com/200ptzm.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página de las ligas(no logueado) 
===
<a href="http://es.tinypic.com?ref=35koraw" target="_blank"><img src="http://i65.tinypic.com/35koraw.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página pdf generado
===
<a href="http://es.tinypic.com?ref=nlzrc8" target="_blank"><img src="http://i65.tinypic.com/nlzrc8.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Ejemplo pdf generado
===
<a href="http://es.tinypic.com?ref=20jrdyu" target="_blank"><img src="http://i64.tinypic.com/20jrdyu.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página liga (logueado)
===
<a href="http://es.tinypic.com?ref=ipbeqr" target="_blank"><img src="http://i64.tinypic.com/ipbeqr.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Página para registrar una liga nueva
===
<a href="http://es.tinypic.com?ref=k2igt3" target="_blank"><img src="http://i65.tinypic.com/k2igt3.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Ejemplo error crear liga nueva
===
<a href="http://es.tinypic.com?ref=15h23a1" target="_blank"><img src="http://i66.tinypic.com/15h23a1.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===
Ejemplo error buscar liga
===
<a href="http://es.tinypic.com?ref=qn7hp4" target="_blank"><img src="http://i68.tinypic.com/qn7hp4.jpg" border="0" alt="Image and video hosting by TinyPic"></a>
===

## Diagrama UML


<a href="http://es.tinypic.com?ref=2mdqikn" target="_blank"><img src="http://i64.tinypic.com/2mdqikn.jpg" border="0" alt="Image and video hosting by TinyPic"></a>


## Diagrama E/R


<a href="http://es.tinypic.com?ref=157ovp1" target="_blank"><img src="http://i65.tinypic.com/157ovp1.jpg" border="0" alt="Image and video hosting by TinyPic"></a>


## Diagrama de secuencia


<a href="http://es.tinypic.com?ref=107mg4y" target="_blank"><img src="http://i68.tinypic.com/107mg4y.jpg" border="0" alt="Image and video hosting by TinyPic"></a>

## Diagrama Azure

<a href="http://es.tinypic.com?ref=24g56xf" target="_blank"><img src="http://i63.tinypic.com/24g56xf.jpg" border="0" alt="Image and video hosting by TinyPic"></a>

## DESPLEGAR LA APLICACION EN AZURE

Se genera un certificado:
  openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout azureus.key -out azureus-cert.pem
  
Permisos a la clave privada:
 chmod 0600 azureus.key
 
Acceder a la maquina virtual con un cliente ssh
 ssh -p puerto azureuser@ip publica
 
Instalamos OpenJDK 8 JRE y mysql con los siguientes comandos:
 sudo add-apt-repository ppa:openjdk-r/ppa
 sudo apt-get update
 sudo apt-get install openjdk-8-jre
 sudo apt-get update
 sudo apt-get install -y mysql -server

Creamos la base de datos:
 $ mysql -u root -p;
 mysql> create database 'nombrebbdd';
 mysql> create user 'user'@'localhost' identified by 'contraseña';
 mysql> grant all privileges on <bbdd>.* to `usuario´@´%´;
 mysql> flush privileges;
 mysql> exit;
 
Creamos el ejecutable desde spring con boton derecho run as-> maven install

Subir el ejecutable desde nuestro equipo a la maquina virtual:

scp -i azureus.key -P puerto application.jar azureuser@<ip publica>:/home/azureuser/

Acceder a la maquina virtual como ya hemos visto en el paso 4:

ssh -p puerto azureuser@<ip publica>

Ejecutar la aplicacion desde la maquina virtual:

java -jar aplicacion.jar
Acceder via web a la aplicación:

https//52.166.61.73
===

## Video ejecucion 

https://youtu.be/08Jx9jXtHEQ

## Integrantes del Proyecto:
* Saúl Alonso Martín
  + Correo: s.alonsoma@alumnos.urjc.es
  + Github: SaulAlonso
* Juan Carlos López De La Torre 
  + Correo: jc.lopezd@alumnos.urjc.es
  + Github: JuanCarlosLopezdeTorre
