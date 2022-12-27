package tingting.immersion.statusbar.activitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tingting.immersion.statusbar.R;
import tingting.immersion.homemodule.utils.StatusBarUtils2;


public class ImmersionActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_action_bar);

        StatusBarUtils2.with(this)
                .setIsActionBar(true)
                .clearActionBarShadow()
                .setDrawable(getResources().getDrawable(R.drawable.shape))
                .init(StatusBarUtils2.DRAWABLE_FLAG);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));
    }
}
