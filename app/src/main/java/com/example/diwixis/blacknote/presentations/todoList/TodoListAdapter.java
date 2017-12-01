package com.example.diwixis.blacknote.presentations.todoList;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diwixis.blacknote.data.Note;
import com.example.diwixis.blacknote.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Diwixis on 06.08.2017.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> implements RealmChangeListener{
    private ClickListener itemListener;
    private RealmResults<Note> notes;


    TodoListAdapter(RealmResults<Note> notes) {
        this.notes = notes;
    }

    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoListAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.message.setText(note.getMessage());
        holder.title.setText(note.getMessageTitle());
        if (note.isFlag()){
            holder.icon.setImageResource(note.getImageResId());
        } else {
            holder.icon.setImageResource(note.getNextImageResId());
        }
        holder.checkBox.setChecked(note.isFlag());
        holder.note = note;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    void setOnItemClickListener(ClickListener listener){
        itemListener = listener;
    }

    @Override
    public void onChange(Object o) {
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {
        @BindView(R.id.text_item_title) TextView title;
        @BindView(R.id.text_item)       TextView message;
        @BindView(R.id.image_item)      ImageView icon;
        @BindView(R.id.check_item)      CheckBox checkBox;
        Note note;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            checkBox.setOnClickListener(view -> {
                if (checkBox.isChecked()) {
                    icon.setImageResource(note.getImageResId());
                } else {
                    icon.setImageResource(note.getNextImageResId());
                }
                itemListener.updateItem(note, checkBox.isChecked());
            });

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.onItemClick(note);
        }

        @Override
        public boolean onLongClick(View view) {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.inflate(R.menu.popup_menu);
            popup.setOnMenuItemClickListener(ViewHolder.this);
            popup.show();
            return true;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getTitle().toString()) {
                case "Edit": {
                    itemListener.onItemClick(note);
                    return true;
                }
                case "Delete": {
                    itemListener.deleteListener(note);
                    return true;
                }
                default:
                    return false;
            }
        }
    }

    interface ClickListener {
        void onItemClick(Note note);

        void deleteListener(Note note);

        void updateItem(Note note);

        void updateItem(Note note, boolean flag);
    }
}
