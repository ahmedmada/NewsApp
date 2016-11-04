package com.example.ahmed.newsapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    static int flag;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"لم يتم رفع التطبيق علي جوجال بلاي بعد.",Toast.LENGTH_LONG).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
                recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        preferences = getSharedPreferences("pref",MODE_PRIVATE);

        flag = preferences.getInt("f",1);
        int check= preferences.getInt("c",0);
        if (check == 0){
            notif();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("c",1);
            editor.commit();
            }
        openNews(flag);
//
//        if (isNetworkConnected()) {
//            getNews("http://www6.mashy.com/uploads/titles.rss");
//        }
//        else {
//                Toast.makeText(MainActivity.this,"Check Your Internet.",Toast.LENGTH_LONG).show();
//            }

    }



    public void getNews(String link){
        try {
            ReedRss reedRss = new ReedRss(this,recyclerView,link);
            reedRss.execute();

        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Check Your Internet.",Toast.LENGTH_LONG).show();
        }

}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            dialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.connect_us) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100004427910245"));
            startActivity(i);
            return true;
        }else if (id == R.id.refresh) {
            openNews(flag);
            return true;
        }else if (id == R.id.exit) {
            dialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        SharedPreferences.Editor editor = preferences.edit();

        if (isNetworkConnected()) {
            if (id == R.id.egy_news) {
//                getNews("http://www6.mashy.com/uploads/titles.rss");
//                setTitle("اخبار مصر");
                editor.putInt("f",1);
                openNews(1);
            } else if (id == R.id.saudi_news) {
//                getNews("http://www6.mashy.com/news/saudi/titles.rss");
//                setTitle("اخبار السعودية");
                editor.putInt("f",2);
                openNews(2);
            } else if (id == R.id.emirates_news) {
//                getNews("http://www6.mashy.com/news/uae/titles.rss");
//                setTitle("اخبار الامارات");
                editor.putInt("f",3);
                openNews(3);
            } else if (id == R.id.arab_news) {
//                getNews("http://www6.mashy.com/news/arab/titles.rss");
//                setTitle("اخبار الوطن العربي");
                editor.putInt("f",4);
                openNews(4);
            } else if (id == R.id.world_news) {
//                getNews("http://www6.mashy.com/news/world/titles.rss");
//                setTitle("اخبار العالم");
                editor.putInt("f",5);
                openNews(5);
            } else if (id == R.id.sports_news) {
//                getNews("http://www6.mashy.com/news/sport/title.rss");
//                setTitle("اخبار الرياضة");
                editor.putInt("f",6);
                openNews(6);
            } else if (id == R.id.arts_news) {
//                getNews("http://www6.mashy.com/news/entertainment/titles.rss");
//                setTitle("اخبار الثقافة والفنون");
                editor.putInt("f",7);
                openNews(7);
            } else if (id == R.id.science_news) {
//                getNews("http://www6.mashy.com/news/technology/titles.rss");
//                setTitle("اخبار العلوم والتكنولوجيا");
                editor.putInt("f",8);
                openNews(8);
            } else if (id == R.id.economy_news) {
//                getNews("http://www6.mashy.com/news/business/titles.rss");
//                setTitle("اخبار الاقتصاد");
                editor.putInt("f",9);
                openNews(9);
            } else if (id == R.id.finny_news) {
//                getNews("http://www6.mashy.com/news/light/titles.rss");
//                setTitle("اخبار طريفة");
                editor.putInt("f",10);
                openNews(10);
            } else if (id == R.id.accidents_news) {
//                getNews("http://www6.mashy.com/news/accidents/titles.rss");
//                setTitle("اخبار الحوادث");
                editor.putInt("f",11);
                openNews(11);
            } else if (id == R.id.repotrs) {
//                getNews("http://www6.mashy.com/news/reports/titles.rss");
//                setTitle("تقارير وتحليلات");
                editor.putInt("f",12);
                openNews(12);
            } else if (id == R.id.mobiles_news) {
//                getNews("http://www6.mashy.com/mobiles/news/titles.rss");
//                setTitle("اخبار الموبايلات");
                editor.putInt("f",13);
                openNews(13);
            } else if (id == R.id.new_mobiles) {
//                getNews("http://www6.mashy.com/mobiles/new-phones/titles.rss");
//                setTitle("احدث الموبايلات");
                editor.putInt("f",14);
                openNews(14);
            } else if (id == R.id.tablets_news) {
//                getNews("http://www6.mashy.com/mobiles/tablets/title.rss");
//                setTitle("اخبار الاجهزة اللوحية");
                editor.putInt("f",15);
                openNews(15);
            } else if (id == R.id.mobile_apps) {
//                getNews("http://www6.mashy.com/mobiles/applications/titles.rss");
//                setTitle("تطبيقات الهواتف الذكية");
                editor.putInt("f",16);
                openNews(16);
            } else if (id == R.id.cinema_news) {
//                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1-%D8%A7%D9%84%D8%B3%D9%8A%D9%86%D9%85%D8%A7.rss");
//                setTitle("اخبار السينما");
                editor.putInt("f",17);
                openNews(17);
            } else if (id == R.id.english_films) {
//                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%A3%D8%AC%D9%86%D8%A8%D9%8A%D8%A9/%D8%A3%D8%AD%D8%AF%D8%AB-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85/%D9%82%D8%A7%D8%A6%D9%85%D8%A9-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%A7%D9%84%D8%A3%D8%AC%D9%86%D8%A8%D9%8A%D8%A9.rss");
//                setTitle("احدث الافلام الاجنبية");
                editor.putInt("f",18);
                openNews(18);
            } else if (id == R.id.arab_films) {
//                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%B9%D8%B1%D8%A8%D9%8A%D8%A9/%D8%A3%D8%AD%D8%AF%D8%AB-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85/%D9%82%D8%A7%D8%A6%D9%85%D8%A9-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%A7%D9%84%D8%B9%D8%B1%D8%A8%D9%8A%D8%A9.rss");
//                setTitle("احدث الافلام العربية");
                editor.putInt("f",19);
                openNews(19);
            } else if (id == R.id.watch) {
//                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%B4%D8%A7%D9%87%D8%AF%D8%AA-%D9%84%D9%83-%D9%81%D9%8A%D9%84%D9%85.rss");
//                setTitle("شاهدت لك");
                editor.putInt("f",20);
                openNews(20);
            } else if (id == R.id.health_news) {
//                getNews("http://www6.mashy.com/health/news/list?func=viewRss");
//                setTitle("أخبار الصحة");
                editor.putInt("f",21);
                openNews(21);
            } else if (id == R.id.family_health) {
//                getNews("http://www6.mashy.com/health/family/list?func=viewRss");
//                setTitle("صحة الأسرة");
                editor.putInt("f",22);
                openNews(22);
            } else if (id == R.id.psychological_health) {
//                getNews("http://www6.mashy.com/health/psychology/list?func=viewRss");
//                setTitle("صحة نفسية");
                editor.putInt("f",23);
                openNews(23);
            } else if (id == R.id.digestive_health) {
//                getNews("http://www6.mashy.com/health/digest/list?func=viewRss");
//                setTitle("صحة هضمية");
                editor.putInt("f",24);
                openNews(24);
            } else if (id == R.id.men_health) {
//                getNews("http://www6.mashy.com/health/man/list?func=viewRss");
//                setTitle("صحة الرجل");
                editor.putInt("f",25);
                openNews(25);
            } else if (id == R.id.agility) {
//                getNews("http://www6.mashy.com/health/fitness/list?func=viewRss");
//                setTitle("رشاقة");
                editor.putInt("f",26);
                openNews(26);
            } else if (id == R.id.plants) {
//                getNews("http://www6.mashy.com/health/vegetarian/list?func=viewRss");
//                setTitle("نباتيات");
                editor.putInt("f",27);
                openNews(27);
            } else if (id == R.id.women_health) {
//                getNews("http://www6.mashy.com/women/health/list?func=viewRss");
//                setTitle("صحة المرأة");
                editor.putInt("f",28);
                openNews(28);
            } else if (id == R.id.alrernative_medicine) {
//                getNews("http://www6.mashy.com/health/alternative/list?func=viewRss");
//                setTitle("الطب البديل");
                editor.putInt("f",29);
                openNews(29);
            } else if (id == R.id.medicine_advice) {
//                getNews("http://www6.mashy.com/health/advice/list?func=viewRss");
//                setTitle("نصائح طبية");
                editor.putInt("f",30);
                openNews(30);
            } else if (id == R.id.your_beauty) {
//                getNews("http://www6.mashy.com/women/beauty/list?func=viewRss");
//                setTitle("جمالك");
                editor.putInt("f",31);
                openNews(31);
            } else if (id == R.id.your_style) {
//                getNews("http://www6.mashy.com/women/style/list?func=viewRss");
//                setTitle("اناقتك");
                editor.putInt("f",32);
                openNews(32);
            } else if (id == R.id.sweet_home) {
//                getNews("http://www6.mashy.com/women/home_sweet_home/list?func=viewRss");
//                setTitle("البيت السعيد");
                editor.putInt("f",33);
                openNews(33);
            } else if (id == R.id.maternity) {
//                getNews("http://www6.mashy.com/women/mother/list?func=viewRss");
//                setTitle("سنة أولى أمومة");
                editor.putInt("f",34);
                openNews(34);
            } else if (id == R.id.caricature) {
//                getNews("http://www6.mashy.com/jokes/cartoon/ashraf_hamdy?func=viewRss");
//                setTitle("كاريكاتير");
                editor.putInt("f",35);
                openNews(35);
            } else if (id == R.id.egy_jokes) {
//                getNews("http://www6.mashy.com/jokes/egyptian_jokes/list?func=viewRss");
//                setTitle("نكت مصرية");
                editor.putInt("f",36);
                openNews(36);
            } else if (id == R.id.abu_arab) {
//                getNews("http://www6.mashy.com/jokes/abou_el3araby/list?func=viewRss");
//                setTitle("ركن ابو العربي");
                editor.putInt("f",37);
                openNews(37);
            } else if (id == R.id.funny_photo) {
//                getNews("http://www6.mashy.com/jokes/funny_pics/pics_list?func=viewRss");
//                setTitle("صور مضحكة");
                editor.putInt("f",38);
                openNews(38);
            } else if (id == R.id.bad_jokes) {
//                getNews("http://www6.mashy.com/jokes/silly_jokes/list?func=viewRss");
//                setTitle("نكت رخمة");
                editor.putInt("f",39);
                openNews(39);
            } else if (id == R.id.arab_jokes) {
//                getNews("http://www6.mashy.com/jokes/arabian_jokes/list?func=viewRss");
//                setTitle("نكت عربية");
                editor.putInt("f",40);
                openNews(40);
            } else if (id == R.id.fawazeer) {
//                getNews("http://www6.mashy.com/jokes/riddles/list?func=viewRss");
//                setTitle("فوازير");
                editor.putInt("f",41);
                openNews(41);
            } else if (id == R.id.arab_proverbs) {
//                getNews("http://www6.mashy.com/quotes/arabian_quotes/list?func=viewRss");
//                setTitle("أمثال عربية");
                editor.putInt("f",42);
                openNews(42);
            } else if (id == R.id.popular_like) {
//                getNews("http://www6.mashy.com/quotes/popular_quotes/list?func=viewRss");
//                setTitle("أمثال شعبية");
                editor.putInt("f",43);
                openNews(43);
            } else if (id == R.id.story_like) {
//                getNews("http://www6.mashy.com/quotes/proverb_story/list?func=viewRss");
//                setTitle("قصة مثل");
                editor.putInt("f",44);
                openNews(44);
            } else if (id == R.id.wisdom) {
//                getNews("http://www6.mashy.com/quotes/wise/list?func=viewRss");
//                setTitle("حكمة");
            } else if (id == R.id.women_love) {
//                getNews("http://www6.mashy.com/quotes/women_love/list?func=viewRss");
//                setTitle("المرأة والحب");
                editor.putInt("f",45);
                openNews(45);
            } else if (id == R.id.celebrity_saying) {
//                getNews("http://www6.mashy.com/quotes/celebrities_quotes/list?func=viewRss");
//                setTitle("أقوال المشاهير");
                editor.putInt("f",46);
                openNews(46);
            } else if (id == R.id.peoples_like) {
//                getNews("http://www6.mashy.com/quotes/people_proverbs/list?func=viewRss");
//                setTitle("أمثال الشعوب");
                editor.putInt("f",47);
                openNews(47);
            } else if (id == R.id.nav_share) {
                Toast.makeText(MainActivity.this, "لم يتم رفع التطبيق علي جوجال بلاي بعد.", Toast.LENGTH_LONG).show();
            } else if (id == R.id.face) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100004427910245"));
                startActivity(i);
            }
            editor.commit();
        }else {
            Toast.makeText(MainActivity.this, "Check your internet", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are You Sure you want te Exit !!");
        builder.setTitle("Exit Message");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Details d = new Details();
                finish();
                d.finish();
            }

        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"You still in the app", Toast.LENGTH_LONG).show();
            }
        });
        final AlertDialog di = builder.create();
        di.show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public void notif(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,7);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,0);

        Intent alarmIntent = new Intent(getApplicationContext(),AlarmRecever.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY,
                PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));

    }

    public void openNews(int f){


        if (isNetworkConnected()) {
            if (f == 1) {
                getNews("http://www6.mashy.com/uploads/titles.rss");
                setTitle("اخبار مصر");
            } else if (f == 2) {
                getNews("http://www6.mashy.com/news/saudi/titles.rss");
                setTitle("اخبار السعودية");
            } else if (f == 3) {
                getNews("http://www6.mashy.com/news/uae/titles.rss");
                setTitle("اخبار الامارات");
            } else if (f == 4) {
                getNews("http://www6.mashy.com/news/arab/titles.rss");
                setTitle("اخبار الوطن العربي");
            } else if (f == 5) {
                getNews("http://www6.mashy.com/news/world/titles.rss");
                setTitle("اخبار العالم");
            } else if (f == 6) {
                getNews("http://www6.mashy.com/news/sport/title.rss");
                setTitle("اخبار الرياضة");
            } else if (f == 7) {
                getNews("http://www6.mashy.com/news/entertainment/titles.rss");
                setTitle("اخبار الثقافة والفنون");
            } else if (f == 8) {
                getNews("http://www6.mashy.com/news/technology/titles.rss");
                setTitle("اخبار العلوم والتكنولوجيا");
            } else if (f == 9) {
                getNews("http://www6.mashy.com/news/business/titles.rss");
                setTitle("اخبار الاقتصاد");
            } else if (f == 10) {
                getNews("http://www6.mashy.com/news/light/titles.rss");
                setTitle("اخبار طريفة");
            } else if (f == 11) {
                getNews("http://www6.mashy.com/news/accidents/titles.rss");
                setTitle("اخبار الحوادث");
            } else if (f == 12) {
                getNews("http://www6.mashy.com/news/reports/titles.rss");
                setTitle("تقارير وتحليلات");
            } else if (f == 13) {
                getNews("http://www6.mashy.com/mobiles/news/titles.rss");
                setTitle("اخبار الموبايلات");
            } else if (f == 14) {
                getNews("http://www6.mashy.com/mobiles/new-phones/titles.rss");
                setTitle("احدث الموبايلات");
            } else if (f == 15) {
                getNews("http://www6.mashy.com/mobiles/tablets/title.rss");
                setTitle("اخبار الاجهزة اللوحية");
            } else if (f == 16) {
                getNews("http://www6.mashy.com/mobiles/applications/titles.rss");
                setTitle("تطبيقات الهواتف الذكية");
            } else if (f == 17) {
                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1-%D8%A7%D9%84%D8%B3%D9%8A%D9%86%D9%85%D8%A7.rss");
                setTitle("اخبار السينما");
            } else if (f == 18) {
                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%A3%D8%AC%D9%86%D8%A8%D9%8A%D8%A9/%D8%A3%D8%AD%D8%AF%D8%AB-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85/%D9%82%D8%A7%D8%A6%D9%85%D8%A9-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%A7%D9%84%D8%A3%D8%AC%D9%86%D8%A8%D9%8A%D8%A9.rss");
                setTitle("احدث الافلام الاجنبية");
            } else if (f == 19) {
                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%B9%D8%B1%D8%A8%D9%8A%D8%A9/%D8%A3%D8%AD%D8%AF%D8%AB-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85/%D9%82%D8%A7%D8%A6%D9%85%D8%A9-%D8%A7%D9%84%D8%A3%D9%81%D9%84%D8%A7%D9%85-%D8%A7%D9%84%D8%B9%D8%B1%D8%A8%D9%8A%D8%A9.rss");
                setTitle("احدث الافلام العربية");
            } else if (f == 20) {
                getNews("http://www6.mashy.com/%D8%B3%D9%8A%D9%86%D9%85%D8%A7/%D8%B4%D8%A7%D9%87%D8%AF%D8%AA-%D9%84%D9%83-%D9%81%D9%8A%D9%84%D9%85.rss");
                setTitle("شاهدت لك");
            } else if (f == 21) {
                getNews("http://www6.mashy.com/health/news/list?func=viewRss");
                setTitle("أخبار الصحة");
            } else if (f == 22) {
                getNews("http://www6.mashy.com/health/family/list?func=viewRss");
                setTitle("صحة الأسرة");
            } else if (f == 23) {
                getNews("http://www6.mashy.com/health/psychology/list?func=viewRss");
                setTitle("صحة نفسية");
            } else if (f == 24) {
                getNews("http://www6.mashy.com/health/digest/list?func=viewRss");
                setTitle("صحة هضمية");
            } else if (f == 25) {
                getNews("http://www6.mashy.com/health/man/list?func=viewRss");
                setTitle("صحة الرجل");
            } else if (f == 26) {
                getNews("http://www6.mashy.com/health/fitness/list?func=viewRss");
                setTitle("رشاقة");
            } else if (f == 27) {
                getNews("http://www6.mashy.com/health/vegetarian/list?func=viewRss");
                setTitle("نباتيات");
            } else if (f == 28) {
                getNews("http://www6.mashy.com/women/health/list?func=viewRss");
                setTitle("صحة المرأة");
            } else if (f == 29) {
                getNews("http://www6.mashy.com/health/alternative/list?func=viewRss");
                setTitle("الطب البديل");
            } else if (f == 30) {
                getNews("http://www6.mashy.com/health/advice/list?func=viewRss");
                setTitle("نصائح طبية");
            } else if (f == 31) {
                getNews("http://www6.mashy.com/women/beauty/list?func=viewRss");
                setTitle("جمالك");
            } else if (f == 32) {
                getNews("http://www6.mashy.com/women/style/list?func=viewRss");
                setTitle("اناقتك");
            } else if (f == 33) {
                getNews("http://www6.mashy.com/women/home_sweet_home/list?func=viewRss");
                setTitle("البيت السعيد");
            } else if (f == 34) {
                getNews("http://www6.mashy.com/women/mother/list?func=viewRss");
                setTitle("سنة أولى أمومة");
            } else if (f == 35) {
                getNews("http://www6.mashy.com/jokes/cartoon/ashraf_hamdy?func=viewRss");
                setTitle("كاريكاتير");
            } else if (f == 36) {
                getNews("http://www6.mashy.com/jokes/egyptian_jokes/list?func=viewRss");
                setTitle("نكت مصرية");
            } else if (f == 37) {
                getNews("http://www6.mashy.com/jokes/abou_el3araby/list?func=viewRss");
                setTitle("ركن ابو العربي");
            } else if (f == 38) {
                getNews("http://www6.mashy.com/jokes/funny_pics/pics_list?func=viewRss");
                setTitle("صور مضحكة");
            } else if (f == 39) {
                getNews("http://www6.mashy.com/jokes/silly_jokes/list?func=viewRss");
                setTitle("نكت رخمة");
            } else if (f == 40) {
                getNews("http://www6.mashy.com/jokes/arabian_jokes/list?func=viewRss");
                setTitle("نكت عربية");
            } else if (f == 41) {
                getNews("http://www6.mashy.com/jokes/riddles/list?func=viewRss");
                setTitle("فوازير");
            } else if (f == 42) {
                getNews("http://www6.mashy.com/quotes/arabian_quotes/list?func=viewRss");
                setTitle("أمثال عربية");
            } else if (f == 43) {
                getNews("http://www6.mashy.com/quotes/popular_quotes/list?func=viewRss");
                setTitle("أمثال شعبية");
            } else if (f == 44) {
                getNews("http://www6.mashy.com/quotes/proverb_story/list?func=viewRss");
                setTitle("قصة مثل");
            } else if (f == 45) {
                getNews("http://www6.mashy.com/quotes/wise/list?func=viewRss");
                setTitle("حكمة");
            } else if (f == 46) {
                getNews("http://www6.mashy.com/quotes/women_love/list?func=viewRss");
                setTitle("المرأة والحب");
            } else if (f == 47) {
                getNews("http://www6.mashy.com/quotes/celebrities_quotes/list?func=viewRss");
                setTitle("أقوال المشاهير");
            } else if (f == 48) {
                getNews("http://www6.mashy.com/quotes/people_proverbs/list?func=viewRss");
                setTitle("أمثال الشعوب");
            }
        }else {
            Toast.makeText(MainActivity.this, "Check your internet", Toast.LENGTH_LONG).show();
        }
    }
}

