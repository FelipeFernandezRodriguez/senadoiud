USE senado_iud;

INSERT INTO roles (id, name, description)
VALUES (1, 'ROLE_ADMIN', 'UserEntity administrador');

INSERT INTO roles (id, name, description)
VALUES (2, 'ROLE_USER', 'UserEntity normal');

INSERT INTO political_parties (id, name, slogan, image)
VALUES (1, 'ADMIN', 'ADMINISTRADOR', 'ADMIN');

INSERT INTO users (id, email, name, last_name, password, department,  enabled, image, political_parties_id)
VALUES (1, 'felipe.fernandezr@est.iudigital.edu.co', 'Felipe', 'Fernández', '123456', 'ANTIOQUIA', true, 'ADMIN', 1);