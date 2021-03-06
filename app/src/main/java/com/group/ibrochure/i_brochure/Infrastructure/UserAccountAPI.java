package com.group.ibrochure.i_brochure.Infrastructure;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.group.ibrochure.i_brochure.Domain.UserAccount.IUserAccountRepo;
import com.group.ibrochure.i_brochure.Domain.UserAccount.UserAccount;
import com.group.ibrochure.i_brochure.UI.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yogi on 05/11/2017.
 */

public class UserAccountAPI extends BaseAPI<UserAccount> implements IUserAccountRepo {

    public UserAccountAPI(Context context) {
        super(context);
    }

    @Override
    public void Save(final ResponseCallBack responseCallBack, final UserAccount entity) {
        if (entity.getId() == 0) {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            responseCallBack.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            responseCallBack.onError(errorResponseHandler(error));
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Username", entity.getUsername());
                    params.put("Email", entity.getEmail());
                    params.put("Password", entity.getPassword());

                    return params;
                }
            };
            RequestHandler.getInstance(context).addToRequestQueue(postRequest);
        } else {
            StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            responseCallBack.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            responseCallBack.onError(errorResponseHandler(error));
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Id", String.valueOf(entity.getId()));
                    params.put("Name", entity.getName());
                    params.put("Contact", entity.getContact());
                    params.put("Telephone", entity.getTelephone());
                    params.put("Address", entity.getAddress());
                    params.put("Picture", entity.getPicture());

                    return params;
                }
            };
            RequestHandler.getInstance(context).addToRequestQueue(putRequest);
        }
    }

    @Override
    public UserAccount CreateNew() {
        return new UserAccount();
    }

    @Override
    public String GetUrl() {
        return URLs.URL_USERACCOUNT;
    }

    @Override
    public void Login(final ResponseCallBack responseCallBack, final String userOrEmail, final String password) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url + "login/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseCallBack.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseCallBack.onError(errorResponseHandler(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("UserOrEmail", userOrEmail);
                params.put("Password", password);

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(postRequest);
    }

    @Override
    public void GetByUsername(final ResponseCallBack responseCallBack, final String username) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url + "getByUsername/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseCallBack.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseCallBack.onError(errorResponseHandler(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", username);

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(postRequest);
    }
}
