package com.example.diwixis.blacknote.presentations.todoListItem;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.diwixis.blacknote.data.Note;

/**
 * Created by Diwixis on 08.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface IListItemView extends MvpView {
    void fillingFields(Note note);
}
