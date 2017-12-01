package com.example.diwixis.blacknote.presentations.todoListItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.diwixis.blacknote.R;
import com.example.diwixis.blacknote.data.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Diwixis on 08.08.2017.
 */

public class ListItemActivity extends MvpAppCompatActivity implements IListItemView{

    @InjectPresenter ListItemPresenter presenter;

    @BindView(R.id.item_message)          EditText textMessage;
    @BindView(R.id.item_message_title)    EditText textMessageTitle;

    private Note lastNote;

    public static void startActivity(Activity activity, Note note){
        Intent intent = new Intent(activity, ListItemActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Note.extraColorRes, note.getImageResId());
        intent.putExtra(Note.extraNextColorRes, note.getNextImageResId());
        intent.putExtra(Note.extraMessage, note.getMessage());
        intent.putExtra(Note.extraMessageTitle, note.getMessageTitle());
        intent.putExtra(Note.extraIsChecked, note.isFlag());
        intent.putExtra(Note.extraId, note.getId());
        activity.startActivity(intent);
    }

    private void saveNote() {
        lastNote.setMessageTitle(textMessageTitle.getText().toString());
        lastNote.setMessage(textMessage.getText().toString());
        presenter.updateNote(lastNote);
    }

    private void backAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention")
                .setMessage("Do you wish to save changes?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    if (!textMessageTitle.getText().toString().isEmpty()) {
                        saveNote();
                        dialog.cancel();
                        ListItemActivity.this.finish();
                    } else {
                        emptyFieldAlertDialog();
                    }
                })
                .setNegativeButton("No",
                        (dialog, id) -> {
                            dialog.cancel();
                            ListItemActivity.this.finish();
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void emptyFieldAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage("You can't save the message with a blank title.")
                .setCancelable(false)
                .setNegativeButton("ОК",(dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        if (!lastNote.getMessage().equals(textMessage.getText().toString())){
            backAlertDialog();
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_activity);
        ButterKnife.bind(this);

        presenter.canShow(getIntent());
    }

    @Override
    public void fillingFields(Note note) {
        textMessage.setText(note.getMessage());
        textMessageTitle.setText(note.getMessageTitle());
        lastNote = note;
    }

    @OnClick(R.id.done_button)
    void onClickDoneButton(){
        if (!textMessageTitle.getText().toString().isEmpty()) {
            saveNote();
            finish();
        } else {
            emptyFieldAlertDialog();
        }
    }


    @OnClick(R.id.revert_button)
    void onClickRevertButton(){
        finish();
    }

    @OnClick(R.id.addImageButton)
    void onClickAddImageButton(){

    }
}
