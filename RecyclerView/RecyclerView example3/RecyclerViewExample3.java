package yuwei.documentdemo;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewExample3 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DataAdapter dataAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example3);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_exp3);
        linearLayoutManager = new LinearLayoutManager(this);
        arrayList = new ArrayList<String>();

        for (int i = 1; i <= 5; i++) {
            arrayList.add(Integer.toString(i));
        }

        dataAdapter = new DataAdapter(arrayList);
        dataAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        Toast.makeText(RecyclerViewExample3.this,
                                Integer.toString(position + 1), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(RecyclerViewExample3.this,
                                "test 2!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public static class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private ArrayList<String> arrayList;

        public DataAdapter(ArrayList<String> arrayList){
            this.arrayList = arrayList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.click_data_item, parent, false);
            return new mViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            ((mViewHolder) holder).text.setText(arrayList.get(position));
            if (itemClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
                        itemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView text;
            public mViewHolder(View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.txv_exp3_item);
            }

            @Override
            public void onClick(View view) {

            }
        }

        public interface OnItemClickListener{
            void onItemClick(View view, int position);
        }

        private OnItemClickListener itemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            itemClickListener = onItemClickListener;
        }
    }
}
