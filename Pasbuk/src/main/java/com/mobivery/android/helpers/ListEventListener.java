package com.mobivery.android.helpers;

import android.view.View;

public interface ListEventListener<T> {

    void onListEvent(int i, final T dto, View view);
}
