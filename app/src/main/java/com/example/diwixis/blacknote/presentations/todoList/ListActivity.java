package com.example.diwixis.blacknote.presentations.todoList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.diwixis.blacknote.R;
import com.example.diwixis.blacknote.data.Note;
import com.example.diwixis.blacknote.presentations.todoListItem.ListItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Diwixis on 04.08.2017.
 */

public class ListActivity extends MvpAppCompatActivity implements IListView{
    public static final int EDIT = 101;
    public static final int DELETE = 102;

    @InjectPresenter ListPresenter presenter;


    @BindView(R.id.todo_list)       RecyclerView todoList;

    TodoListAdapter.ClickListener itemListener = new TodoListAdapter.ClickListener() {
        @Override
        public void onItemClick(Note note) {
            presenter.editItem(note);
        }

        @Override
        public void deleteListener(Note note) {
            presenter.deleteItem(note);
            todoList.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void updateItem(Note note) {
            presenter.updateItem(note);
        }

        @Override
        public void updateItem(Note note, boolean flag) {
            presenter.updateItemAndFlag(note, flag);
            todoList.getAdapter().notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        todoList.setLayoutManager( new LinearLayoutManager(this));
        todoList.addItemDecoration(new ItemDivider(this));
        TodoListAdapter listAdapter = new TodoListAdapter(presenter.getNotesFromDb());
        listAdapter.setOnItemClickListener(itemListener);
        todoList.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void startItemActivity(int noteId) {
        ListItemActivity.startActivity(this, noteId);
    }

    @Override
    public void startItemActivity() {
        ListItemActivity.startActivity(this);
    }

    @OnClick(R.id.addNewItem)
    void onClickAddButton(){
        presenter.newItem();
    }
}
