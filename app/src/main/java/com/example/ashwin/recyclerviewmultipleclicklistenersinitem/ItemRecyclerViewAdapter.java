package com.example.ashwin.recyclerviewmultipleclicklistenersinitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ashwin on 29/12/16.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder>
{
    private static String LOG_TAG = "ItemRecyclerViewAdapter";
    private static ArrayList<Item> mItemsList;

    public interface ItemClickListener
    {
        public void onButton1Clicked(View v, int position);

        public void onButton2Clicked(View v, int position);

        public void onImageClicked(View v, int position);

        public void onItemClicked(View v, int position);
    }

    private static Context mContext;
    private static ItemClickListener sItemListener;

    public ItemRecyclerViewAdapter(Context context, ItemClickListener itemClickListener, ArrayList<Item> itemsList)
    {
        mContext = context;
        sItemListener = itemClickListener;
        mItemsList = itemsList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position)
    {
        holder.position = position;
        holder.mLabel.setText(mItemsList.get(position).getText());

        Log.d("onbindviewholder", mItemsList.get(position).getText());
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public RelativeLayout mItemRelativeLayout;
        public TextView mLabel;
        public ImageView mItemImageView;
        public Button mButton1, mButton2;
        public int position;

        public ItemViewHolder(View itemView)
        {
            super(itemView);

            mButton1 = (Button) itemView.findViewById(R.id.itemButton1);
            mButton1.setOnClickListener(this);

            mButton2 = (Button) itemView.findViewById(R.id.itemButton2);
            mButton2.setOnClickListener(this);

            mItemImageView = (ImageView) itemView.findViewById(R.id.itemImageView);
            mItemImageView.setOnClickListener(this);

            mItemRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.itemRelativeLayout);
            mItemRelativeLayout.setOnClickListener(this);

            mLabel = (TextView) itemView.findViewById(R.id.itemTextView);
            mLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, mItemsList.get(position).getText() + " text clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v)
        {
            int id = v.getId();
            if( sItemListener!=null )
            {
                switch(id) {
                    case R.id.itemButton1:
                        sItemListener.onButton1Clicked(v, position);
                        break;
                    case R.id.itemButton2:
                        sItemListener.onButton2Clicked(v, position);
                        break;
                    case R.id.itemImageView:
                        sItemListener.onImageClicked(v, position);
                        break;
                    case R.id.itemRelativeLayout:
                        sItemListener.onItemClicked(v, position);
                        break;
                }
            }
        }
    }

    public void addItem(Item dataObj, int index)
    {
        mItemsList.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index)
    {
        mItemsList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount()
    {
        return mItemsList.size();
    }
}
