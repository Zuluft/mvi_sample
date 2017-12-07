package com.zuluft.mvi.presentation.main.login;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zuluft.mvi.R;
import com.zuluft.mvi.base.annotations.LayoutResourceId;
import com.zuluft.mvi.base.fragments.BaseFragment;
import com.zuluft.mvi.presentation.main.login.dataModels.LoginDataModel;

import javax.annotation.Nonnull;

import io.reactivex.Observable;

@LayoutResourceId(R.layout.fragment_login)
public class LoginFragment
        extends
        BaseFragment<LoginViewState, LoginPresenter>
        implements
        LoginView {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView mTvErrorText;

    private ProgressDialog mProgressDialog;

    @Override
    protected void reflectState(@Nonnull final LoginViewState viewState) {
        switch (viewState.state()) {
            case LoginViewState.LOGIN_NOT_STARTED:
                break;
            case LoginViewState.LOGIN_IN_PROGRESS:
                showProgressDialog();
                break;
            case LoginViewState.LOGIN_SUCCESS:
                hideProgressDialog();
                break;
            case LoginViewState.LOGIN_FAILED:
                hideProgressDialog();
                mTvErrorText.setText("Invalid username or password");
                break;
        }
    }

    private void showProgressDialog() {
        mProgressDialog = ProgressDialog.show(mLastContext,
                "Loading", "Please Wait");
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onPresenterReady(@Nonnull final LoginPresenter presenter) {
        presenter.attach(this);
    }

    @Override
    protected void renderView(Bundle savedInstanceState) {
        mEtUsername = finViewById(R.id.etUsername);
        mEtPassword = finViewById(R.id.etPassword);
        mBtnLogin = finViewById(R.id.btnLogin);
        mTvErrorText = finViewById(R.id.tvErrorText);
        applyTextChangesLogic();
    }

    private void applyTextChangesLogic() {
        mBtnLogin.setEnabled(false);
        InitialValueObservable<CharSequence> usernameObservable
                = RxTextView.textChanges(mEtUsername);
        InitialValueObservable<CharSequence> passwordObservable
                = RxTextView.textChanges(mEtPassword);
        Observable.combineLatest(usernameObservable, passwordObservable,
                (username, password) -> username.length() * password.length() != 0)
                .subscribe(enable -> mBtnLogin.setEnabled(enable));
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Observable<LoginDataModel> loginClickIntent() {
        return RxView.clicks(mBtnLogin)
                .map(o -> LoginDataModel.create(mEtUsername.getText().toString(),
                        mEtPassword.getText().toString()));
    }

    @Override
    public void onDestroyView() {
        hideProgressDialog();
        super.onDestroyView();
    }
}
