package id.rumahawan.fightcovid19.navigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.navigation.model.data.Hospital
import kotlinx.android.synthetic.main.item_hospital.view.*
import kotlinx.android.synthetic.main.item_menu.view.container

class AdapterHospital(
    private var arrayList: MutableList<Hospital>
) : RecyclerView.Adapter<AdapterHospital.ViewHolder>() {
    var bridge: Interface? = null

    lateinit var context: Context

    fun setInterface(bridge: Interface?) {
        this.bridge = bridge
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hospital, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]

        holder.tvName.text = item.name
        holder.tvAddress.text = item.address
        holder.tvDistance.text = "${item.distance} Km dari tempatmu"

        holder.container.setOnClickListener {
            bridge?.onClickItemProduk(item, position)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var container = itemView.container
        internal var tvName = itemView.tvName
        internal var tvAddress = itemView.tvAddress
        internal var tvDistance = itemView.tvDistance
    }

    interface Interface {
        fun onClickItemProduk(item: Hospital, position: Int)
    }
}