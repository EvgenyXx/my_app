-- Создание таблицы users
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    number_phone VARCHAR(11) UNIQUE NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    create_at timestamp NOT NULL DEFAULT NOW(),
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    birth_date DATE NOT NULL
);

-- Создание индексов
CREATE INDEX IF NOT EXISTS idx_user_id ON users (id);
CREATE INDEX IF NOT EXISTS idx_numberPhone ON users (number_phone);
CREATE INDEX IF NOT EXISTS idx_email ON users (email);
