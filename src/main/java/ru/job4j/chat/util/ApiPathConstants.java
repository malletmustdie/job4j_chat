package ru.job4j.chat.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiPathConstants {

    public static final String[] AUTH_WHITELIST = {
            "/",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**"
    };

    public static final String API_V_1 = "/api/v1";

    public static final String AUTHENTICATION = "/authentication";

    public static final String LOGIN = "/login";

    public static final String SIGN_UP_URL = API_V_1 + AUTHENTICATION + LOGIN;

    public static final String PERSON = "/person";

    public static final String CREATE_PERSON = "/create-person";

    public static final String UPDATE_PERSON = "/update-person";

    public static final String DELETE_PERSON = "/delete-person";

    public static final String FIND_PERSON = "/get-person";

    public static final String FIND_ALL_PERSONS = "/get-all-persons";

    public static final String ROOM = "/room";

    public static final String CREATE_ROOM = "/create-room";

    public static final String DELETE_ROOM = "/delete-room";

    public static final String JOIN_ROOM = "/join-room";

    public static final String LEAVE_ROOM = "/leave-room";

    public static final String FIND_ROOM = "/get-room";

    public static final String FIND_ALL_ROOM = "/get-all-room";

    public static final String MESSAGE = "/message";

    public static final String CREATE_MESSAGE = "/create-message";

    public static final String UPDATE_MESSAGE = "/update-message";

    public static final String DELETE_MESSAGE = "/delete-message";

    public static final String FIND_MESSAGE = "/get-message";

}
