INSERT INTO Cat (owner, name,  breed)
VALUES ('Koci zakątek', 'Liliana', 'SYBERYJSKI'),
       ('Zmierzch', 'Filomena', 'SYBERYJSKI'),
       ('Z kociego domu', 'Pankracy', 'SYBERYJSKI'),
       ('Koci łapci', 'Amelka', 'SYBERYJSKI'),
       ('Rajskie Łąki', 'Zefir', 'SYBERYJSKI'),
       ('Z Dziczy', 'Ryś', 'SYBERYJSKI'),
       ('Czarnylas', 'Balwinka', 'SYBERYJSKI'),

       ('Zmierzch', 'Amber', 'SYJAMSKI'),
       ('Z kociego domu', 'Mruczek', 'SYJAMSKI'),
       ('Koci łapci', 'Bajka', 'SYJAMSKI'),
       ('Z Dziczy', 'Zoja', 'SYJAMSKI'),

       ('Koci zakątek', 'Ruda', 'BRYTYJSKI'),
       ('Zmierzch', 'Apollo', 'BRYTYJSKI'),
       ('Z kociego domu', 'Behemot', 'BRYTYJSKI'),
       ('Koci łapci', 'Felek', 'BRYTYJSKI'),
       ('Czarnylas', 'Gacek', 'BRYTYJSKI'),

       ('Z kociego domu', 'Dante', 'MAIN_COON'),
       ('Koci łapci', 'Nela', 'MAIN_COON'),
       ('Rajskie Łąki', 'Tofik', 'MAIN_COON'),
       ('Z Dziczy', 'Szogun', 'MAIN_COON'),
       ('Czarnylas', 'Skarpetka', 'MAIN_COON');

INSERT INTO cat_tickets (cat_id, tickets)
VALUES (1,2);

INSERT INTO judge_points_mapping (cat_id, judge_id, points)
VALUES (1,1,9),
       (1,2,9),
       (2,1,8);