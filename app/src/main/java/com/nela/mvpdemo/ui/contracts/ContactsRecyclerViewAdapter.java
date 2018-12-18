package com.nela.mvpdemo.ui.contracts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nela.mvpdemo.R;
import com.nela.mvpdemo.contract.contracts.ContactsContract;
import com.nela.mvpdemo.utils.CircleDrawables;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {

    private ContactsContract.Presenter mPresenter;

    public ContactsRecyclerViewAdapter(ContactsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_contact, parent, false);
        if (viewType == 1) {
            view.findViewById(R.id.message_text_response).setVisibility(View.VISIBLE);
            view.findViewById(R.id.message_voice_response).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.message_text_response).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.message_voice_response).setVisibility(View.VISIBLE);
        }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return mPresenter.getItemResponseType();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ContactsContract.Presenter.ItemDraw item = mPresenter.getItemDraw(position);
        if (item != null) {
            holder.position = position;
            holder.mSection.setVisibility(item.showSection ? View.VISIBLE : View.GONE);
            holder.mSection.setText(item.section);
            holder.mName.setText(item.name);
            holder.mNumber.setText(item.number);
            if (item.header != null) {
                holder.mAvatarName.setVisibility(View.INVISIBLE);
//                Glide.with(AppData.getContext())
//                        .load(Uri.parse(item.header))
//                        .asBitmap()
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .placeholder(CircleDrawables.getDefault())
//                        .transform(new GlideCircleTransform(AppData.getContext()))
//                        .into(holder.mAvatar);
            } else {
                holder.mAvatarName.setVisibility(View.VISIBLE);
                if (item.name.length() >= 2) {
                    holder.mAvatarName.setText(item.name.substring(item.name.length() - 2, item.name.length()));
                } else {
                    holder.mAvatarName.setText(item.name);
                }
                holder.mAvatar.setImageDrawable(CircleDrawables.getDefault());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPresenter.getDataCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_contact_item_section)
        TextView mSection;
        @BindView(R.id.txt_contact_item_name)
        TextView mName;
        @BindView(R.id.txt_contact_item_number)
        TextView mNumber;
        @BindView(R.id.img_avatar)
        ImageView mAvatar;
        @BindView(R.id.txt_avatar_name)
        TextView mAvatarName;

        @BindView(R.id.message_voice_response)
        ImageView mVoiceResponse;
        @BindView(R.id.message_text_response)
        ImageView mTextResponse;

        private int position;

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
            mPresenter.showContactsDetailsActivity(position);
        }

        @OnClick(R.id.view_contact_item)
        void onClickCallItem(View view) {
            mPresenter.call(position);
        }

        @OnLongClick(R.id.view_contact_item)
        boolean onLongClickConferenceItem(View view) {
            return false;
        }

        @OnClick(R.id.message_text_response)
        public void onMessageTextResponse() {
            mPresenter.showTextResponseUI(position);
        }
    }
}