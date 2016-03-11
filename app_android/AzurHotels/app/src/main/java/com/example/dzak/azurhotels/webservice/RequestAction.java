package com.example.dzak.azurhotels.webservice;

/**
 * Created by dzak on 25/02/16.
 */
public class RequestAction {

    public static final String ws_url = "http://10.0.2.2/azure_app/webservice/api.php";
    public static final String ws_url_get = ws_url + "?action=get";
    public static final String ws_url_post = ws_url + "?action=post";

    // Constants Get
    class Get{
        public static final String GET_HOTELS="get_hotels", GET_ALL_USERS="get_users";
    }

    // Constants Post
    class Post{
        public static final String REQ_REGISTRE="req_registre", REQ_LOGIN="req_login";
    }

}
