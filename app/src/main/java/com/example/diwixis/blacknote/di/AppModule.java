package com.example.diwixis.blacknote.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diwixis on 08.08.2017.
 */
@Module
public class AppModule {
    private final Context appContext;

    public AppModule(Context context) {
        appContext = context;
    }

    @Singleton
    @Provides
    Context providesContext() {
        return appContext;
    }
}
