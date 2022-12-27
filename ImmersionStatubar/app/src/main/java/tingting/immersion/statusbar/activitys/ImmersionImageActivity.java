package tingting.immersion.statusbar.activitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tingting.immersion.statusbar.R;
import tingting.immersion.homemodule.utils.StatusBarUtils2;

/**
 * 沉浸式图片
 */
public class ImmersionImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_image);

        StatusBarUtils2.with(this).init(StatusBarUtils2.DEFAULT_FLAG);
    }
}
