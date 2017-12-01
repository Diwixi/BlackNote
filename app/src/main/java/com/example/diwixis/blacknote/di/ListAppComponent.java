package com.example.diwixis.blacknote.di;

import com.example.diwixis.blacknote.presentations.todoList.ListPresenter;
import com.example.diwixis.blacknote.presentations.todoListItem.ListItemPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Diwixis on 08.08.2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface ListAppComponent {
    void inject(ListPresenter presenter);
    void inject(ListItemPresenter presenter);
}
