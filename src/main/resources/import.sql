
INSERT INTO photos (title, description, visible, url) VALUES('Prima', 'pellicano', 1, 'https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg');

INSERT INTO categories (name) VALUES('Category 1');

INSERT INTO photos_categories (photo_id, category_id) VALUES(1, 1);
