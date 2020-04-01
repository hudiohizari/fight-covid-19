package id.rumahawan.fightcovid19.navigation.model.data

class Hospital(
    var name: String? = "",
    var lat: String? = "",
    var lng: String? = "",
    var telephone: String? = "",
    var address: String? = "",
    var distance: Double? = 0.0
){
    companion object{
        fun empty(): Hospital{
            return Hospital("", "", "", "", "", 0.0)
        }
    }
}