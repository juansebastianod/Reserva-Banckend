# Aruitectura
https://drive.google.com/file/d/16VCTqeBNocJtdQXSEhLTUWfa-zaiksyb/view?usp=drive_link
# Video guia 
https://drive.google.com/file/d/1_0CEtmrTVLJ9gfuoIBvBqa-uACnB8st7/view?usp=drive_link
# Tener el jdk 17 de java
# crear la base de datos 
 create database reservation;
# Correr el proyecto
# Insertar los roles
use reservation;

INSERT INTO roles (name, description) VALUES
('ADMIN', 'Administrator role with full access');

-- Rol 2

INSERT INTO roles (name, description) VALUES
('CLIENTE', 'Regular user role with limited access');

# Crear un usuario ADMIN
INSERT INTO users (name, username, email, password, phone, address, role_id) 
VALUES ('Admin Name', 'adminusername', 'admin@example.com', 'hashed_password', '1234', 'Admin Address', 1);
# Segui indicaciones del video de como sirve
