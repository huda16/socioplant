package com.example.socioplant;

import java.util.ArrayList;

public class PlantsData {
    private static String[] plantNames = {
            "Bonsai",
            "Fiddle Fig",
            "Cactus",
            "Tulip"
    };

    private static String[] plantTypes = {
            "Indoor Type",
            "Outdoor Type",
            "Indoor Type",
            "Indoor Type"
    };

    private static String[] plantDescriptions = {
            "Salah seorang ulama dan khatib terkemuka di Masjid Besar Kasultanan Yogyakarta pada masa itu, dan ibu dari K.H. Ahmad Dahlan adalah puteri dari H. Ibrahim yang juga menjabat penghulu Kesultanan Ngayogyakarta Hadiningrat pada masa itu. KH. Ahmad Dahlan telah mempelopori kebangkitan ummat Islam untuk menyadari nasibnya sebagai bangsa terjajah yang masih harus belajar dan berbuat.",
            "Jenderal TNI Anumerta Ahmad Yani (juga dieja Achmad Yani; lahir di Purworejo, Jawa Tengah, 19 Juni 1922 – meninggal di Lubang Buaya, Jakarta, 1 Oktober 1965 pada umur 43 tahun) adalah komandan Tentara Nasional Indonesia Angkatan Darat, dan dibunuh oleh anggota Gerakan 30 September saat mencoba untuk menculik dia dari rumahnya.",
            "Sutomo (lahir di Surabaya, Jawa Timur, 3 Oktober 1920 – meninggal di Padang Arafah, Arab Saudi, 7 Oktober 1981 pada umur 61 tahun) lebih dikenal dengan sapaan akrab oleh rakyat sebagai Bung Tomo, adalah pahlawan yang terkenal karena peranannya dalam membangkitkan semangat rakyat untuk melawan kembalinya penjajah Belanda melalui tentara NICA, yang berakhir dengan pertempuran 10 November 1945 yang hingga kini diperingati sebagai Hari Pahlawan.",
            "Jenderal TNI (Purn.) Gatot Soebroto (lahir di Sumpiuh, Banyumas, Jawa Tengah, 10 Oktober 1907 – meninggal di Jakarta, 11 Juni 1962 pada umur 54 tahun) adalah tokoh perjuangan militer Indonesia dalam merebut kemerdekaan dan juga pahlawan nasional Indonesia. Ia dimakamkan di Ungaran, kabupaten Semarang."
    };

    private static int[] plantImages = {
            R.drawable.bonsai,
            R.drawable.fiddle,
            R.drawable.cactus,
            R.drawable.tulip
    };

    public static ArrayList<Plant> getListData() {
        ArrayList<Plant> list = new ArrayList<>();
        for (int position = 0; position < plantNames.length; position++) {
            Plant hero = new Plant();
            hero.setName(plantNames[position]);
            hero.setType(plantTypes[position]);
            hero.setDescription(plantDescriptions[position]);
            hero.setPhoto(plantImages[position]);
            list.add(hero);
        }
        return list;
    }
}
