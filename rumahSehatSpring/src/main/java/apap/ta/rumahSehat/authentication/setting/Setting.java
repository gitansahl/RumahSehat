package apap.ta.rumahSehat.authentication.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Setting {
    public static final String CLIENT_BASE_URL = "https://apap-056.cs.ui.ac.id";

    public static final String CLIENT_LOGIN = CLIENT_BASE_URL + "/validate-ticket";

    public static final String CLIENT_LOGOUT = CLIENT_BASE_URL + "/logout";

    public static final String SERVER_BASE_URL = "https://sso.ui.ac.id/cas2";

    public static final String SERVER_LOGIN = SERVER_BASE_URL + "/login?service=";

    public static final String SERVER_LOGOUT = SERVER_BASE_URL + "/logout?url=";

    public static final String SERVER_VALIDATE_TICKET
            = SERVER_BASE_URL + "/serviceValidate?ticket=%s&service=%s";

    public static final List<String> whitelist = List.of("gitan.sahl", "amelia.putri02", "josias.marchellino", "muhammad.raihan05", "muhammad.ivan91");

    public static <T> Map<String, T> response(T status, T data) {
        Map <String, T> res = new HashMap<>();

        res.put("status", status);
        res.put("data", data);

        return res;
    }
}
