package simpletraffictlight.example.tung.demo_simple_traffict_light;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by user on 5/30/2015.
 */
public class DetailActivity extends AppCompatActivity {
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";

    ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.detail_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView image = (ImageView) findViewById(R.id.img_cover);

        LoadImage(getIntent().getExtras().getString("coverId"),image);
    }

    public void LoadImage(String coverId, ImageView imageView) {
        if(coverId.length() > 0) {
            Picasso.with(this).load(IMAGE_URL_BASE + coverId + "-L.jpg").placeholder(R.mipmap.img_books_loading).into(imageView);
        }
    }

    private void setShareIntent() {

        // create an Intent with the contents of the TextView
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "Book Recommendation!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "goodle.com");

        // Make sure the provider knows
        // it should work with that Intent
        shareActionProvider.setShareIntent(shareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        if(shareItem != null) {
            shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        }

        setShareIntent();
        return true;
    }

}



