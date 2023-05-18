//package com.example.orcas.ui.dialogs
//
//import android.Manifest
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore
//import android.util.SparseArray
//import android.view.LayoutInflater
//import android.view.SurfaceHolder
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import androidx.databinding.DataBindingUtil
//import com.google.android.gms.vision.CameraSource
//import com.google.android.gms.vision.Detector
//import com.google.android.gms.vision.barcode.Barcode
//import com.google.android.gms.vision.barcode.BarcodeDetector
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//import com.example.orcas.R
//import com.example.orcas.databinding.FragmentQrScannerBinding
//
//import com.example.orcas.util.extensions.showLocalNotification
//import com.example.orcas.util.LocalNotificationType
////import com.tbruyelle.rxpermissions3.RxPermissions
//import me.dm7.barcodescanner.zbar.Result
//
//private const val CAMERA_PERMISSION = 1098
//class QRScannerDialog(
//    val onQrRead: (String, BottomSheetDialogFragment) -> Unit,
//    val onDismiss: () -> Unit = {}
//) : BottomSheetDialogFragment(), QRScannerViewDelegate, SurfaceHolder.Callback {
//
//    private lateinit var binding: FragmentQrScannerBinding
//
//    private var holder: SurfaceHolder? = null
//    private var called = false
//    private val barcode: BarcodeDetector by lazy {
//        BarcodeDetector
//            .Builder(this.requireActivity())
//            .setBarcodeFormats(Barcode.QR_CODE)
//            .build()
//    }
//    private val cameraSource: CameraSource by lazy {
//        CameraSource
//            .Builder(this.requireActivity(), barcode)
//            .setFacing(CameraSource.CAMERA_FACING_BACK)
//            .setRequestedFps(24F)
//            .setAutoFocusEnabled(true)
//            .setRequestedPreviewSize(1024, 1024)
//            .build()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qr_scanner, container, false)
//        binding.delegate = this
//        return binding.root
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        val sheetContainer = requireView().parent as? ViewGroup ?: return
//        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//
//        RxPermissions(requireActivity())
//            .request(Manifest.permission.CAMERA)
//            .subscribe { isGranted ->
//                if (isGranted) {
//                   initScanner()
//                } else {
//                    // Permission not granted -> Do your own stuff here
//                }
//            }
//    }
//
//    override fun onStop() {
//        cameraSource.stop()
//        cameraSource.release()
//        barcode.release()
//        super.onStop()
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == CAMERA_PERMISSION){
//            onStart()
//        }
//    }
//
//    override fun onClose() {
//        onDismiss()
//        dismiss()
//    }
//
//    private fun initScanner() {
//        binding.apply {
//            cameraSurface.setZOrderMediaOverlay(true)
//            cameraSurface.holder.addCallback(this@QRScannerDialog)
//        }
//
//        holder = this.binding.cameraSurface.holder
//
//        barcode.setProcessor(object : Detector.Processor<Barcode> {
//            override fun release() {}
//
//            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
//                val codes: SparseArray<Barcode> = detections?.detectedItems ?: SparseArray()
//                if (codes.size() > 0) {
//                    val rawResult = Result()
//                    rawResult.contents = codes.valueAt(0).displayValue
//                    parseQR(rawResult)
//                }
//            }
//        })
//    }
//
//    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
//
//    override fun surfaceDestroyed(holder: SurfaceHolder) {}
//
//    override fun surfaceCreated(holder: SurfaceHolder) {
//        try {
//            if (
//                ContextCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.CAMERA
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                cameraSource.start(binding.cameraSurface.holder)
//            }
//        } catch (ex: Throwable) {
//            cameraSource.stop()
//
//            showLocalNotification(
//                LocalNotificationType.ERROR,
//                ex.localizedMessage ?: ""
//            )
//        } catch (ex: Exception) {
//            cameraSource.stop()
//            showLocalNotification(
//                LocalNotificationType.ERROR,
//                ex.localizedMessage ?: ""
//            )
//        }
//    }
//
//
//    private fun parseQR(qr: Result) {
//        if (!called) {
//            onQrRead(qr.contents,this)
//        }
//        called = true
//    }
//}
//
//
//interface QRScannerViewDelegate {
//    fun onClose()
//}