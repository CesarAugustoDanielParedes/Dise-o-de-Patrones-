CREATE PROCEDURE CrearCurso
    @codigoCurso NVARCHAR(50),
    @nombreCurso NVARCHAR(100),
    @creditos INT,
    @idDocente INT,
    @descripcion NVARCHAR(MAX),
    @estado NVARCHAR(20)
AS
BEGIN
    INSERT INTO Cursos(codigoCurso, nombreCurso, creditos, idDocente, descripcion, estado)
    VALUES (@codigoCurso, @nombreCurso, @creditos, @idDocente, @descripcion, @estado)
END

CREATE PROCEDURE AsignarTarea
    @idTarea INT,
    @titulo NVARCHAR(100),
    @fechaEntrega DATE,
    @tipoArchivo NVARCHAR(20),
    @idCurso INT,
    @idDocente INT,
    @descripcion NVARCHAR(MAX)
AS
BEGIN
    INSERT INTO Tareas(id, titulo, fechaEntrega, tipoArchivo, idCurso, idDocente, descripcion)
    VALUES (@idTarea, @titulo, @fechaEntrega, @tipoArchivo, @idCurso, @idDocente, @descripcion)
END

CREATE PROCEDURE CalificarEstudiante
    @idCurso INT,
    @idEstudiante INT,
    @idTarea INT,
    @fechaEntrega DATE,
    @nota NUMERIC(4,2),
    @observacion NVARCHAR(MAX)
AS
BEGIN
    INSERT INTO Calificaciones(idCurso, idAlumno, idTarea, fechaEntrega, nota, observacion)
    VALUES (@idCurso, @idEstudiante, @idTarea, @fechaEntrega, @nota, @observacion)
END


