package com.example.diwixis.blacknote.data;

import android.support.annotation.NonNull;

import java.util.Arrays;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Diwixis on 08.08.2017.
 */

public class DbRepository implements IDbRepository {

    @Inject
    DbRepository() {
    }

    @NonNull
    @Override
    public RealmResults<Note> getNotes() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        String[] fields = new String[]{"flag", "id"};
        Sort[] sorted = new Sort[]{Sort.ASCENDING, Sort.DESCENDING};
        RealmResults<Note> results = realm.where(Note.class)
                .findAllSorted(fields, sorted);
        realm.commitTransaction();
        return results;
    }

    @Override
    public Note getNoteById(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Note results = realm.where(Note.class).equalTo("id", id).findFirst();
        realm.commitTransaction();
        return realm.copyFromRealm(results);
    }

    @Override
    public void setNote(Note note) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(note);
        realm.commitTransaction();
    }

    @Override
    public void deleteNote(Note note) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Note.class).equalTo("id", note.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    public void updateFlag(Note note, boolean flag) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> note.setFlag(flag));
    }
}