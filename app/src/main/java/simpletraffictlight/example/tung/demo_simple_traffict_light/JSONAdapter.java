package simpletraffictlight.example.tung.demo_simple_traffict_light;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Tran on 5/29/2015.
 */
public class JSONAdapter extends BaseAdapter {
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";

    Context   _context;
    JSONArray _jsonArray;
    LayoutInflater _layoutInflater;

    public JSONAdapter(Context context, LayoutInflater inflater, JSONArray array)
    {
        _context = context;
        _layoutInflater = inflater;
        _jsonArray = array;
    }

    public JSONAdapter(Context context, LayoutInflater inflater)
    {
        _context = context;
        _layoutInflater = inflater;
        _jsonArray = new JSONArray();
    }

    public void updateData(JSONArray json) {
        _jsonArray = json;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return _jsonArray.length();
    }

    @Override
    public JSONObject getItem(int position) {
        return _jsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holer;
        if(convertView == null) {
            convertView = _layoutInflater.inflate(R.layout.book_item_for_gridview, null);

            holer = new ViewHoler();
            holer.img_thumb  = (ImageView)convertView.findViewById(R.id.img_thumbnail);
            holer.text_title = (TextView)convertView.findViewById(R.id.text_title);
            holer.text_auth  = (TextView)convertView.findViewById(R.id.text_author);
            convertView.setTag(holer);
        }else {
            holer = (ViewHoler) convertView.getTag();
        }
        JSONObject json = (JSONObject)getItem(position);

        if(json.has("cover_i")){

            String imageId = json.optString("cover_i");

            String imageUrl = IMAGE_URL_BASE + imageId + "-S.jpg";

            Picasso.with(_context).load(imageUrl).placeholder(R.mipmap.ic_books).into(holer.img_thumb);
        }else{
            holer.img_thumb.setImageResource(R.mipmap.ic_books);
        }

        if(json.has("title")) {
            holer.text_title.setText(json.optString("title"));
        }
        if(json.has("author_name")) {
            holer.text_auth.setText(json.optJSONArray("author_name").optString(0));
        }


        return convertView;
    }

    private static class ViewHoler{
        public ImageView img_thumb;
        public TextView  text_title;
        public TextView  text_auth;
    }

}
