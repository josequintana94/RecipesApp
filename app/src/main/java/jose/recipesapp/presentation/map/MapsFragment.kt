package jose.recipesapp.presentation.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import jose.recipesapp.R

class MapsFragment : Fragment() {
    private val args: MapsFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->
        println("args maps fragment: ${args.latLng}")
        val place = args.latLng
        googleMap.addMarker(MarkerOptions().position(place).title("Marker"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(place))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}