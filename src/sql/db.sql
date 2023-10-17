CREATE TABLE cargo (
  IDCARGO int(11) NOT NULL,
  NOMBRECARGO varchar(20) DEFAULT NULL,
  ESTADO bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE empleado (
  IDEMPLEADO int(11) NOT NULL,
  NOMBRE varchar(40) DEFAULT NULL,
  APELLIDOS varchar(40) DEFAULT NULL,
  SEXO char(1) DEFAULT NULL,
  TELEFONO char(12) DEFAULT NULL,
  FECHANACIMIENTO date DEFAULT NULL,
  TIPDOCUMENTO char(11) DEFAULT NULL,
  IDUSUARIO int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE usuario (
  IDUSUARIO int(11) NOT NULL,
  NOMBREUSUSARIO varchar(20) DEFAULT NULL,
  CLAVE varchar(10) DEFAULT NULL,
  ESTADO bit(1) DEFAULT NULL,
  IDCARGO int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE cargo
  ADD PRIMARY KEY (IDCARGO);


ALTER TABLE empleado
  ADD PRIMARY KEY (IDEMPLEADO),
  ADD KEY FK_EMPLEADO_USU (IDUSUARIO);


ALTER TABLE usuario
  ADD PRIMARY KEY (IDUSUARIO),
  ADD KEY FK_USUARIO_CARGO (IDCARGO);


ALTER TABLE cargo
  MODIFY IDCARGO int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE empleado
  MODIFY IDEMPLEADO int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE usuario
  MODIFY IDUSUARIO int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE empleado
  ADD CONSTRAINT FK_EMPLEADO_USU FOREIGN KEY (IDUSUARIO) REFERENCES usuario (IDUSUARIO);


ALTER TABLE usuario
  ADD CONSTRAINT FK_USUARIO_CARGO FOREIGN KEY (IDCARGO) REFERENCES cargo (IDCARGO);

