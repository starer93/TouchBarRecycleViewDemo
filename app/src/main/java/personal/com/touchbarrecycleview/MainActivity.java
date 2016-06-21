package personal.com.touchbarrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView view;
    private ArrayList<Person> personArrayList = new ArrayList<>();
    private String[] letter = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private Hashtable<String, Integer> letterTable = new Hashtable();
    private ArrayList<Integer> indexs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShortCutView shortCutView = (ShortCutView) findViewById(R.id.letter_list);

        initHashTable();
        mock();
        addHeader();
        view  = (RecyclerView) findViewById(R.id.rv_list);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter adapter = new recycleAdapter(personArrayList);
        view.setAdapter(adapter);

        shortCutView.setOnTouchLetterChangedListener(new ShortCutView.OnTouchLetterChangedListener() {
            @Override
            public void onTouchLetterChangedListener(int index) {
                if(indexs.contains(index))
                {
                        view.scrollToPosition(index);}
                 }
        });

        shortCutView.setOnTouchLetterReleasedListener(new ShortCutView.OnTouchLetterReleasedListener() {
            @Override
            public void onTouchLetterReleasedListener(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initHashTable()
    {
        for(int i = 0; i< letter.length; i++)
        {
            letterTable.put(letter[i],i);
        }
    }

    private void mock()
    {
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("abc","a"));
        personArrayList.add(new Person("bc1","b"));
        personArrayList.add(new Person("bc1","b"));
        personArrayList.add(new Person("bc1","b"));
        personArrayList.add(new Person("bc1","b"));
        personArrayList.add(new Person("bc1","b"));
        personArrayList.add(new Person("c","c"));
        personArrayList.add(new Person("c1","c"));
        personArrayList.add(new Person("fffdf","f"));
        personArrayList.add(new Person("wwww","w"));
        personArrayList.add(new Person("hhhh","h"));
        personArrayList.add(new Person("usad","u"));
        personArrayList.add(new Person("usad","u"));
        personArrayList.add(new Person("usad","u"));
        personArrayList.add(new Person("usad","u"));
        personArrayList.add(new Person("usad","u"));
        personArrayList.add(new Person("usad","u"));
        personArrayList.add(new Person("usad","u"));
        personArrayList.add(new Person("usad","u"));

    }

    private void addHeader()
    {
        String current = "";
        for(int i = 0; i < personArrayList.size(); i++)
        {
            Person person = personArrayList.get(i);
            if(person.getIndx().equals(current)) //如果拼音首页字母等于当前的字母，则不是该字母的第一个
            {
                person.setFist(false); //所以不需要加header
            }
            else //如果是第一个
            {
                current = person.getIndx();
                person.setFist(true); //添加header
                int c = letterTable.get(current); //并且获得该字母在字母表中的的index
                indexs.add(c);

            }
        }
    }
}
