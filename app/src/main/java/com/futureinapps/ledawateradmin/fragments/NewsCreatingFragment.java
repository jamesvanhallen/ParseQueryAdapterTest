package com.futureinapps.ledawateradmin.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.activities.MainActivity;
import com.futureinapps.ledawateradmin.pojos.News;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    @Bind(R.id.image_news_creating)
    ImageView mImage;

    private News news;
    private Bitmap bmp;
    final int GALLERY_PICTURE = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_creator, container, false);
        ButterKnife.bind(this, v);
        if(getArguments()!=null){
            news = getArguments().getParcelable("news");
            mTitle.setText(news.getTitle());
            mText.setText(news.getMessage());
            if(news.getImage()!=null){
                Picasso.with(getActivity()).load(news.getImage().getUrl()).into(mImage);
                mTitle.setVisibility(View.GONE);
                mText.setVisibility(View.GONE);
            }

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
        if(title.equals("") && bmp == null){
            a = false;
            Toast.makeText(getActivity(), "Введите заголовок", Toast.LENGTH_SHORT).show();
        }
        if(text.equals("") && bmp == null){
            a = false;
            Toast.makeText(getActivity(), "Введите текст сообщения", Toast.LENGTH_SHORT).show();
        }
        if(a){
            if(bmp!=null){
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                ParseFile pFile = new ParseFile("file.png", stream.toByteArray());
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                news.setImage(pFile);
            } else {
                news.setTitle(title);
                news.setMessage(text);
            }

            news.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    ParsePush push = new ParsePush();
                    push.setMessage(title);
                    push.sendInBackground();
                    MainActivity.changeFragment(new NewsFragment(), false, (AppCompatActivity)getActivity());
                }
            });

        }
    }
    @OnClick(R.id.add_image_btn)
    void onAddImageClick(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, GALLERY_PICTURE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GALLERY_PICTURE && resultCode == Activity.RESULT_OK && null != data) {
            mTitle.setVisibility(View.GONE);
            mText.setVisibility(View.GONE);
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            // Get the cursor
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            bmp = BitmapFactory
                    .decodeFile(imgDecodableString);
            mImage.setImageBitmap(bmp);

        }
    }

}
