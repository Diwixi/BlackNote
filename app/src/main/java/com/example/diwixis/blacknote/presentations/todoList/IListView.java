package com.example.diwixis.blacknote.presentations.todoList;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Diwixis on 04.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
interface IListView extends MvpView{
    void startItemActivity(int noteId);
    void startItemActivity();
}
