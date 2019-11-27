package com.example.rummates;

        import android.os.Bundle;
        import android.view.MenuItem;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;

        import com.example.rummates.fragments.NoteFragment;
        import com.example.rummates.fragments.ShoppingListFragment;
        import com.example.rummates.fragments.ProfileFragment;
        import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingListFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment selectedFragment = null;

            switch(item.getItemId())
            {
                case R.id.bottom_nav_shoppinglist:
                    selectedFragment = new ShoppingListFragment();
                    break;

                case R.id.bottom_nav_notes:
                    selectedFragment = new NoteFragment();
                    break;

                case R.id.bottom_nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

//    @Override
//    public void onBackPressed() {
//
//    }
}
