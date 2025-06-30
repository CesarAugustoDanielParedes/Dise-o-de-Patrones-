-- Tabla Usuario (entidad padre)
CREATE TABLE Usuario (
    ID_Usuario INT PRIMARY KEY IDENTITY(1,1),
    Nombres NVARCHAR(100) NOT NULL,
    Apellidos NVARCHAR(100) NOT NULL,
    DNI NVARCHAR(20) NOT NULL UNIQUE,
    CorreoInstitucional NVARCHAR(100) NOT NULL UNIQUE,
    FechaNacimiento DATE NOT NULL,
    Celular NVARCHAR(20),
    TipoUsuario NVARCHAR(10) NOT NULL CHECK (TipoUsuario IN ('ALUMNO', 'DOCENTE'))
);
GO
- Dimension de ventana (581, 597)
- Dimension de Mini ventana (597, 377)

-- Tabla Alumno
CREATE TABLE Alumno (
    ID_Usuario INT PRIMARY KEY,
    CarreraProfesional NVARCHAR(100) NOT NULL,
    CicloActual INT NOT NULL,
    EstadoAlumno NVARCHAR(20) NOT NULL CHECK (EstadoAlumno IN ('ACTIVO', 'EGRESADO', 'RETIRADO')),
    FechaIngreso DATE NOT NULL,
    PromedioPonderado NUMERIC(4,2),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario) ON DELETE CASCADE
);
GO


-- Tabla Docente
CREATE TABLE Docente (
    ID_Usuario INT PRIMARY KEY,
    Especialidad NVARCHAR(100) NOT NULL,
    GradoAcademico NVARCHAR(100) NOT NULL,
    TipoContrato NVARCHAR(50) NOT NULL,
    FechaIngreso DATE NOT NULL,
    Facultad NVARCHAR(100) NOT NULL,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario) ON DELETE CASCADE
);
GO

-- Procedimientos almacenados CRUD completos

-- Registrar Usuario (com n)
CREATE PROCEDURE sp_RegistrarUsuario
    @Nombres NVARCHAR(100),
    @Apellidos NVARCHAR(100),
    @DNI NVARCHAR(20),
    @CorreoInstitucional NVARCHAR(100),
    @FechaNacimiento DATE,
    @Celular NVARCHAR(20),
    @TipoUsuario NVARCHAR(10),
    @ID_Usuario INT OUTPUT
AS
BEGIN
    INSERT INTO Usuario (Nombres, Apellidos, DNI, CorreoInstitucional, FechaNacimiento, Celular, TipoUsuario)
    VALUES (@Nombres, @Apellidos, @DNI, @CorreoInstitucional, @FechaNacimiento, @Celular, @TipoUsuario);
    
    SET @ID_Usuario = SCOPE_IDENTITY();
END;
GO

-- Registrar Alumno
CREATE PROCEDURE sp_RegistrarAlumno
    @ID_Usuario INT,
    @CarreraProfesional NVARCHAR(100),
    @CicloActual INT,
    @EstadoAlumno NVARCHAR(20),
    @FechaIngreso DATE,
    @PromedioPonderado DECIMAL(3,2)
AS
BEGIN
    INSERT INTO Alumno (ID_Usuario, CarreraProfesional, CicloActual, EstadoAlumno, FechaIngreso, PromedioPonderado)
    VALUES (@ID_Usuario, @CarreraProfesional, @CicloActual, @EstadoAlumno, @FechaIngreso, @PromedioPonderado);
END;
GO

-- Registrar Docente
CREATE PROCEDURE sp_RegistrarDocente
    @ID_Usuario INT,
    @Especialidad NVARCHAR(100),
    @GradoAcademico NVARCHAR(100),
    @TipoContrato NVARCHAR(50),
    @FechaIngreso DATE,
    @Facultad NVARCHAR(100)
AS
BEGIN
    INSERT INTO Docente (ID_Usuario, Especialidad, GradoAcademico, TipoContrato, FechaIngreso, Facultad)
    VALUES (@ID_Usuario, @Especialidad, @GradoAcademico, @TipoContrato, @FechaIngreso, @Facultad);
END;
GO


-- Actualizar Usuario
CREATE PROCEDURE sp_ActualizarUsuario
    @ID_Usuario INT,
    @Nombres NVARCHAR(100),
    @Apellidos NVARCHAR(100),
    @DNI NVARCHAR(20),
    @CorreoInstitucional NVARCHAR(100),
    @FechaNacimiento DATE,
    @Celular NVARCHAR(20)
AS
BEGIN
    UPDATE Usuario SET
        Nombres = @Nombres,
        Apellidos = @Apellidos,
        DNI = @DNI,
        CorreoInstitucional = @CorreoInstitucional,
        FechaNacimiento = @FechaNacimiento,
        Celular = @Celular
    WHERE ID_Usuario = @ID_Usuario;
END;
GO


-- Actualizar Alumno
CREATE PROCEDURE sp_ActualizarAlumno
    @ID_Usuario INT,
    @CarreraProfesional NVARCHAR(100),
    @CicloActual INT,
    @EstadoAlumno NVARCHAR(20),
    @PromedioPonderado DECIMAL(3,2)
AS
BEGIN
    UPDATE Alumno SET
        CarreraProfesional = @CarreraProfesional,
        CicloActual = @CicloActual,
        EstadoAlumno = @EstadoAlumno,
        PromedioPonderado = @PromedioPonderado
    WHERE ID_Usuario = @ID_Usuario;
END;
GO

-- Actualizar Docente
CREATE PROCEDURE sp_ActualizarDocente
    @ID_Usuario INT,
    @Especialidad NVARCHAR(100),
    @GradoAcademico NVARCHAR(100),
    @TipoContrato NVARCHAR(50),
    @Facultad NVARCHAR(100)
AS
BEGIN
    UPDATE Docente SET
        Especialidad = @Especialidad,
        GradoAcademico = @GradoAcademico,
        TipoContrato = @TipoContrato,
        Facultad = @Facultad
    WHERE ID_Usuario = @ID_Usuario;
END;
GO

-- Eliminar Usuario (se elimina en cascada por la FK)
CREATE PROCEDURE sp_EliminarUsuario
    @ID_Usuario INT
AS
BEGIN
    DELETE FROM Usuario WHERE ID_Usuario = @ID_Usuario;
END;
GO

-- Obtener todos los usuarios
CREATE PROCEDURE sp_ObtenerTodosUsuarios
AS
BEGIN
    SELECT u.*, 
           a.CarreraProfesional, a.CicloActual, a.EstadoAlumno, a.PromedioPonderado,
           d.Especialidad, d.GradoAcademico, d.TipoContrato, d.Facultad
    FROM Usuario u
    LEFT JOIN Alumno a ON u.ID_Usuario = a.ID_Usuario
    LEFT JOIN Docente d ON u.ID_Usuario = d.ID_Usuario;
END;
GO

-- Obtener usuario por ID
CREATE PROCEDURE sp_ObtenerUsuarioPorId
    @ID_Usuario INT
AS
BEGIN
    SELECT u.*, 
           a.CarreraProfesional, a.CicloActual, a.EstadoAlumno, a.PromedioPonderado,
           d.Especialidad, d.GradoAcademico, d.TipoContrato, d.Facultad
    FROM Usuario u
    LEFT JOIN Alumno a ON u.ID_Usuario = a.ID_Usuario
    LEFT JOIN Docente d ON u.ID_Usuario = d.ID_Usuario
    WHERE u.ID_Usuario = @ID_Usuario;
END;
GO

