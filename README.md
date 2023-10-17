# javaSwingForm

## Description
Este proyecto es un ejemplo de como crear un  sistema de registro de usuarios y empleados, con la finalidad de aprender a usar la libreria de java swing y mysql.

## Proyecto
 ![Vista](/src/img/1_s.png)
 ![AddUser](/src/img/2.png)
 ![System](/src/img/3_s.png)
 ![Add](/src/img/4_s.png)
 ![employee](/src/img/6_s.png)

## MySQl
  ![MySQL](/src/img/0_SQL.png)
  ![MySQL](/src/img/1_SQL.png)
  ![MySQL](/src/img/3_sql.png)

## Instalación
Para poder ejecutar el proyecto es necesario tener instalado:
* Java 20 -> https://www.oracle.com/java/technologies/javase-jdk20-downloads.html
* MySQL 8 -> https://railway.app/project/27b7ddaf-464f-48b5-9d80-dd9fee7976f8/plugin/0c8c491b-ddd1-4f0f-804b-7738e786cfac/data?state=table&table=users
  # importante
* mysql-connector-java-8.0.22.jar

## Query
```sql
CREATE TABLE `cargo` (
  `ID_CARGO` int PRIMARY KEY NOT NULL AUTO_INCREMENT ,
  `NOMBRECARGO` varchar(200),
  `ESTADO` bit(1)
);

CREATE TABLE `empleado` (
  `ID_EMPLEADO` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200),
  `APELLIDOS` varchar(200),
  `SEXO` char(1),
  `TELEFONO` char(12),
  `FECHANACIMIENTO` date,
  `TIPDOCUMENTO` char(11),
  `ID_USUARIO` int
);

CREATE TABLE `usuario` (
  `ID_USUARIO` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `NOMBREUSUARIO` varchar(200),
  `CLAVE` varchar(10),
  `ESTADO` bit(1),
  `ID_CARGO` int
);

ALTER TABLE `empleado` ADD FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID_USUARIO`);

ALTER TABLE `usuario` ADD FOREIGN KEY (`ID_CARGO`) REFERENCES `cargo` (`ID_CARGO`);
```


