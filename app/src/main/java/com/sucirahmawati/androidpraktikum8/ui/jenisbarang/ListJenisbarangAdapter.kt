package com.sucirahmawati.androidpraktikum8.ui.jenisbarang

import android.app.AlertDialog
import android.telecom.Call
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sucirahmawati.androidpraktikum8.model.Jenisbarang
import com.sucirahmawati.androidpraktikum8.network.ApiService
import okhttp3.Response
import java.util.*

class ListJenisbarangAdapter(private val listJenisbarang: ArrayList<Jenisbarang.JenisbarangData>) : RecyclerView.Adapter<ListJenisbarangAdapter.ListViewHolder>() {

    class ListViewHolder(private val binding: ItemRowJenisbarangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(jenisbarangData: Jenisbarang.JenisbarangData) {
            with(binding){
                tvNamaJenisbarang.text = jenisbarangData.namajenisbarang
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowJenisbarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listJenisbarang[position])
    }

    override fun getItemCount():Int = listJenisbarang.size

    btnDelete.setOnClickListener {
        AlertDialog.Builder(binding.root.context)
            .setMessage("Data akan dihapus, lanjutkan?")
            .setCancelable(false)
            .setPositiveButton("Ya") { dialog, _ ->

                ApiService.Api.retrofitService.delete(jenisbarangData)
                    .enqueue(object :
                        Callback<Jenisbarang.JenisbarangResponse> {
                        override fun onResponse(
                            call: Call<Jenisbarang.JenisbarangResponse>,
                            response: Response<Jenisbarang.JenisbarangResponse>
                        ) {
                            Toast.makeText(
                                binding.root.context, response.body()?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            listJenisbarang.removeAt(bindingAdapterPosition)
                            bindingAdapter?.notifyItemRemoved(bindingAdapterPosition)
                        }

                        override fun onFailure(
                            call: Call<Jenisbarang.JenisbarangResponse>,
                            t: Throwable
                        ) {
                            Toast.makeText(
                                binding.root.context,
                                t.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}