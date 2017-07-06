package offlineminds.com.mymovielist.ui.HomeFragment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.config;
import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 19/06/17.
 */

public class GridViewAdapter extends ArrayAdapter<Result> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Result> mGridData = new ArrayList<>();


    public GridViewAdapter(@NonNull Context context,
                           @LayoutRes int resource,
                           @NonNull List<Result> objects,
                           Context mContext, int layoutResourceId, ArrayList<Result> mGridData) {
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
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
        return mGridData.size();
    }

    @Nullable
    @Override
    public Result getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
        Picasso.with(mContext).load(config.imgBaseUrl+item.getPosterPath()).into(holder.imageView);
        return row;
    }

    static class ViewHolder {
//        TextView titleTextView;
        ImageView imageView;
    }
}
