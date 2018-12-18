package com.nela.mvpdemo.ui.contracts;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nela.mvpdemo.R;
import com.nela.mvpdemo.base.BaseFragment;
import com.nela.mvpdemo.contract.contracts.ContactsContract;
import com.nela.mvpdemo.presenter.contracts.ContractsPresenter;
import com.nela.mvpdemo.ui.view.LetterIndexView;

import butterknife.BindView;

public class ContactsFragment extends BaseFragment<ContactsContract.Presenter> implements ContactsContract.View, LetterIndexView.OnTouchingLetterChangedListener {
    public static final int REPLAY_TYPE_TEXT = 1;
    public static final int REPLAY_TYPE_VOICE = 0;

    @BindView(R.id.list_contacts)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_letters)
    LetterIndexView mLetterIndexView;
    @BindView(R.id.txt_letter)
    TextView mLetter;
    @BindView(R.id.sv_search)
    SearchView mSearchView;

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected ContactsContract.Presenter setPresenter() {
        return new ContractsPresenter(this);
    }

    @Override
    protected void initViews() {
        mPresenter.start();
        mSearchView.onActionViewExpanded();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mPresenter.startSearch(s);
                return true;
            }
        });
        mSearchView.setFocusable(false);
        mSearchView.clearFocus();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @Override
    public void onDataChange() {
        if (mRecyclerView.getAdapter() != null) {
            if (mRecyclerView.getAdapter().getItemCount() != 0) {
                mRecyclerView.getAdapter().notifyDataSetChanged();
            } else {
            }
        }
    }

    @Override
    public void onHit(String letter) {
        mLetter.setVisibility(View.VISIBLE);
        mLetter.setText(letter.toUpperCase());
        mRecyclerView.scrollToPosition(mPresenter.getSectionPos(letter));
    }

    @Override
    public void onCancel() {
        mLetter.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSearchList() {
        mRecyclerView.setAdapter(new ContactsSearchRecyclerViewAdapter(mPresenter));
        mLetterIndexView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showContactsList() {
        mRecyclerView.setAdapter(new ContactsRecyclerViewAdapter(mPresenter));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int position = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                mLetterIndexView.setCurLetter(mPresenter.getSectionByIndex(position));
            }
        });
        mLetter.setVisibility(View.INVISIBLE);
        mLetterIndexView.setOnTouchingLetterChangedListener(this);
        mLetterIndexView.setupLetters(mPresenter.getAllSectionPos());
    }

    @Override
    public void showCallActivity(String number, boolean video) {
        Toast.makeText(mContext, "showCallActivity : " + number, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startContactsDetailsActivity(String number) {
        Toast.makeText(mContext, "startContactsDetailsActivity: " + number, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTextResponseUI(String uid) {
        Toast.makeText(mContext, "showTextResponseUI: " + uid, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showInfo(int info) {

    }

    public void changeMessageReplayType(int type) {
        mPresenter.setItemResponseType(type);
        onDataChange();
    }
}
