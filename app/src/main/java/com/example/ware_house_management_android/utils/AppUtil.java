package com.example.ware_house_management_android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.ware_house_management_android.dtos.auth.JWTPayloadDecoded;
import com.example.ware_house_management_android.models.UserModel;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AppUtil {
    private AppUtil() {
    }

    public static UserModel currentUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (TextUtils.isEmpty(userJson)) {
            return null;
        }

        Gson gson = new Gson();
        JWTPayloadDecoded jwtPayloadDecoded = gson.fromJson(userJson, JWTPayloadDecoded.class);

        return new UserModel(jwtPayloadDecoded.getId(), jwtPayloadDecoded.getFullName(), jwtPayloadDecoded.getEmail(), jwtPayloadDecoded.getRole());
    }

    public static void clearCurrentUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("user");
        editor.apply();
    }

    public static String dateToLocaleString(Date date) {
        if (date == null) {
            return "";
        }

        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
    }

    public static String convertToMongoDbIsoDate(String inputDate) throws Exception {
        // Parse the input date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = inputFormat.parse(inputDate);

        // Format to MongoDB ISODate format
        SimpleDateFormat mongoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        mongoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return mongoFormat.format(date);
    }

    public static <T> void observeOnce(@NonNull LiveData<T> liveData, @NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        liveData.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                observer.onChanged(t);
                liveData.removeObserver(this);
            }
        });
    }
}

