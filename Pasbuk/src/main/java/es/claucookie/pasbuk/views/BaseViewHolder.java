package es.claucookie.pasbuk.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobivery.android.helpers.ListEventListener;

/**
 * Created by claucookie on 20/03/15.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected View rootView;
    protected Context context;

    public BaseViewHolder(Context context, View view) {
        super(view);
        this.rootView = view;
        this.context = context;
    }

    private ListEventListener<T> listEventListener;

    public void setListEventListener(ListEventListener<T> listEventListener) {
        this.listEventListener = listEventListener;
    }
    public ListEventListener<T> getListEventListener()
    {
        return this.listEventListener;
    }

    public abstract void bind(final T object);
}
