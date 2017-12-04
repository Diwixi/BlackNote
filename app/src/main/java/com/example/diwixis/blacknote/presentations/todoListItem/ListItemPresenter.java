package com.example.diwixis.blacknote.presentations.todoListItem;

import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.diwixis.blacknote.App;
import com.example.diwixis.blacknote.data.Note;
import com.example.diwixis.blacknote.domain.DataInteractor;

import javax.inject.Inject;

/**
 * Created by Diwixis on 08.08.2017.
 */
@InjectViewState
public class ListItemPresenter extends MvpPresenter<IListItemView> {
    @Inject    DataInteractor dataInteractor;

    ListItemPresenter() {
        App.getListComponent().inject(this);
    }

    void canShow(Intent intent) {
        int id = intent.getIntExtra(Note.extraId, -1);
        Note note = (id == -1) ? new Note() : dataInteractor.getNoteById(id);
        getViewState().fillingFields(note);
    }

    void updateNote(Note note) {
        dataInteractor.updateNote(note);
    }
}
