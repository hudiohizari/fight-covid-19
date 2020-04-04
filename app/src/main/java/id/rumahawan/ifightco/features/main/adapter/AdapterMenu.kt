package id.rumahawan.ifightco.features.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rumahawan.ifightco.features.main.model.data.MenuItem
import kotlinx.android.synthetic.main.item_menu.view.*

class AdapterMenu(
    private var arrayList: MutableList<MenuItem>,
    private var layout: Int
) : RecyclerView.Adapter<AdapterMenu.ViewHolder>() {
    var bridge: Interface? = null

    lateinit var context: Context

    fun setInterface(bridge: Interface?) {
        this.bridge = bridge
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]

        if (!item.isActive) holder.container.alpha = 0.6f
        holder.ivMenu.setImageResource(item.img)
        holder.tvMenu.text = item.name

        holder.container.setOnClickListener {
            bridge?.onClickItemProduk(item, position)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var container = itemView.container
        internal var ivMenu = itemView.ivMenu
        internal var tvMenu = itemView.tvMenu
    }

    interface Interface {
        fun onClickItemProduk(item: MenuItem?, position: Int)
    }
}