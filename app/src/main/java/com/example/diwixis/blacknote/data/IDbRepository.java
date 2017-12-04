package com.example.diwixis.blacknote.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Diwixis on 08.08.2017.
 */

interface IDbRepository {
    @NonNull
    List<Note> getNotes();

    Note getNoteById(int id);

    void setNote(Note note);

    void deleteNote(Note note);

    void updateFlag(Note note, boolean flag);
}
