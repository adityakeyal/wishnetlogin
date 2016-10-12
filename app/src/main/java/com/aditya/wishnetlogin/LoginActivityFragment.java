package com.aditya.wishnetlogin;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aditya.wishnetlogin.prefs.LoginSelectionDTO;
import com.aditya.wishnetlogin.util.Connectivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {

    public LoginActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        final Button loginBtn = (Button)rootView.findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSelectionDTO loginSelection = getLoginSelection();

                if(Connectivity.isConnectedWifi(getContext())) {
                    if (loginSelection.isValid()) {
                        //start new task
                        ConnectLoginAsyncTask async = new ConnectLoginAsyncTask(loginBtn);
                        async.execute(loginSelection);

                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "Configure you preferences from Settings", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getActivity().getBaseContext(), "Cannot process. Connect to WIFI", Toast.LENGTH_LONG).show();
                }
            }
        });


        Button logoutBtn = (Button)rootView.findViewById(R.id.logout_btn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSelectionDTO loginSelection = getLoginSelection();
                if (Connectivity.isConnectedWifi(getContext())) {
                    if (loginSelection.isValid()) {
                        //start new task
                        ConnectLogoutAsycTask async = new ConnectLogoutAsycTask(loginBtn);
                        async.execute(loginSelection);

                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "Configure you preferences from Settings", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Cannot process. Connect to WIFI", Toast.LENGTH_LONG).show();
                }
            }
        });




        /*final CircularProgressButton progresBtn = (CircularProgressButton)rootView.findViewById(R.id.login_btn);
        progresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSelectionDTO loginSelection = getLoginSelection();
                if(loginSelection.isValid()){
                    //start new task
                    ConnectLoginAsyncTask async = new ConnectLoginAsyncTask(progresBtn);
                    async.execute(loginSelection);

                }else{
                    Toast.makeText(getActivity().getBaseContext(),"Configure you preferences from Settings",Toast.LENGTH_LONG).show();
                }





            }
        });
*/
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    public LoginSelectionDTO getLoginSelection(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userName = sharedPreferences.getString(getString(R.string.pref_user_name), "");
        String password = sharedPreferences.getString(getString(R.string.pref_password),"");

        LoginSelectionDTO selection = new LoginSelectionDTO();
        selection.setPassword(password);
        selection.setUserName(userName);

        return selection;
    }
}
