-- V6__insert_evgeny_admin_user.sql
-- Вставка пользователя Evgeny Pavlov с админскими правами

-- Вставка пользователя с датой рождения и новым номером телефона
INSERT INTO users (email, number_phone, firstname, lastname, password, create_at, update_at, birth_date)
VALUES (
    'evgenypavlov666@yandex.ru',  -- Почта
    '89181339188',  -- Новый номер телефона
    'Evgeny',  -- Имя
    'Pavlov',  -- Фамилия
    '$2a$10$ZUigNKsQmOwrRTdKT5O4AeRHroCxIXyd0zR4YHNGKwRemBwvTStHu',  -- Захешированный пароль
    CURRENT_TIMESTAMP,  -- Дата создания
    CURRENT_TIMESTAMP,  -- Дата обновления
    '1996-04-24'  -- Дата рождения
);

-- Присвоение роли админа пользователю
WITH user_id AS (
    SELECT id FROM users WHERE email = 'evgenypavlov666@yandex.ru'
)
INSERT INTO users_roles (user_id, role_id)
SELECT user_id.id, roles.id
FROM user_id, roles
WHERE roles.role_name = 'ROLE_ADMIN';
