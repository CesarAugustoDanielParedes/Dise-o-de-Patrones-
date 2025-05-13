-- BASE DE DATOS
CREATE DATABASE RutaClass;

--Usuario
CREATE LOGIN user_g6 WITH PASSWORD = 'root1';
GO

USE RutaClass;
GO

CREATE USER user_g6 FOR LOGIN user_g6;
GO

-- Otorga permisos, por ejemplo:
ALTER ROLE db_owner ADD MEMBER user_g6;
GO



