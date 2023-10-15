# javaSwingForm

## Description
Este proyecto es un ejemplo de como crear un formulario con Java Swing.

## Proyecto
 ![Vista](/src/img/1_login.png)
 ![Succes](/src/img/2_loginsucces.png)
 ![Error](/src/img/3_error.png)
 ![Mysql](/src/img/5_db.png)

## Instalación
Para poder ejecutar el proyecto es necesario tener instalado:
* Java 20 -> https://www.oracle.com/java/technologies/javase-jdk20-downloads.html
* MySQL 8 -> https://railway.app/project/27b7ddaf-464f-48b5-9d80-dd9fee7976f8/plugin/0c8c491b-ddd1-4f0f-804b-7738e786cfac/data?state=table&table=users
    
* mysql-connector-java-8.0.22.jar

## Query
```sql
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```





