package id.kotlin.seatravel.recycleviewTiketKamar

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.seatravel.BatalkanPesanan.CancelTiketKamar
import id.kotlin.seatravel.BatalkanPesanan.CancelTiketKapal
import id.kotlin.seatravel.R


class tiketkamarAdapter (private val tiketkamarlist : ArrayList<dataTiketKamar>)
    : RecyclerView.Adapter<tiketkamarAdapter.kamarviewholder>() {

    class kamarviewholder(view : View) : RecyclerView.ViewHolder(view) {
        val card : CardView = view.findViewById(R.id.card)
        val tvnama : TextView = view.findViewById(R.id.rcnama)
        val tvkamar : TextView = view.findViewById(R.id.rckamar)
        val tvjmlkamar : TextView = view.findViewById(R.id.rcjmlkamar)
        val tvcheckin : TextView = view.findViewById(R.id.rccheckin)
        val tvcheckout : TextView = view.findViewById(R.id.rccheckout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kamarviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kamar , parent , false)
        return kamarviewholder(view)
    }

    override fun onBindViewHolder(holder: kamarviewholder, position: Int) {
        val data = tiketkamarlist[position]
        holder.tvnama.text = data.nama
        holder.tvkamar.text = data.kamar
        holder.tvjmlkamar.text = data.jmlKamar
        holder.tvcheckin.text = data.checkin
        holder.tvcheckout.text = data.checkout
        holder.card.setOnClickListener {
            //intent ke CancelTiketKamar lempar semua data
            val intent = Intent(holder.card.context , CancelTiketKamar::class.java)
            intent.putExtra("id" , data.id)
            intent.putExtra("nama" , data.nama)
            intent.putExtra("kamar" , data.kamar)
            intent.putExtra("jmlkamar" , data.jmlKamar)
            intent.putExtra("checkin" , data.checkin)
            intent.putExtra("checkout" , data.checkout)
            holder.card.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return tiketkamarlist.size
    }

}