package offlineminds.com.mymovielist.ui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.config;
import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 19/06/17.
 */

public class AdapterComedyFragment extends ArrayAdapter<Result> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Result> mGridData = new ArrayList<>();

    private static String TAG = AdapterComedyFragment.class.getName();

    public AdapterComedyFragment(@NonNull Context context,
                                 @LayoutRes int resource,
                                 @NonNull List<Result> objects,
                                 Context mContext,
                                 int layoutResourceId,
                                 ArrayList<Result> mGridData) {

        super(context, resource, objects);
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.mGridData = mGridData;
    }



    /**
     * Updates grid data and refresh grid items.
     * @param mGridData
     */

    public void setGridData(ArrayList<Result> mGridData) {
        Log.d(TAG,"Inside SET GRID DATA");
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = mGridData.size();
        Log.d(TAG,"GRID DATA COUNT: "+ count);
        return count;
    }

    @Nullable
    @Override
    public Result getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG,"Get View Initialised !!! ");
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
//            holder.titleTextView = (TextView) row.findViewById(R.id.grid_item_title);
            holder.imageView = (ImageView) row.findViewById(R.id.grid_item_image);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        Result item = mGridData.get(position);
//        holder.titleTextView.setText(Html.fromHtml(item.getTitle()));
        Log.d(TAG,"Inside Adapter Image URL:--- "+item.getPosterPath());
        Picasso.with(mContext).load(config.imgBaseUrl+item.getPosterPath()).into(holder.imageView);
        return row;
    }

    static class ViewHolder {
//        TextView titleTextView;
        ImageView imageView;
    }
}
