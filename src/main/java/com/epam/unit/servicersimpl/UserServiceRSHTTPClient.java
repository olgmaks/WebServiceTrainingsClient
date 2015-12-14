package com.epam.unit.servicersimpl;

import com.epam.unit.model.Role;
import com.epam.unit.model.User;
import com.epam.unit.modelweb.Response;
import com.epam.unit.modelweb.Summary;
import com.epam.unit.modelweb.SummaryFault;
import com.epam.unit.modelweb.SummarySuccess;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OLEG on 08.12.2015.
 */
public class UserServiceRSHTTPClient {

    private static final String SERVER_URI = "http://localhost:8080";
    private static final String LOGIN_USER_URI = SERVER_URI + "/Services/rest/userService/login";
    private static final String DELETE_USER_URI = SERVER_URI + "/Services/rest/userService/deleteUserById";
    private static final String REGISTER_USER_URI = SERVER_URI + "/Services/rest/userService/register";
    private static final String GET_ALL_USERS_URI = SERVER_URI + "/Services/rest/userService/getAllUsers";
    private static final String AUTHORIZE_USER_URI = SERVER_URI + "/Services/rest/userService/authorize";
    private static final String GET_USERS_BY_ROLE_NAME_URI = SERVER_URI + "/Services/rest/userService/getUsersByRoleName";

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String FIRST_NAME_PARAM = "firstName";
    private static final String LAST_NAME_PARAM = "lastName";
    private static final String ROLE_NAME_PARAM = "roleName";
    private static final String ID_PARAM = "id";


    private ObjectMapper objectMapper;

    public UserServiceRSHTTPClient() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject methodGET(String URI) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(URI);

        HttpResponse response = httpClient.execute(httpGet);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }




        JSONObject responseJSON = new JSONObject(result.toString());

        return responseJSON;
    }

    private JSONObject methodPOST(String URI, List<NameValuePair> postMethodParams) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(URI);

        UrlEncodedFormEntity form = new UrlEncodedFormEntity(postMethodParams, Consts.UTF_8);

        post.setEntity(form);
        HttpResponse response = httpClient.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return new JSONObject(result.toString());
    }


    private Summary fetchSummary(JSONObject jsonObject) throws IOException {


        JSONObject summary = jsonObject.getJSONObject("summary");

        if (summary.has("successMessage")) {

            return objectMapper.readValue(summary.toString(), SummarySuccess.class);

        } else if (summary.has("errorMessage")) {

            return objectMapper.readValue(summary.toString(), SummaryFault.class);
        }

        return null;
    }

    private List<User> fetchUsers(JSONObject jsonObject) throws IOException {
        if (!jsonObject.has("results"))
            return null;
        return objectMapper.readValue(jsonObject.get("results").toString(), new TypeReference<List<User>>() {
        });
    }

    private User fetchUser(JSONObject jsonObject) throws IOException {
        if (!jsonObject.has("result"))
            return null;
        return objectMapper.readValue(jsonObject.getJSONObject("result").toString(), User.class);
    }


    private List<Role> fetchRoles(JSONObject jsonObject) throws IOException {
        if (!jsonObject.has("results"))
            return null;
        return objectMapper.readValue(jsonObject.get("results").toString(), new TypeReference<List<Role>>() {
        });
    }

    public Response getAllUsers() {

        Response response = null;

        try {

            JSONObject responseJSON = methodGET(GET_ALL_USERS_URI);

            response = new Response();

            Summary summary = fetchSummary(responseJSON);

            setSummaryToResponse(summary, response);

            List users = fetchUsers(responseJSON);

            response.setRoleOrUser(users);

            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public Response register(String email, String password, String firstName, String lastName) {

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair(EMAIL_PARAM, email));
        params.add(new BasicNameValuePair(PASSWORD_PARAM, password));
        params.add(new BasicNameValuePair(FIRST_NAME_PARAM, firstName));
        params.add(new BasicNameValuePair(LAST_NAME_PARAM, lastName));

        JSONObject responseJSON = null;
        Summary summary = null;
        User user = null;

        try {

            responseJSON = methodPOST(REGISTER_USER_URI, params);
            summary = fetchSummary(responseJSON);
            user = fetchUser(responseJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = new Response();

        if (user != null) {
            List users = new ArrayList<>();
            users.add(user);
            response.setRoleOrUser(users);
        }

        setSummaryToResponse(summary, response);

        return response;

    }


    public Response login(String email, String password) {

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair(EMAIL_PARAM, email));
        params.add(new BasicNameValuePair(PASSWORD_PARAM, password));

        JSONObject responseJSON = null;
        Summary summary = null;
        User user = null;

        try {

            responseJSON = methodPOST(LOGIN_USER_URI, params);
            summary = fetchSummary(responseJSON);
            user = fetchUser(responseJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = new Response();

        if (user != null) {
            List users = new ArrayList<User>();
            users.add(user);
            response.setRoleOrUser(users);
        }

        setSummaryToResponse(summary, response);

        return response;
    }

    private void setSummaryToResponse(Summary summary, Response response) {
        if (summary instanceof SummarySuccess) {

            response.setSummarySuccess((SummarySuccess) summary);

        } else if (summary instanceof SummaryFault) {

            response.setSummaryFault((SummaryFault) summary);
        }
    }


    public Response authorize(String email, String password) {

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair(EMAIL_PARAM, email));
        params.add(new BasicNameValuePair(PASSWORD_PARAM, password));

        JSONObject responseJSON = null;
        Summary summary = null;
        List roles = null;

        try {

            responseJSON = methodPOST(AUTHORIZE_USER_URI, params);
            summary = fetchSummary(responseJSON);
            roles = fetchRoles(responseJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = new Response();

        setSummaryToResponse(summary, response);

        response.setRoleOrUser(roles);

        return response;
    }


    public Response getUsersByRoleName(String roleName) {

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair(ROLE_NAME_PARAM, roleName));

        JSONObject responseJSON = null;
        Summary summary = null;
        List users = null;

        try {

            responseJSON = methodPOST(GET_USERS_BY_ROLE_NAME_URI, params);
            summary = fetchSummary(responseJSON);
            users = fetchUsers(responseJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = new Response();


        setSummaryToResponse(summary, response);


        response.setRoleOrUser(users);

        return response;
    }


    public Response deleteUserById(String id) {

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair(ID_PARAM, id));

        JSONObject responseJSON = null;
        Summary summary = null;

        try {

            responseJSON = methodPOST(DELETE_USER_URI, params);
            summary = fetchSummary(responseJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = new Response();

        setSummaryToResponse(summary, response);

        return response;
    }


//    public static void main(String[] args) {
//        UserServiceRSHTTPClient userServiceRSHTTPClient = new UserServiceRSHTTPClient();
////        System.out.println(userServiceRSHTTPClient.register("olehMaks@gmail.com", "qwerty", "agds", "fdfs"));
////        System.out.println(userServiceRSHTTPClient.authorize("olehMaks@gmail.com", "qwerty"));
//
//        System.out.println(userServiceRSHTTPClient.deleteUserById("1456"));
//    }
}
