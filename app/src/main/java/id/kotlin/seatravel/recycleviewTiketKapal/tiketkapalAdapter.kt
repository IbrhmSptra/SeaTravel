package id.kotlin.seatravel.recycleviewTiketKapal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.seatravel.BatalkanPesanan.CancelTiketKapal
import id.kotlin.seatravel.R

class tiketkapalAdapter (private val tiketkapallist : ArrayList<datatTiketKapal>)
    : RecyclerView.Adapter<tiketkapalAdapter.kapalviewholder>() {

    class kapalviewholder(view : View) : RecyclerView.ViewHolder(view) {
        val card : CardView = view.findViewById(R.id.card)
        val tvasal : TextView = view.findViewById(R.id.rcasal)
        val tvtujuan : TextView = view.findViewById(R.id.rctujuan)
        val tvtgl : TextView = view.findViewById(R.id.rctgl)
        val tvdewasa : TextView = view.findViewById(R.id.rcdewasa)
        val tvanak : TextView = view.findViewById(R.id.rcanak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kapalviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kapal , parent , false)
        return kapalviewholder(view)
    }

    override fun onBindViewHolder(holder: kapalviewholder, position: Int) {
        val data = tiketkapallist[position]
        holder.tvasal.text = data.asal
        holder.tvtujuan.text = data.tujuan
        holder.tvtgl.text = data.tanggal
        holder.tvdewasa.text = data.dewasa
        holder.tvanak.text = data.anak
        holder.card.setOnClickListener {
            //intent ke CancelTiketKapal lempar semua data berdasarkan apa yang di klik
            val intent = Intent(holder.card.context , CancelTiketKapal::class.java)
            intent.putExtra("id" , data.id)
            intent.putExtra("asal" , data.asal)
            intent.putExtra("tujuan" , data.tujuan)
            intent.putExtra("tgl" , data.tanggal)
            intent.putExtra("dewasa" , data.dewasa)
            intent.putExtra("anak" , data.anak)
            holder.card.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return tiketkapallist.size
    }

}