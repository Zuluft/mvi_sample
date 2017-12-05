package com.zuluft.mvi.base.annotations;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface LayoutResourceId {
    @LayoutRes int value();
}
