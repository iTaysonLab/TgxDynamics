package bruhcollective.itaysonlab.tgxthemegenerator.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import bruhcollective.itaysonlab.tgxthemegenerator.BuildConfig
import bruhcollective.itaysonlab.tgxthemegenerator.R
import com.google.android.material.transition.MaterialFadeThrough

class AboutFragment: Fragment(R.layout.fragment_about) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enterTransition = MaterialFadeThrough()
    returnTransition = MaterialFadeThrough()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<TextView>(R.id.version).text = "version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}, ${BuildConfig.BUILD_TYPE})"

    view.findViewById<View>(R.id.author).openLink("https://t.me/itaysonlab")
    view.findViewById<View>(R.id.channel).openLink("https://t.me/bruhcollective")
    view.findViewById<View>(R.id.source).openLink("https://github.com/iTaysonLab/TgxDynamics")
  }

  private fun View.openLink(url: String) {
    setOnClickListener {
      it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
  }
}