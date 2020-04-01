package com.example.a3130_group17_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/** Activity for viewing the product list.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    List<AuthUI.IdpConfig> authproviders;
    private FirebaseUser user;
    private Button signinButton;
    private TextView userName, userEmail;
    private Menu drawerMenu;
    private NavController navController;
    private NavigationView navView;

    private int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinButton = findViewById(R.id.signin_button);
        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.navdrawer);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        drawerMenu = navView.getMenu();
        drawerLayout = findViewById(R.id.drawer_layout);
        userName = navView.getHeaderView(0).findViewById(R.id.nav_drawer_header_display_name);
        userEmail = navView.getHeaderView(0).findViewById(R.id.nav_drawer_header_email);
        authproviders = Arrays.asList( new AuthUI.IdpConfig.EmailBuilder().build() );

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            signinButton.setText("Sign Out");
            signinButton.setBackgroundResource(R.color.signout_button);

            userName.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());
        } else {
            signinButton.setBackgroundResource(R.color.signin_button);
            drawerMenu.getItem(1).setEnabled(false);
        }

        setSupportActionBar(toolbar);

        drawerMenu.getItem(0).setChecked(true);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                for (int i = 0; i < drawerMenu.size(); i++) {
                    drawerMenu.getItem(i).setChecked(false);
                }
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.log_summary_option:
                        navController.navigate(R.id.seeLogFragment);
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.product_list_option:
                        navController.navigate(R.id.productListFragment);
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return true;
            }
        });


        Set<Integer> topLevelDests = new HashSet<Integer>();
        topLevelDests.add(R.id.seeLogFragment);
        topLevelDests.add(R.id.productListFragment);


        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(topLevelDests)
                        .setDrawerLayout(drawerLayout)
                        .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                                case android.R.id.home:
                                drawerLayout.openDrawer(Gravity.LEFT);
                       }
                        return true;
    }

    /**
     * Onclick method for the signin button.
     * @param view the view that was pressed.
     */
    public void signIn(View view) {
        if (user == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(authproviders)
                            .setTheme(R.style.FirebaseAuthUI)
                            .build(),
                    RC_SIGN_IN);
        }
        else {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            signinButton.setText("Sign In");
                            signinButton.setBackgroundResource(R.color.signin_button);
                            user = null;

                            userName.setText("Not Signed In");
                            userEmail.setText("Click below to sign in!");
                            drawerMenu.getItem(1).setEnabled(false);
                            navController.navigate(R.id.productListFragment);
                            drawerMenu.getItem(0).setChecked(true);
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            signinButton.setText("Sign Out");
            signinButton.setBackgroundResource(R.color.signout_button);

            userName.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());
            drawerMenu.getItem(1).setEnabled(true);

        }
    }
}