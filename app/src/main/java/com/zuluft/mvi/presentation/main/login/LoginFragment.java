package com.zuluft.mvi.presentation.main.login;


import android.os.Bundle;

import com.zuluft.mvi.R;
import com.zuluft.mvi.base.annotations.LayoutResourceId;
import com.zuluft.mvi.base.fragments.BaseFragment;

import javax.annotation.Nonnull;

@LayoutResourceId(R.layout.fragment_login)
public class LoginFragment
        extends
        BaseFragment<LoginViewState, LoginPresenter>
        implements
        LoginView {
    @Override
    protected void reflectState(@Nonnull final LoginViewState state) {

    }

    @Override
    protected void onPresenterReady(@Nonnull final LoginPresenter presenter,
                                    boolean isFirstAttach) {
        presenter.attach(this, isFirstAttach);
    }

    @Override
    protected void renderView(Bundle savedInstanceState) {

    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
