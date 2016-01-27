package com.leowong.demo.retrofitwithrxjava;

import com.leowong.demo.retrofitwithrxjava.models.Repository;
import com.leowong.extendedrecyclerview.adapters.CommonAdapter;

import java.util.List;

/**
 * User: wanglg
 * Date: 2016-01-12
 * Time: 14:45
 * FIXME
 */
public class RepositoryAdapter extends CommonAdapter<Repository> {
    public RepositoryAdapter(List<Repository> mDatas) {
        super(mDatas);
    }

    @Override
    public int getLayoutId(int i) {
        return R.layout.item_repo;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        Repository repository = mDatas.get(position);
        holder.setText(R.id.text_repo_title, repository.name);
        holder.setText(R.id.text_repo_description, repository.description);
        holder.setText(R.id.text_watchers, mContext.getResources().getString(R.string.text_watchers, repository.watchers));
        holder.setText(R.id.text_stars, mContext.getResources().getString(R.string.text_stars, repository.stars));
        holder.setText(R.id.text_forks, mContext.getResources().getString(R.string.text_forks, repository.forks));

    }
}
