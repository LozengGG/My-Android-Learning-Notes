package yuwei.documentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewExample1 extends AppCompatActivity {

    private RecyclerView dataTable;

    private String[] dataTitles = {"t1", "t2", "t3", "t4", "t5"};
    private int[] dataValues = {1, 2, 3, 4, 5};
    private ArrayList<DataItem> dataItemArray;
    private int itemNum = 5;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private DataAdapter dAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example1);

        dataTable = (RecyclerView) findViewById(R.id.recyclerview_exp1);

        dataItemArray = new ArrayList<>();
        for(int i = 0; i < itemNum; i++){
            dataItemArray.add(new DataItem(dataTitles[i],Integer.toString(dataValues[i])));
        }

        linearLayoutManager = new LinearLayoutManager(this);
        dividerItemDecoration = new DividerItemDecoration
                (dataTable.getContext(), linearLayoutManager.getOrientation());
        dAdapter = new DataAdapter(dataItemArray);
        dataTable.addItemDecoration(dividerItemDecoration);
        dataTable.setLayoutManager(linearLayoutManager);
        dataTable.setAdapter(dAdapter);
    }

    private class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

        private ArrayList<DataItem> diArray;

        public DataAdapter(ArrayList<DataItem> arrayList) {
            diArray = arrayList;
        }

        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
                DataItem di = diArray.get(position);
                TextView title = holder.txvTitle;
                TextView value = holder.txvValue;
                title.setText(di.getTitle());
                value.setText(di.getValue());
        }

        @Override
        public int getItemCount() {
            return diArray.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView txvTitle, txvValue;
            public ViewHolder(View itemView) {
                super(itemView);
                txvTitle = itemView.findViewById(R.id.txv_data_title);
                txvValue = itemView.findViewById(R.id.txv_data_value);
            }
        }
    }

    private class DataItem {

        String title, value;

        public DataItem(String t, String v) {
            title = t;
            value = v;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
