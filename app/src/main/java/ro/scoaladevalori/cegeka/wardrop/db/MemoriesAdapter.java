package ro.scoaladevalori.cegeka.wardrop.db;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ro.scoaladevalori.cegeka.wardrop.R;

public class MemoriesAdapter  extends CursorAdapter{


    public MemoriesAdapter(Context context, Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.clothing_item_view,viewGroup,false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        Memory memory = new Memory(cursor);

        holder.colorTextView.setText(memory.getColor());
        holder.categoryTextView.setText(memory.getCategory());
        holder.styleTextView.setText(memory.getStyle());
        holder.imageView.setImageBitmap(memory.getImage());
    }

    private class ViewHolder{
        final TextView colorTextView;
        final TextView categoryTextView;
        final TextView styleTextView;
        final ImageView imageView;


        ViewHolder(View view){
            colorTextView = view.findViewById(R.id.list_item_color);
            categoryTextView = view.findViewById(R.id.list_item_category);
            styleTextView = view.findViewById(R.id.list_item_style);
            imageView = view.findViewById(R.id.list_item_image_view_picture);

        }

    }
}
