-- Создание таблицы roles
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    role_name VARCHAR(255) UNIQUE NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание индексов для таблицы roles
CREATE INDEX IF NOT EXISTS idx_role_id ON roles (id);
CREATE INDEX IF NOT EXISTS idx_role_name ON roles (role_name);
