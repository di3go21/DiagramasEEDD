create DATABASE if NOT EXISTS eedd;
use eedd;

CREATE TABLE IF NOT EXISTS `eedd`.`Curso` (
  `nombre` VARCHAR(45) NOT NULL,
  `siglas` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`siglas`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `eedd`.`Alumno` (
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NULL,
  `curso` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dni`),
  CONSTRAINT `fk_Alumno_Curso1`
    FOREIGN KEY (`curso`)
    REFERENCES `eedd`.`Curso` (`siglas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `eedd`.`Asignatura` (
  `nombre` VARCHAR(45) NOT NULL,
  `siglas` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`siglas`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `eedd`.`Profesor` (
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NULL,
  PRIMARY KEY (`dni`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `eedd`.`CUR_ASIG` (
  `xcurso` VARCHAR(45) NOT NULL,
  `xasignatura` VARCHAR(45) NOT NULL,
  `xprofesor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`xcurso`, `xasignatura`),
  CONSTRAINT `fk_CUR_ASIG_Curso1`
    FOREIGN KEY (`xcurso`)
    REFERENCES `eedd`.`Curso` (`siglas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CUR_ASIG_Asignatura1`
    FOREIGN KEY (`xasignatura`)
    REFERENCES `eedd`.`Asignatura` (`siglas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CUR_ASIG_Profesor1`
    FOREIGN KEY (`xprofesor`)
    REFERENCES `eedd`.`Profesor` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into asignatura values (
"Formación y Orientación Laboral","FOL"),(
"Fundamentos de hardware","FUN"),(
"Gestión de bases de datos","BBDD"),(
"Implantación de sistemas operativos","ISO"),(
"Lenguajes de marcas","LM"),(
"Planificación y administración de redes","PAR"),
("Sistemas Informáticos","SIS"),
("Entornos de Desarrollo","ENT"),
("Programación","PROG");

insert into profesor values (
"Javier","Lopez","111111",md5('profe')),
("Carolina","Tratamebien","222222",md5('profe')),
("Jerónimo","Eldefol","333333",md5('profe')),
("Manuel","Santana","444444",md5('profe')),
("Juan","Sistemas","555555",md5('profe')),
("Lorena","Aranda","666666",md5('profe')
);

insert into curso values (
    "Desarrollo de Aplicaciones Web","DAW"),
    ("Desarrollo de Aplicaciones Multiplataforma","DAM"),
    ("Administración de Sistemas Informáticos en Red","ASIR"
);

insert into alumno VALUES
("Diego","Leiva","112233",md5('alum'),"DAW"),
("Pedro","Gonzalez","223344",md5('alum'),"DAW"),
("Lucía","Rodríguez","334455",md5('alum'),"DAW"),
("Rocío","Gutiérrez","445566",md5('alum'),"DAW"),
("Rubén","Sánchez","778899",md5('alum'),"DAW"),
("Daniel","Iglesias","667788",md5('alum'),"DAM"),
("Javier","Bale","998877",md5('alum'),"DAM"),
("Silvia","Contreras","224411",md5('alum'),"DAM"),
("Almudena","Márquez","225566",md5('alum'),"DAM"),
("Laura","Garzón","113322",md5('alum'),"DAM"),
("Alba","Hernández","113344",md5('alum'),"ASIR"),
("Marina","Pérez","119988",md5('alum'),"ASIR"),
("Carlos","Casado","668877",md5('alum'),"ASIR"),
("Álvaro","Moreno","998833",md5('alum'),"ASIR"),
("Juan","Briones","445577",md5('alum'),"ASIR");

insert into cur_asig values 
("DAW","BBDD","444444"), 
("DAW","ENT","111111"), 
("DAW","PROG","666666"), 
("DAM","ENT","111111"), 
("DAM","LM","222222"), 
("DAM","BBDD","666666"), 
("ASIR","FOL","333333"), 
("ASIR","FUN","555555"), 
("ASIR","BBDD","444444");

select alumno.*, asignatura.nombre
from alumno,curso,cur_asig,asignatura
where alumno.curso=curso.siglas
and cur_asig.xcurso = curso.siglas 
and asignatura.siglas= cur_asig.xasignatura
order by alumno.nombre;

select profesor.*, curso.siglas, alumno.nombre, asignatura.siglas
from alumno,curso,cur_asig,profesor,asignatura
where alumno.curso=curso.siglas
and cur_asig.xcurso = curso.siglas 
and cur_asig.xasignatura = asignatura.siglas 
and profesor.dni= cur_asig.xprofesor
order by profesor.nombre,asignatura.siglas;
