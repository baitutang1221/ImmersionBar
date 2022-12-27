package tingting.immersion.statusbar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tingting.immersion.homemodule.HomeActivity
import tingting.immersion.statusbar.activitys.ImmersionActionBarActivity
import tingting.immersion.statusbar.activitys.ImmersionBarActivity
import tingting.immersion.statusbar.activitys.ImmersionImageActivity
import tingting.immersion.statusbar.activitys.ImmersionNavActivity

class MainActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button -> startActivity(
                Intent(
                    this,
                    ImmersionBarActivity::class.java
                )
            )
            R.id.button2 -> startActivity(
                Intent(
                    this,
                    ImmersionImageActivity::class.java
                )
            )
            R.id.button3 -> startActivity(
                Intent(this,
                    ImmersionNavActivity::class.java
                )
            )
            R.id.button4 -> startActivity(
                Intent(
                    this,
                    ImmersionActionBarActivity::class.java
                )
            )
            R.id.button5 -> startActivity(
                Intent(
                    this,
                    HomeActivity::class.java
                )
            )
            else -> {
            }
        }
    }
}