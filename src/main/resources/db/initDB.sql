DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE,
  calories_per_day INTEGER DEFAULT 2000 NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals
(
  id  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  dateTime TIMESTAMP NOT NULL,
  description VARCHAR NOT NULL,
  calories INTEGER NOT NULL,
  owner_id INTEGER NOT NULL,
  CONSTRAINT meal_owner_inx UNIQUE (owner_id, id),
  FOREIGN KEY (owner_id) REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO users
  (name, email, password, registered, enabled, calories_per_day)
VALUES
  ('UserName', 'user@email.com', 'userPassword', now(), TRUE, 2000),
  ('AdminName', 'admin@email.com', 'adminPassword', now(), TRUE, 3000);