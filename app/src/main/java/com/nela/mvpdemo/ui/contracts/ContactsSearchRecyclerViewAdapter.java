package com.nela.mvpdemo.ui.contracts;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nela.mvpdemo.R;
import com.nela.mvpdemo.contract.contracts.ContactsContract;
import com.nela.mvpdemo.utils.CircleDrawables;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ContactsSearchRecyclerViewAdapter extends RecyclerView.Adapter<ContactsSearchRecyclerViewAdapter.ViewHolder> {

    ContactsContract.Presenter mPresenter;

    public ContactsSearchRecyclerViewAdapter(ContactsContract.Presenter presenter) {
        super();
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ContactsSearchRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contact, parent, false);
        return new ContactsSearchRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ContactsContract.Presenter.ItemDraw item = mPresenter.getSearchItemDraw(position);
        if (item != null) {
            viewHolder.position = position;
            viewHolder.mSection.setVisibility(item.showSection ? View.VISIBLE : View.GONE);
            viewHolder.mSection.setText(item.section);
            SpannableString number = text2HighFont(item.number, item.searchKey);
            viewHolder.mName.setText(item.name);
            viewHolder.mNumber.setText(number);
            viewHolder.mCloud.setVisibility(item.showCloud ? View.VISIBLE : View.INVISIBLE);
            if (item.header != null) {
                viewHolder.mAvatarName.setVisibility(View.INVISIBLE);
//                Glide.with(AppData.getContext())
//                        .load(Uri.parse(item.header))
//                        .asBitmap()
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .placeholder(CircleDrawables.getDefault())
//                        .transform(new GlideCircleTransform(AppData.getContext()))
//                        .into(holder.mAvatar);
            } else {
                viewHolder.mAvatarName.setVisibility(View.VISIBLE);
                if (item.name.length() >= 2) {
                    viewHolder.mAvatarName.setText(item.name.substring(item.name.length() - 2, item.name.length()));
                } else {
                    viewHolder.mAvatarName.setText(item.name);
                }
                viewHolder.mAvatar.setImageDrawable(CircleDrawables.getDefault());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPresenter.getSearchDataCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_contact_item_section)
        TextView mSection;
        @BindView(R.id.txt_contact_item_name)
        TextView mName;
        @BindView(R.id.txt_contact_item_number)
        TextView mNumber;
        @BindView(R.id.img_cloud)
        ImageView mCloud;
        @BindView(R.id.img_avatar)
        ImageView mAvatar;
        @BindView(R.id.txt_avatar_name)
        TextView mAvatarName;
        int position;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        @Override
        public String toString() {
            return super.toString();
        }

        @OnClick(R.id.view_avatar)
        void onClickHead(View view) {
            mPresenter.showContactsDetailsActivityFromSearch(position);
        }

        @OnClick(R.id.view_contact_item)
        void onClickCallItem(View view) {

        }

        @OnLongClick(R.id.view_contact_item)
        boolean onLongClickConferenceItem(View view) {
            return false;
        }
    }

    //关键字高亮
    private SpannableString text2HighFont(String string, String highString) {
        SpannableString highFont = new SpannableString(string);
        Pattern p = Pattern.compile(highString);
        Matcher m = p.matcher(string);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            highFont.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return highFont;
    }
}
