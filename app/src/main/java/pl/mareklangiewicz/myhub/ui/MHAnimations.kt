package pl.mareklangiewicz.myhub.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Build
import android.view.View
import android.view.animation.LinearInterpolator
import pl.mareklangiewicz.mydrawables.MyMagicLinesDrawable

/**
 * Created by Marek Langiewicz on 01.02.16.
 * Some animations used in MHActivity
 */
internal class MHAnimations(private val mlogo: MagicLogo,private val mhomepage: MagicHomePage,private val mlines: MagicLines) {

    constructor(logo: View, homepage: View, lines: View, lcolor: Int, lwidth: Float)
    : this(MagicLogo(logo), MagicHomePage(homepage), MagicLines(lcolor, lwidth)) {
        lines.background = mlines
    }

    fun onGlobalDrawerOpened() { mlines.start() }
    fun onGlobalDrawerClosed() { mlines.stop() }
    fun onGlobalDrawerSlide(offset: Float) {
        mlogo.offset = offset
        mhomepage.offset = offset
    }


    /**
     * Some simple logo animation
     */
    internal class MagicLogo(logo: View) {

        private val animator = ObjectAnimator.ofPropertyValuesHolder(logo,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 0.2f, 1f),
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -130f, -30f, 0f)
        )

        init {
            animator.interpolator = LinearInterpolator()
        }

        var offset: Float = 0f
            set(value) {
                field = value
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1)
                    animator.setCurrentFraction(value)
            }
    }

    /**
     * Some simple home page presentation/animation
     * TODO SOMEDAY: open browser when clicked..
     */
    internal class MagicHomePage(homepage: View) {

        private val animator = ObjectAnimator.ofPropertyValuesHolder(homepage,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 0f, 0.7f),
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -50f, -50f, 0f)
        )

        init {
            // homepage.setOnClickListener {  }
            animator.interpolator = LinearInterpolator()
        }

        var offset: Float = 0f
            set(value) {
                field = value
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1)
                    animator.setCurrentFraction(value)
            }
    }

    /**
     * Little animation widget to make header view more interesting
     */
    internal class MagicLines(color: Int, width: Float) : MyMagicLinesDrawable() {

        private val animator = ObjectAnimator.ofInt(this, "level", 0, 10000)

        init {
            setColor(color)
            setStrokeWidth(width)
            animator.setDuration(1000).interpolator = LinearInterpolator()

        }

        fun start() {
            if (!animator.isStarted)
                animator.start()
        }

        fun stop() {
            animator.cancel()
            setLevel(0)
        }
    }

}

