package simpletraffictlight.example.tung.demo_simple_traffict_light;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return null;
    }
}
