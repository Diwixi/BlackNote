package com.example.diwixis.blacknote.presentations.todoList;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.diwixis.blacknote.App;
import com.example.diwixis.blacknote.data.Note;
import com.example.diwixis.blacknote.domain.DataInteractor;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by Diwixis on 04.08.2017.
 */
@InjectViewState
public class ListPresenter extends MvpPresenter<IListView>{
    @Inject    DataInteractor dataInteractor;

    ListPresenter() {
        App.getListComponent().inject(this);
    }

    void editItem(Note note) {
        getViewState().startItemActivity(note.getId());
    }

    void newItem() {
        getViewState().startItemActivity();
    }

    RealmResults<Note> getNotesFromDb() {
        return dataInteractor.getNotesFromDb();
    }


    void deleteItem(Note note) {
        dataInteractor.deleteNote(note);
    }

    void updateItem(Note note) {
        dataInteractor.updateNote(note);
    }

    void updateItemAndFlag(Note note, boolean flag) {
        dataInteractor.updateFlag(note, flag);
    }
}
