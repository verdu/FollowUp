package com.followup.arielverdugo.followup;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by arielverdugo on 5/7/17.
 */

public class SessionManager
{

        private SharedPreferences prefs;

        private static final String PREFS_NAME = "_SESSION_", EMAIL = "_EMAIL_";

        public SessionManager(Context context) {
            prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }

        public void guardarEmail(String email) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(EMAIL, email);
            editor.apply();
        }


        public boolean estaLogueado() {
            return !prefs.getString(EMAIL, "").isEmpty();
        }


        public String getEmail() {
            return prefs.getString(EMAIL, "");
        }

        public void logout() {
            SharedPreferences.Editor editor = prefs.edit();

            editor.remove(EMAIL);

            editor.apply();
        }
    }

