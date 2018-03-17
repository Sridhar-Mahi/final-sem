package com.example.piccosoft.designing;



import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] titles = {"Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd",
            "Spiro Solutions Pvt. Ltd"};

    private int[] images_1 = { R.drawable.anna1,
            R.drawable.anna2,
            R.drawable.anna3,
            R.drawable.anna4,
            R.drawable.anna5,
            R.drawable.anna6,
            R.drawable.anna7,
            R.drawable.anna8,
            R.drawable.anna9,
            R.drawable.anna10,
            R.drawable.anna11,
            R.drawable.anna12,
            R.drawable.anna13,
            R.drawable.anna14,
            R.drawable.anna15};

    private int[] images_2 = { R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3,
            R.drawable.s4,
            R.drawable.s5,
            R.drawable.s6,
            R.drawable.s7,
            R.drawable.s8,
            R.drawable.s9,
            R.drawable.s10,
            R.drawable.s11,
            R.drawable.s12,
            R.drawable.s13,
            R.drawable.s14,
            R.drawable.s15};

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView item_images_2;
        public ImageView item_images_1;
        public TextView item_title;

        public ViewHolder(View itemView) {
            super(itemView);
            item_images_2 = itemView.findViewById(R.id.item_images_2);
            item_title = itemView.findViewById(R.id.item_title);
            item_images_1 =
                    itemView.findViewById(R.id.item_images_1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Spiro Solutions Pvt. Ltd, AnnaNagar Chennai. " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.item_title.setText(titles[i]);
        viewHolder.item_images_2.setImageResource(images_2[i]);
        viewHolder.item_images_1.setImageResource(images_1[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}