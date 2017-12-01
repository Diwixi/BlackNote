package com.example.diwixis.blacknote.data;

import com.example.diwixis.blacknote.R;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Diwixis on 06.08.2017.
 */

public class Note  extends RealmObject{
    public final static String extraColorRes = "COLOR_RES_EXTRA";
    public final static String extraNextColorRes = "NEXT_COLOR_RES_EXTRA";
    public final static String extraMessage = "MESSAGE_EXTRA";
    public final static String extraMessageTitle = "MESSAGE_TITLE_EXTRA";
    public final static String extraIsChecked = "CHECKED_EXTRA";
    public final static String extraId = "ID_EXTRA";

    @PrimaryKey
    private int id;

    private int imageResId;
    private int nextImageResId;

    @Required
    private String messageTitle;
    private String message;

    private boolean flag;

    public Note() {
        id = getNextKey();
        imageResId = R.color.holo_green_dark;
        nextImageResId = R.color.holo_red_dark;
        message = "";
        messageTitle = "";
        flag = false;
    }

    public Note(int imageResId, int nextImageResId, String message, String messageTitle, boolean flag) {
        id = getNextKey();
        this.imageResId = imageResId;
        this.nextImageResId = nextImageResId;
        this.message = message;
        this.messageTitle = messageTitle;
        this.flag = flag;
    }

    public Note(int id, int imageResId, int nextImageResId, String message, String messageTitle, boolean flag) {
        this.id = id;
        this.imageResId = imageResId;
        this.nextImageResId = nextImageResId;
        this.message = message;
        this.messageTitle = messageTitle;
        this.flag = flag;
    }

    private int getNextKey() {
        try {
            Realm realm = Realm.getDefaultInstance();
            Number number = realm.where(Note.class).max("id");
            if (number != null) {
                return number.intValue() + 1;
            } else {
                return 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getNextImageResId() {
        return nextImageResId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getId() {
        return id;
    }

    public void setMessageTitle (String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    void setFlag(boolean flag) {
        this.flag = flag;
    }
}
