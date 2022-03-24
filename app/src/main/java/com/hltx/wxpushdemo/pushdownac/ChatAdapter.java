package com.hltx.wxpushdemo.pushdownac;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hltx.wxpushdemo.R;


import androidx.annotation.NonNull;

/**
 * @author leeskyyou
 * @description:
 * @date : 2022/3/22 11:14 上午
 */
public class ChatAdapter extends BaseQuickAdapter<ChatModel, BaseViewHolder> {

    public ChatAdapter() {
        super(R.layout.adapter_chat_cell);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ChatModel chatModel) {
        baseViewHolder.setText(R.id.content_label,chatModel.content);
    }
}
