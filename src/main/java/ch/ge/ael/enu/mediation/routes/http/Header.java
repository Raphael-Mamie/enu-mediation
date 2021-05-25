package ch.ge.ael.enu.mediation.routes.http;

import org.springframework.http.HttpHeaders;

/**
 * Pour les autres en-tetes, notamment HTTP, voir {}@link {@link HttpHeaders}.
 */
public class Header {

    public static final String X_CSRF_TOKEN = "X-CSRF-Token";

    public static final String REMOTE_USER = "remote_user";

    private Header() {
    }

}
