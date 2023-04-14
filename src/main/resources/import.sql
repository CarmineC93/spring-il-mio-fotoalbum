
INSERT INTO photos (title, description, visible, url) VALUES('Prima', 'pellicano', 1, 'https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg');
INSERT INTO photos (title, description, visible, url) VALUES('Seconda', 'prova con validazione', 1, 'https://rivistanatura.com/wp-content/uploads/2021/06/rana-cioccolato-770x470.jpg');


INSERT INTO categories (name) VALUES('Category 1');

INSERT INTO photos_categories (photo_id, category_id) VALUES(1, 1);

INSERT INTO users (email, first_name, last_name, password) VALUES('gino@email.it', 'Gino', 'Rossi', '{noop}gino123');
INSERT INTO users (email, first_name, last_name, password) VALUES('pluto@email.it', 'Pluto', 'Plutino', '{noop}pluto123');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users_roles(user_id, roles_id) VALUES (1,1);
INSERT INTO users_roles(user_id, roles_id) VALUES (2,2);
