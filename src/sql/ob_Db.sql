

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


String MYSQLHOST = "containers-us-west-167.railway.app";
String MYSQLPORT = "5829";
String MYSQLDATABASE = "railway";
String MYSQLUSER= "root";
String url = "jdbc:mysql://" + MYSQLHOST + ":" + MYSQLPORT + "/" + MYSQLDATABASE;

