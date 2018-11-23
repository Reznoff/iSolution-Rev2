package com.example.kemalmaulana.isolution.model.content

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName

data class Profile(
        @SerializedName("id_pelajar")
        val idPelajar: String? = null,

        @SerializedName("id_kelas")
        val idKelas: String? = null,

        @SerializedName("NIS")
        val nis: String? = null,

        @SerializedName("nisn")
        val nisn: String? = null,

        @SerializedName("nama_lengkap")
        val namaLengkap: String? = null,

        @SerializedName("tempat_lahir")
        val tempatLahir: String? = null,

        @SerializedName("tanggal_lahir")
        val tanggalLahir: String? = null,

        @SerializedName("jenis_kelamin")
        val jenisKelamin: String? = null,

        @SerializedName("alamat")
        val alamat: String? = null,

        @SerializedName("nama_ayah")
        val namaAyah: String? = null,

        @SerializedName("nama_ibu")
        val namaIbu: String? = null,

        @SerializedName("kontak")
        val kontak: String? = null,

        @SerializedName("status_pelajar")
        val statusPelajar: String? = null,

        @SerializedName("penjelasan")
        val penjelasan: String? = null,

        @SerializedName("id_paket")
        val idPaket: String? = null,

        @SerializedName("id_asrama")
        val idAsrama: String? = null,

        @SerializedName("anak_ke")
        val anakKe: String? = null,

        @SerializedName("agama")
        val agama: String? = null,

        @SerializedName("status_keluarga")
        val statusKeluarga: String? = null,

        @SerializedName("telepon")
        val telepon: String? = null,

        @SerializedName("tgl_penerimaan_kelas")
        val tglPenerimaanKelas: String? = null,

        @SerializedName("sekolah_asal")
        val sekolahAsal: String? = null,

        @SerializedName("kelas_diterima")
        val kelasDiterima: String? = null,

        @SerializedName("tgl_diterima_kelas")
        val tglDiterimaKelas: String? = null,

        @SerializedName("terakhir_ubah")
        val terakhirUbah: String? = null

)