package com.aditya.wishnetlogin;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.aditya.wishnetlogin.prefs.LoginSelectionDTO;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Aditya on 10/12/2016.
 */
public class ConnectLoginAsyncTask extends AsyncTask<LoginSelectionDTO,Void,Integer> {

    private Button button;

    public ConnectLoginAsyncTask(Button loginBtn) {
        this.button = loginBtn;
    }


    @Override
    protected Integer doInBackground(LoginSelectionDTO... loginSelectionDTO) {

        publishProgress();

        LoginSelectionDTO loginData = loginSelectionDTO[0];

        //open default page
    try {


        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody formBody = new FormBody.Builder()
                .add("username", loginData.getUserName())
                .add("password", loginData.getPassword())
                .build();

        Request.Builder builder = new Request.Builder()
 //               .url("http://2.2.2.2/login")
.url("http://172.18.77.1/login")


                .post(formBody);
        Request request = builder.build();

        Response response = client.newCall(request).execute();
        response.body().close();

        //login

    }catch(Exception ex){
        Log.e("",ex.toString());
        ex.printStackTrace();
        return -1;
    }


        return 1;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        this.button.setEnabled(false);
    }

    @Override
    protected void onPostExecute(Integer aVoid) {

        this.button.setEnabled(true);
        if(aVoid==-1){
            Toast.makeText(this.button.getContext(),"Error in Connection. Try Again",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.button.getContext(),"Successfully Logged in.",Toast.LENGTH_SHORT).show();
        }

    }
}
