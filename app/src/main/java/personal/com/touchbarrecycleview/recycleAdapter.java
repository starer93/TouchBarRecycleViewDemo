package personal.com.touchbarrecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/20.
 */
public class recycleAdapter extends RecyclerView.Adapter {

    private ArrayList<Person> personArrayList;

    public recycleAdapter(ArrayList<Person> persons) {
        personArrayList = persons;
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SViewHolder holder = new SViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Person person = personArrayList.get(position);
        SViewHolder planholder= (SViewHolder) holder;
        planholder.tv_title.setText(person.getName());
        if(person.isFist())
        {
            planholder.tv_header.setText(person.getIndx());
        }
        else
        {
            planholder.tv_header.setVisibility(View.GONE);
        }
    }

    class SViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_title;
        TextView tv_header;
        public SViewHolder(View view)
        {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.txt_name);
            tv_header = (TextView) view.findViewById(R.id.txt_header);
        }
    }
}
