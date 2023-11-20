package jose.recipesapp.data.source

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeolocationProvider @Inject constructor() {

    fun getGeocoding(locationName: String?): Single<LatLng> {
        return Single.create { emitter ->
            try {
                val url =
                    URL(
                        "https://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(
                            locationName,
                            "UTF-8"
                        ) + "&key=" + "AIzaSyA1-mcGjfKsRhbRtzY80s1_OIg03qXgeFQ"
                    )
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connect()
                val `in` = BufferedReader(
                    InputStreamReader(
                        urlConnection.inputStream
                    )
                )
                var line: String?
                val response = StringBuilder()
                while (`in`.readLine().also { line = it } != null) {
                    response.append(line)
                }
                `in`.close()
                val latLng = getLatLngFromGeocoding(response.toString())
                emitter.onSuccess(latLng!!)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    private fun getLatLngFromGeocoding(jsonResponse: String?): LatLng? {
        try {
            val jsonObject = JSONObject(jsonResponse)
            val results = jsonObject.getJSONArray("results")
            if (results.length() > 0) {
                val location =
                    results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location")
                val lat = location.getDouble("lat")
                val lng = location.getDouble("lng")
                return LatLng(lat, lng)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }
}