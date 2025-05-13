

-- Tabla Usuario (base para estudiante y docente)
CREATE TABLE Usuario (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('ESTUDIANTE', 'DOCENTE')),
    fecha_registro DATETIME DEFAULT GETDATE()
);
GO

-- Tabla Estudiante (extiende Usuario)
CREATE TABLE Estudiante (
    usuario_id INT PRIMARY KEY,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    grado VARCHAR(50),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);
GO

-- Tabla Docente (extiende Usuario)
CREATE TABLE Docente (
    usuario_id INT PRIMARY KEY,
    especialidad VARCHAR(100),
    departamento VARCHAR(100),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);
GO

-- Tabla Curso
CREATE TABLE Curso (
    id INT PRIMARY KEY IDENTITY(1,1),
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    creditos INT NOT NULL,
    docente_id INT,
    FOREIGN KEY (docente_id) REFERENCES Docente(usuario_id)
);
GO

-- Lote 2: Creación del procedimiento almacenado (debe ser el primero en su lote)
CREATE PROCEDURE sp_crud_afiliacion
    @opcion VARCHAR(10), -- 'CREATE', 'READ', 'UPDATE', 'DELETE'
    @tipo_entidad VARCHAR(20), -- 'USUARIO', 'ESTUDIANTE', 'DOCENTE', 'CURSO'
    @id INT = NULL,
    @nombre VARCHAR(100) = NULL,
    @email VARCHAR(100) = NULL,
    @telefono VARCHAR(20) = NULL,
    @tipo_usuario VARCHAR(20) = NULL,
    @matricula VARCHAR(20) = NULL,
    @grado VARCHAR(50) = NULL,
    @especialidad VARCHAR(100) = NULL,
    @departamento VARCHAR(100) = NULL,
    @codigo_curso VARCHAR(20) = NULL,
    @descripcion_curso TEXT = NULL,
    @creditos_curso INT = NULL,
    @docente_id_curso INT = NULL,
    @resultado INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    IF @opcion = 'CREATE'
    BEGIN
        IF @tipo_entidad = 'USUARIO'
        BEGIN
            INSERT INTO Usuario (nombre, email, telefono, tipo)
            VALUES (@nombre, @email, @telefono, @tipo_usuario)
            SET @resultado = SCOPE_IDENTITY()
        END
        ELSE IF @tipo_entidad = 'ESTUDIANTE'
        BEGIN
            INSERT INTO Estudiante (usuario_id, matricula, grado)
            VALUES (@id, @matricula, @grado)
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'DOCENTE'
        BEGIN
            INSERT INTO Docente (usuario_id, especialidad, departamento)
            VALUES (@id, @especialidad, @departamento)
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'CURSO'
        BEGIN
            INSERT INTO Curso (codigo, nombre, descripcion, creditos, docente_id)
            VALUES (@codigo_curso, @nombre, @descripcion_curso, @creditos_curso, @docente_id_curso)
            SET @resultado = SCOPE_IDENTITY()
        END
    END
    ELSE IF @opcion = 'READ'
    BEGIN
        IF @tipo_entidad = 'USUARIO'
            SELECT * FROM Usuario WHERE id = @id
        ELSE IF @tipo_entidad = 'ESTUDIANTE'
            SELECT u.*, e.matricula, e.grado 
            FROM Usuario u JOIN Estudiante e ON u.id = e.usuario_id 
            WHERE u.id = @id
        ELSE IF @tipo_entidad = 'DOCENTE'
            SELECT u.*, d.especialidad, d.departamento 
            FROM Usuario u JOIN Docente d ON u.id = d.usuario_id 
            WHERE u.id = @id
        ELSE IF @tipo_entidad = 'CURSO'
            SELECT * FROM Curso WHERE id = @id
    END
    ELSE IF @opcion = 'UPDATE'
    BEGIN
        IF @tipo_entidad = 'USUARIO'
        BEGIN
            UPDATE Usuario SET 
                nombre = @nombre, 
                email = @email, 
                telefono = @telefono
            WHERE id = @id
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'ESTUDIANTE'
        BEGIN
            UPDATE Estudiante SET 
                matricula = @matricula, 
                grado = @grado
            WHERE usuario_id = @id
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'DOCENTE'
        BEGIN
            UPDATE Docente SET 
                especialidad = @especialidad, 
                departamento = @departamento
            WHERE usuario_id = @id
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'CURSO'
        BEGIN
            UPDATE Curso SET 
                codigo = @codigo_curso,
                nombre = @nombre,
                descripcion = @descripcion_curso,
                creditos = @creditos_curso,
                docente_id = @docente_id_curso
            WHERE id = @id
            SET @resultado = @id
        END
    END
    ELSE IF @opcion = 'DELETE'
    BEGIN
        IF @tipo_entidad = 'USUARIO'
        BEGIN
            -- Primero eliminar de las tablas hijas si existen
            DELETE FROM Estudiante WHERE usuario_id = @id
            DELETE FROM Docente WHERE usuario_id = @id
            -- Luego eliminar el usuario
            DELETE FROM Usuario WHERE id = @id
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'ESTUDIANTE'
        BEGIN
            DELETE FROM Estudiante WHERE usuario_id = @id
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'DOCENTE'
        BEGIN
            DELETE FROM Docente WHERE usuario_id = @id
            SET @resultado = @id
        END
        ELSE IF @tipo_entidad = 'CURSO'
        BEGIN
            DELETE FROM Curso WHERE id = @id
            SET @resultado = @id
        END
    END
END
GO