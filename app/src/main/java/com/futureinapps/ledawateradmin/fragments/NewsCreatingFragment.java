package com.futureinapps.ledawateradmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.pojos.News;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fappsilya on 07.07.15.
 */
public class NewsCreatingFragment extends BaseFragment {

    @Bind(R.id.title)
    EditText mTitle;
    @Bind(R.id.text)
    EditText mText;

    private News news;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_creator, container, false);
        ButterKnife.bind(this, v);
        if(getArguments()!=null){
            news = getArguments().getParcelable("news");
            mTitle.setText(news.getTitle());
            mText.setText(news.getMessage());
        } else {
            news = new News();
        }

        return v;
    }
    @OnClick(R.id.publish_btn)
    void onPublishBtnClick(){
        final String title = mTitle.getText().toString().trim();
        String text = mText.getText().toString().trim();
        boolean a = true;
        if(title.equals("")){
            a = false;
            Toast.makeText(getActivity(), "Введите заголовок", Toast.LENGTH_SHORT).show();
        }
        if(text.equals("")){
            a = false;
            Toast.makeText(getActivity(), "Введите текст сообщения", Toast.LENGTH_SHORT).show();
        }
        if(a){

            news.setTitle(title);
            news.setMessage(text);
            news.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    ParsePush push = new ParsePush();
                    push.setMessage(title);
                    push.sendInBackground();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container2, new NewsFragment())
                            .commit();
                }
            });

        }
    }
}
