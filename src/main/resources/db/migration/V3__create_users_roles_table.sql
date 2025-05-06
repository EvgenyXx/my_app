-- Создание таблицы users_roles (если она не была создана автоматически)
CREATE TABLE IF NOT EXISTS users_roles (
    user_id UUID NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Создание индексов для улучшения производительности
CREATE INDEX IF NOT EXISTS idx_user_role_user_id ON users_roles (user_id);
CREATE INDEX IF NOT EXISTS idx_user_role_role_id ON users_roles (role_id);
