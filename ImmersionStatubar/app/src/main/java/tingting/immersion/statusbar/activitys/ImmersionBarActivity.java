package tingting.immersion.statusbar.activitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tingting.immersion.homemodule.utils.StatusBarUtils;
import tingting.immersion.statusbar.R;
import tingting.immersion.homemodule.utils.StatusBarUtils2;

public class ImmersionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_immersion_bar);

        StatusBarUtils2.with(this)
                .setColor(getResources().getColor(R.color.blue))
//                .setDrawable(getResources().getDrawable(R.drawable.shape))
                .init(StatusBarUtils2.COLOR_FLAG);
    }
}
