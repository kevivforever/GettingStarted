package in.finder.gettingstarted.Utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by keviv on 31/01/2018.
 */

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;
    private int spanCount;

    public ItemOffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId, int spanCount) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column
        outRect.left = mItemOffset - column * mItemOffset / spanCount; // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * mItemOffset / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

        if (position < spanCount) { // top edge
            outRect.top = mItemOffset;
        }
        outRect.bottom = mItemOffset; // item bottom
    }
}
