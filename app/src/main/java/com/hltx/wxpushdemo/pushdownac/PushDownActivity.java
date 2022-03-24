package com.hltx.wxpushdemo.pushdownac;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.google.android.material.appbar.AppBarLayout;
import com.hltx.wxpushdemo.databinding.ActivytyPushDownBinding;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author leeskyyou
 * @description:
 * @date : 2022/3/23 2:53 下午
 */
public class PushDownActivity extends AppCompatActivity {

    public ActivytyPushDownBinding binding;

    public ArrayList<ChatModel> chatModels = new ArrayList<>();
    public ChatAdapter chatAdapter;

    private ViewGroup.LayoutParams layoutParams;
    private Integer anWith;
    private Integer anheight;
    private Integer scWidth;
    private Integer scHeight;
    private Integer currentHeight = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivytyPushDownBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.appbar.setExpanded(false);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            scWidth = getWindowManager().getCurrentWindowMetrics().getBounds().width();
            scHeight = getWindowManager().getCurrentWindowMetrics().getBounds().height();
        } else {
            scWidth = getWindowManager().getDefaultDisplay().getWidth();
            scHeight = getWindowManager().getDefaultDisplay().getHeight();
        }

        anWith = binding.processView.getLayoutParams().width;
        anheight = binding.processView.getLayoutParams().height;
        layoutParams = binding.processView.getLayoutParams();
        //
        initSubview();
    }

    public void  initSubview(){
        chatAdapter = new ChatAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //从底部开始显示
//        linearLayoutManager.setStackFromEnd(true);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(chatAdapter);

        String[] strs = {"1fdsfds","在吗","哈哈哈","好的。。。。","一定","阔以。。。。。。。。"};
        for (int i = 0; i < 20; i++) {
            ChatModel chatModel = new ChatModel();
            chatModel.content = strs[i%6] + "第----"+ i;
            chatModels.add(chatModel);
        }
        //数据
        chatAdapter.setNewInstance(chatModels);

//        binding.smartRefresh.setRefreshHeader(new ClassicsHeader(this));
////        binding.smartRefresh.setRefreshFooter(new ClassicsFooter(this));
//        binding.smartRefresh.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                //获取上一条  插入-----
////                chatModels.addAll(0,mockeNewMessage());
////                chatAdapter.notifyItemInserted(0);
////                binding.smartRefresh.finishRefresh();
//                binding.appbar.setExpanded(true);
//            }
//        });

        //获取初始verticalOffset

        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i("位置变化-----",verticalOffset + "");
                Integer scrollY = scHeight + verticalOffset;



                if (scrollY > 0){
                    if (scrollY > (scHeight/2)){
                        //设置透明度
                        binding.processView.setVisibility(View.GONE);
                    }else{
                        binding.processView.setVisibility(View.VISIBLE);
                        processWith((scrollY/8));
                    }
                }
            }
        });



    }

    public void processWith(int progress){
        if (currentHeight == progress){
            Log.i("布局未修改","----");
            return;
        }
        currentHeight = progress;
//        layoutParams.width =  anWith + progress;
        layoutParams.height = anheight + progress;

        Log.i("得到process参数-----",progress+"" + "宽度---->"+ anWith + "高度--->" + layoutParams.height);
//        ViewGroup.LayoutParams nlayoutParams = new ViewGroup.LayoutParams(layoutParams);
//        nlayoutParams.width = width;
//        nlayoutParams.height = height;

        binding.processView.setLayoutParams(layoutParams);
    }


    public void  insertNewMessage(){
        ChatModel chatModel = new ChatModel();
        chatModel.content = "这是第----" + (chatModels.size()+1) + "消息";
        chatModels.add(chatModels.size(),chatModel);
        chatAdapter.notifyItemInserted(chatModels.size());
    }

    public ArrayList<ChatModel> mockeNewMessage(){
        ArrayList<ChatModel> modelArrayList = new ArrayList<>();
        String[] strs = {"1fdsfds","在吗","哈哈哈","好的。。。。","一定","阔以。。。。。。。。"};
        for (int i = 0; i < 10; i++) {
            ChatModel chatModel = new ChatModel();
            chatModel.content = strs[i%6];
            modelArrayList.add(chatModel);
        }
        return modelArrayList;
    }


}
