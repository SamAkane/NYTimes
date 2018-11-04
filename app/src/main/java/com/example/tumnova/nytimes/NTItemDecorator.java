package com.example.tumnova.nytimes;

import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class NTItemDecorator extends RecyclerView.ItemDecoration {
    private int offset;
    private int columnNumber;

    public NTItemDecorator(Context context, int dpSize, int columnNumber) {
        offset = (context.getResources().getDimensionPixelOffset(dpSize));
        this.columnNumber = columnNumber;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) < columnNumber){
            outRect.set(offset, offset, offset, offset);
        } else {
            outRect.set(offset, 0, offset, offset);
        }
    }
}
