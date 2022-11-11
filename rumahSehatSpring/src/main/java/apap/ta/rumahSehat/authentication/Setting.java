package apap.ta.rumahSehat.authentication;

import java.util.List;

public class Setting {
    final public static String CLIENT_BASE_URL = "http://localhost:8080";

    final public static String CLIENT_LOGIN = CLIENT_BASE_URL + "/validate-ticket";

    final public static String CLIENT_LOGOUT = CLIENT_BASE_URL + "/logout";

    final public static String SERVER_BASE_URL = "https://sso.ui.ac.id/cas2";

    final public static String SERVER_LOGIN = SERVER_BASE_URL + "/login?service=";

    final public static String SERVER_LOGOUT = SERVER_BASE_URL + "/logout?url=";

    final public static String SERVER_VALIDATE_TICKET
            = SERVER_BASE_URL + "/serviceValidate?ticket=%s&service=%s";

    final public static List<String> whitelist = List.of("gitan.sahl", "amelia.putri02", "josias.marchellino", "muhammad.raihan05", "muhammad.ivan91");
}
