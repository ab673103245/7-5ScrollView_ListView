package qianfeng.a7_5scrollviewlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = ((ListView) findViewById(R.id.lv));
        initData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        Log.d("google-my:", "onCreate: list:" + lv.getChildCount());
        int totalHeight = 0;
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            View itemView = adapter.getView(i, null, lv);

            // 两个0，代表父容器对itemView的宽高不作限制
            itemView.measure(0,0); // 没有这行代码根本就拿不到itemView.getMeasureHeight()
            int height = itemView.getMeasuredHeight();
            
            totalHeight += height;

        }

        ViewGroup.LayoutParams layoutParams = lv.getLayoutParams();
        layoutParams.height = totalHeight + lv.getDividerHeight() * (count - 1); // lv.getDividerheight()是获取listView里面分隔线的高度
        // 让ListView重新测量一次高度
        lv.requestLayout();

//        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams1.height = totalHeight + lv.getDividerHeight()*(count - 1);
//        lv.setLayoutParams(layoutParams1);


    }

    private void initData() {
        list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            list.add(new String("张三：" + i));
        }

    }
}
