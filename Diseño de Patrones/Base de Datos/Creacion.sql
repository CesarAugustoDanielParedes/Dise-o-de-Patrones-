-- BASE DE DATOS
CREATE DATABASE RutaClass;

--Usuario
CREATE LOGIN user06 WITH PASSWORD = 'root3';
GO

USE RutaClass;
GO

CREATE USER user06 FOR LOGIN user06;
GO

-- Otorga permisos, por ejemplo:
ALTER ROLE db_owner ADD MEMBER user06;
GO



