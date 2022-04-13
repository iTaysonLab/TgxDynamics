package bruhcollective.itaysonlab.tgxthemegenerator.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import bruhcollective.itaysonlab.tgxthemegenerator.BuildConfig
import bruhcollective.itaysonlab.tgxthemegenerator.R
import bruhcollective.itaysonlab.tgxthemegenerator.data.Generator
import com.google.android.material.transition.MaterialFadeThrough
import java.io.File

class GeneratorFragment: Fragment(R.layout.fragment_generator) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enterTransition = MaterialFadeThrough()
    returnTransition = MaterialFadeThrough()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<View>(R.id.start).setOnClickListener {
      val f = File(requireContext().cacheDir, "generatedTheme.tgx-theme")

      if (f.exists()) f.delete()
      f.createNewFile()
      f.writeText(Generator.generateFileContent(ContextThemeWrapper(requireContext(), android.R.style.Theme_DeviceDefault)))

      val fileUri = FileProvider.getUriForFile(requireContext(), "${BuildConfig.APPLICATION_ID}.fileprovider", f)

      requireActivity().startActivity(Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND

        putExtra(Intent.EXTRA_STREAM, fileUri)
        type = "application/octet-stream"

        //setDataAndType(fileUri, "application/octet-stream")
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
      }, "Share a theme"))
    }
  }
}