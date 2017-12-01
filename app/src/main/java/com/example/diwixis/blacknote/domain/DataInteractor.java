package com.example.diwixis.blacknote.domain;

import com.example.diwixis.blacknote.data.DbRepository;
import com.example.diwixis.blacknote.data.Note;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by Diwixis on 08.08.2017.
 */

public class DataInteractor {
    @Inject    DbRepository dbRepository;

    @Inject    DataInteractor() {}

    public RealmResults<Note> getNotesFromDb() {
        return dbRepository.getNotes();
    }

    public void deleteNote(Note note) {
        dbRepository.deleteNote(note);
    }

    public void updateNote(Note note) {
        dbRepository.setNote(note);
    }

    public void updateFlag(Note note, boolean flag) {
        dbRepository.updateFlag(note, flag);
    }
}
