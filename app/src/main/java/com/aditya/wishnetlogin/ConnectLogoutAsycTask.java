package com.aditya.wishnetlogin;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.aditya.wishnetlogin.prefs.LoginSelectionDTO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Aditya on 10/12/2016.
 */
public class ConnectLogoutAsycTask extends AsyncTask<LoginSelectionDTO,Void,Integer> {

    private Button mButton;

    public ConnectLogoutAsycTask(Button mButton) {
        this.mButton = mButton;
    }



    @Override
    protected Integer doInBackground(LoginSelectionDTO... loginSelectionDTOs) {

        publishProgress();
    try {

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("http://192.168.183.201:9086/Kolkata4/WISHN/logoutUI.do3?sessUserName="+loginSelectionDTOs[0].getUserName())
                .build();
        Response response = client.newCall(request).execute();
        response.body().close();
    }catch (Exception ex){
        Log.e(ConnectLogoutAsycTask.class.getName(),ex.getMessage());
        return -1;
    }

        return 1;
    }

    @Override
    protected void onPostExecute(Integer aVoid) {
        this.mButton.setEnabled(true);

        if(aVoid==-1){
            Toast.makeText(this.mButton.getContext(),"Error in Connection. Try Again",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.mButton.getContext(),"Successfully Logged out",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        this.mButton.setEnabled(false);
    }
}
