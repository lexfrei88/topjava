DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, owner_id) VALUES
  ('2017-01-16T14:00', 'Dinner 2', 4000, 100000),
  ('2017-01-16T09:33', 'Breakfast 2', 1450, 100000),
  ('2017-01-15T22:00', 'Supper', 51, 100000),
  ('2017-01-15T16:00', 'Dinner', 400, 100000),
  ('2017-01-15T12:00', 'Lunch', 1100, 100000),
  ('2017-01-15T09:00', 'Breakfast', 450, 100000),
  ('2017-01-15T22:01', 'Admin Supper', 580, 100001),
  ('2017-01-15T10:00', 'Admin Breakfast', 1400, 100001);