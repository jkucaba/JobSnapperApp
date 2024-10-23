package jk.jobsnapper.controllers.UtilControllers

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.Manifest
import jk.jobsnapper.databinding.FragmentProfileBinding
import jk.jobsnapper.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private var selectedImageUri: Uri? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            pickImageLauncher.launch("image/*")
        } else {
            Log.e("ProfileFragment", "Permission denied")
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        var result: String? = null
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            if (idx != -1) {
                result = cursor.getString(idx)
            }
            cursor.close()
        }
        return result
    }

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.profilePicture.setImageURI(it)
            runBlocking {
                withContext(Dispatchers.IO) {
                    val filePath = getRealPathFromURI(it)
                    if (filePath != null) {
                        val file = File(filePath)
                        val requestFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                        val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, requestFile)
                        Model.getInstanceWC().uploadProfileImage(Model.getInstanceWC().user!!.iduser, body)
                    } else {
                        Log.e("ProfileFragment", "Failed to get file path from URI")
                    }
                }
            }
        }
    }
    private fun loadProfileImage(userId: Long) {
        runBlocking {
            withContext(Dispatchers.IO) {
                try {
                    val imageBytes = Model.getInstanceWC().fetchProfileImage(userId)
                    if (imageBytes != null) {
                        withContext(Dispatchers.Main) {
                            binding.profilePicture.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size))
                        }
                    } else {
                        Log.e("ProfileFragment", "Failed to load profile image")
                    }
                } catch (e: IOException) {
                    Log.e("ProfileFragment", "Failed to load profile image", e)
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        runBlocking {
            withContext(Dispatchers.IO) {
                val user = Model.getInstanceWC().user
                binding.firstNameEt.setText(user.firstName ?: "")
                binding.lastNameEt.setText(user.lastName ?: "")
                binding.bioEt.setText(user.profile ?: "")
                binding.abilitiesEt.setText(user.abilities ?: "")

                val genderPosition = when (user.sex) {
                    "Male" -> 0
                    "Female" -> 1
                    "Not Specified" -> 2
                    else -> 2
                }
                binding.genderSpinner.setSelection(genderPosition)
                binding.phoneNumber.setText(user.phone.toString() ?: "")
                binding.birthdayTv.text = user.birthday ?: ""
                user.profileImage?.let {
                    binding.profilePicture.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))
                }

            }
        }
        binding.changePictureBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImageLauncher.launch("image/*")
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

        return binding.root
    }

}