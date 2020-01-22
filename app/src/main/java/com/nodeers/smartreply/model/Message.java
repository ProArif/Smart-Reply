package com.nodeers.smartreply.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.nodeers.smartreply.R;

public class Message {

    public final String text;
    public final boolean isLocalUser;
    public final long timestamp;

    public Message(String text, boolean isLocalUser, long timestamp) {
        this.text = text;
        this.isLocalUser = isLocalUser;
        this.timestamp = timestamp;
    }

    @NonNull
    public Drawable getIcon(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_tag_faces_black_24dp);
        if (drawable == null) {
            throw new IllegalStateException("Could not get drawable ic_tag_faces_black_24dp");
        }

        //set drawable tint for colors
        drawable = DrawableCompat.wrap(drawable);
        int color = isLocalUser ? Color.BLUE : Color.RED;
        if (Build.VERSION.SDK_INT >= 22) {
            DrawableCompat.setTint(drawable, color);
        } else {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }

        return drawable;
    }
}
