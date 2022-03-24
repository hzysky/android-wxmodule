package com.hltx.wxpushdemo.animationStudy;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.SeekBar;


import com.hltx.wxpushdemo.databinding.ActivityAnimationHomeBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author leeskyyou
 * @description:
 * @date : 2022/3/23 5:55 下午
 */
public class AnimationTestAc extends AppCompatActivity {

    public ActivityAnimationHomeBinding binding;

    private ViewGroup.LayoutParams layoutParams;
    private Integer anWith;
    private Integer anheight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnimationHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        layoutParams = binding.anView.getLayoutParams();
        anWith = layoutParams.width;
        anheight = layoutParams.height;
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    processWith(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //开始拖动
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void processWith(int progress){

        layoutParams.width =  anWith + progress;
        layoutParams.height = anheight + progress;
        Log.i("得到process参数-----",progress+"" + "宽度---->"+ layoutParams.width + "高度--->" + layoutParams.height);
        binding.anView.setLayoutParams(layoutParams);
    }

}
