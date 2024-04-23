package com.iba.ipr.dto.response;

public class ResponseMessageConstant {
    public static final String INCORRECT_FILE_INFORMATION = "Incorrect file information";
    public static final String TEMPLATE = """
            {
                "dateTime": "2023-01-20T13:37:19.408Z",
                "message":\040""";
    public static final String TEMPLATE_END = "\"\n}";

    public static final String INCORRECT_CREDENTIALS = TEMPLATE + "\"" + "Incorrect credentials." + TEMPLATE_END;
    public static final String USER_WAS_NOT_FOUND = TEMPLATE + "\"" + "User with id '111111' not found." + TEMPLATE_END;
}