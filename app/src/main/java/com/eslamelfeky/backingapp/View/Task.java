package com.eslamelfeky.backingapp.View;

import com.google.android.exoplayer2.SimpleExoPlayer;

public interface Task {
     void hideActionBar();
    int checkScreenWidth();
    void setFrameLayoutViability(boolean Viability);
    void onBackClick(boolean enable);
    void fullScreen();
}
