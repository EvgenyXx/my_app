package com.example.my_app.service.user;

import com.example.my_app.dto.user.*;
import com.example.my_app.entity.User;
import com.example.my_app.exception.user.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    /**
     * Метод для регистрации пользователя в приложении.
     * Если регистрация прошла успешно пользователь сохраняется в системе
     * @param userCreateRequest, в качестве параметра принимает дто
     *  которое, содержит номер телефона, имя пользователя, пароль пользователя
     */
    UserCreateResponse createUser(UserCreateRequest request);



    /**
     * Сохраняет пользователя в базе данных
     * @param user,принимает на вход сущность пользователя
     *                       и сохраняет ее в базе данных
     */
    User saveUser(User user);

    /**
     * Метод для поиска сущности пользователя по айди
     * @param uuid,принимает айди пользователя для его поиска
     * @return возвращает пользователя если он будет найдет
     * @throws UserNotFoundException если пользователя не найден
     * будет выброшено исключение об отсутствии такого пользователя
     */
    User findUserById(UUID uuid);

    /**
     * Метод для поиска пользователей по первым буквам имени
     *
     * @param firstname, принимает целое имя пользователя или первые буквы имени для поиска
     * @return возвращает список пользователей у которых идет совпадение по заданным параметрам
     */
    List<UserSearchResponse> findByFirstnameStartingWithOrderByFirstnameDesc(String firstname);

    /**
     * Метод для удаления пользователя из системы
     * @param userId, принимает айди пользователя и если пользователя с таким айди существует
     *                то, удаляет его из системы
     */
    void deleteUserById(UUID userId);

    /**
     * Метод для обновления пользователя
     * @param userId, принимает айди пользователя для поиска, если пользователь не найден будет
     *                выброшено исключение о том что пользователь не найден
     * @param userUpdateRequest,дто со всеми теми полями которые пользователь может
     *                              обновить вручную
     * @return возвращает дто с информацией о пользователи
     */
    UserUpdateResponse updateUser(UUID userId, UserUpdateRequest userUpdateRequest);



    /**
     * Метод предназначен для поиска пользователя по номеру телефона
     * @param numberPhone,принимает номер телефона для поиска
     * @return возвращает пользователя если находит его по параметру
     */
    User findByNumberPhone(String numberPhone);

    /**
     * Метод для поиска пользователя по email
     * @param email, в качестве параметра принимает email который введет пользователь
     * @return User , возвращает пользователя с заданным email
     */
    User findByEmail(String email);

    /**
     * Метод для получения возраста пользователя
     * @return возвращает возраст числом
     */
    int getAgeUser(UUID userId);
}
