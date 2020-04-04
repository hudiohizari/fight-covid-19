package id.rumahawan.ifightco.features.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rumahawan.ifightco.R
import id.rumahawan.ifightco.features.main.model.data.Inbox
import kotlinx.android.synthetic.main.item_inbox.view.*

class AdapterInbox(
    private var arrayList: MutableList<Inbox>
) : RecyclerView.Adapter<AdapterInbox.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_inbox, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]

        holder.tvTitle.text = item.title
        holder.tvMessage.text = item.message
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvTitle = itemView.tvTitle
        internal var tvMessage = itemView.tvMessage
    }
}