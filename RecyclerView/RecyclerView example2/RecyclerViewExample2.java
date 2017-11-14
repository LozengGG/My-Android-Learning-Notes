package yuwei.documentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewExample2 extends AppCompatActivity {

    private enum VIEWTYPE{
        TEXT_ONLY,
        TEXT_IMAGE
    }
    private ArrayList<String> testArray;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example2);

        recyclerView = (RecyclerView) findViewById(R.id.test_recycler_view);

        testArray = new ArrayList<String>();
        testArray.add("Test");
        for(int i = 0; i < 5; i++){
            testArray.add(Integer.toString(i));
        }

        setRecyclerView();

    }

    private void setRecyclerView() {
        dataAdapter = new DataAdapter(testArray);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private ArrayList<String> dataArray;

        public DataAdapter(ArrayList<String> arrayList) {
            dataArray = arrayList;
        }

        @Override
        public int getItemViewType(int position) {
            int type;
            switch(position){
                case 0:
                    type = VIEWTYPE.TEXT_IMAGE.ordinal();
                    break;
                case 1:
                    type = VIEWTYPE.TEXT_ONLY.ordinal();
                    break;
                default:
                    type = VIEWTYPE.TEXT_ONLY.ordinal();
                    break;
            }
            return type;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEWTYPE.TEXT_IMAGE.ordinal()) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_image, parent, false);
                return new TextImageHolder(v);
            }
            else if (viewType == VIEWTYPE.TEXT_ONLY.ordinal()) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_only, parent, false);
                return new TextOnlyHolder(v);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TextImageHolder) {
                ((TextImageHolder) holder).text_i.setText(dataArray.get(position));
                ((TextImageHolder) holder).image.setImageResource(R.drawable.example);
            }
            else if (holder instanceof TextOnlyHolder) {
                ((TextOnlyHolder) holder).text_o.setText(dataArray.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return dataArray.size();
        }

        public class TextImageHolder extends RecyclerView.ViewHolder {
            public TextView text_i;
            public ImageView image;
            public TextImageHolder(View itemView) {
                super(itemView);
                text_i = itemView.findViewById(R.id.txv_text_img);
                image = itemView.findViewById(R.id.img_text_img);
            }
        }

        public class TextOnlyHolder extends RecyclerView.ViewHolder {
            public TextView text_o;
            public TextOnlyHolder(View itemView) {
                super(itemView);
                text_o = itemView.findViewById(R.id.txv_text_only);
            }
        }
    }
}
