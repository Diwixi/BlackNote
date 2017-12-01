package com.example.diwixis.blacknote.data;

import android.support.annotation.NonNull;

import io.realm.RealmResults;

/**
 * Created by Diwixis on 08.08.2017.
 */

interface IDbRepository {
    @NonNull
    RealmResults<Note> getNotes();

    void setNote(Note note);

    void deleteNote(Note note);

    void updateFlag(Note note, boolean flag);
}
