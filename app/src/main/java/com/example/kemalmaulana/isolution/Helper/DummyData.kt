package com.example.kemalmaulana.isolution.Helper

object DummyData {
    val nilai = listOf(
            Nilai("MP01", "Agama", "2016",70, 'A',95),
            Nilai("MP02", "Matematika", "2016",70,'C', 65),
            Nilai("MP03", "IPS", "2016",70,'A', 92),
            Nilai("MP04", "IPA", "2016",70,'B', 70)
    )

    val spiritual = listOf(
            SikapSpiritual('A', "Berdoa dan muroja'ah sebelum belajar sering dengan sungguh-sungguh")
    )

    val sosial = listOf(
            SikapSosial('B', "Sering menyampaikan informasi dengan jujur, santun dalam berperilaku, bertanggungjawab dalam pekerjaan, disiplin dalam melaksanakan tugas, percaya diri dalam deklamasi lisan")
    )

    val jadwalPelajaran = listOf(
            Pelajaran("Senin", "Agama", "Yusuf Syariffudin S.Ag", "2016", "B", "8.30", "10.00", "2018"),
            Pelajaran("Senin", "Matematika", "Maman Fatman S.T", "2016", "B", "10.00", "12.00", "2018"),
            Pelajaran("Senin", "IPS", "Drs. Yoyom Maemunah", "2016", "B", "13.00", "14.40", "2018"),
            Pelajaran("Senin", "IPA", "Elis Sumiyati S.Si", "2016", "B", "14.40", "16.00", "2018")
    )

    val daftarKehadiran = listOf(
            Kehadiran("Abrar Shidiq Safwan", "IX B", "2018-08-16 / 09:44:13", "Hadir"),
            Kehadiran("Abrar Shidiq Safwan", "IX B", "2018-08-14 / 11:07:28", "Hadir"),
            Kehadiran("Abrar Shidiq Safwan", "IX B", "2018-08-13 / 13:34:03", "Hadir")
    )

    class Nilai(val kode: String, val nama: String, val tahunAjaran: String, val kkm: Int, val predikat: Char, val nilai: Int)
    class SikapSpiritual(val predikat: Char, val keterangan: String)
    class SikapSosial(val predikat: Char, val keterangan: String)
    class Pelajaran(val hari: String, val nama_pelajaran: String, val pengajar: String, val kurikulum: String, val kelas: String, val jam_masuk: String, val jam_keluar: String, val tahun_pelajaran: String)
    class Kehadiran(val nama: String, val kelas: String, val waktu: String, val status: String)
}