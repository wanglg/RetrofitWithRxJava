package com.leowong.demo.retrofitwithrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.leowong.demo.retrofitwithrxjava.models.Repository;
import com.leowong.demo.retrofitwithrxjava.net.GitUserApi;
import com.leowong.extendedrecyclerview.ExtendedRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.edit_user_name)
    EditText editUserName;
    @Bind(R.id.button_search)
    ImageButton buttonSearch;
    @Bind(R.id.extend_list)
    ExtendedRecyclerView extendList;
    RepositoryAdapter repositoryAdapter;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        extendList.setLayoutManager(linearLayoutManager);
        extendList.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.button_search)
    public void onSearchClick() {
        subscription = RetrofitManager.getInstance().create(GitUserApi.class).publicRepositories(editUserName.getText().toString())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        repositoryAdapter.replaceAll(null);
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        repositoryAdapter = new RepositoryAdapter(repositories);
                        extendList.setAdapter(repositoryAdapter);
//                        repositoryAdapter.replaceAll(repositories);
                    }

                    @Override
                    public void onStart() {
                        extendList.setVisibility(View.VISIBLE);
                        repositoryAdapter = new RepositoryAdapter(null);
                        extendList.setProgressAdapter(repositoryAdapter);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) subscription.unsubscribe();
    }
}
