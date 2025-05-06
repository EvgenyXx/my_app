-- V7__Create_cards_table.sql

CREATE TABLE IF NOT EXISTS cards (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,        -- ID карты
    card_number VARCHAR(16) NOT NULL UNIQUE,       -- Номер карты
    expiration_number DATE NOT NULL,               -- Дата истечения срока
    card_type VARCHAR(255) ,               -- Тип карты (используется Enum, поэтому строка)
    user_id UUID NOT NULL,                         -- Идентификатор пользователя
    create_at timestamp NOT NULL DEFAULT NOW(),
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id) -- Связь с таблицей пользователей
);

CREATE INDEX idx_card_id ON cards (id);           -- Индекс для быстрого поиска по ID карты
