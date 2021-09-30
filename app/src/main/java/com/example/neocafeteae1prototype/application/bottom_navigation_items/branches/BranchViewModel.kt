package com.example.neocafeteae1prototype.application.bottom_navigation_items.branches

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class BranchViewModel : ViewModel() {


    private val neobisInstagram = "https://www.instagram.com/neobis.kg/"
    private val neobisFacebook = "https://ru-ru.facebook.com/neobis.clubs/"

    val testBranchInfo = "Кофейня в самом центре города." +
            "Аромат настоящего кофе и уюта домашняя отмасфера "

    private val workTimeList = listOf<AllModels.BranchWorkTime>(
            AllModels.BranchWorkTime("Пн", "10:00", "22:00", false),
            AllModels.BranchWorkTime("Вт", "10:00", "22:00", true),
            AllModels.BranchWorkTime("Ср", "10:00", "22:00", false),
            AllModels.BranchWorkTime("Че", "10:00", "22:00", false),
            AllModels.BranchWorkTime("Пт", "10:00", "22:00", false)
    )

    val list = listOf<AllModels.NeoCafePlace>(
            AllModels.NeoCafePlace("https://media-cdn.tripadvisor.com/media/photo-s/1a/2b/ea/41/photo0jpg.jpg", "Панфиолова 55", "14:15", "https://go.2gis.com/ih9kh", workTimeList, "0777652485", testBranchInfo,neobisFacebook, neobisInstagram ),
            AllModels.NeoCafePlace("https://www.screenly.io/assets/coffee-shop-digital-sign-c1a802afd67812d4f641854893f2ea8b94ade4c9df445cac471c4f8f27208e94.png", "Горький 55", "15:15","https://go.2gis.com/vicnej", workTimeList, "0708092772", testBranchInfo, neobisFacebook, neobisInstagram),
            AllModels.NeoCafePlace("https://mir-s3-cdn-cf.behance.net/project_modules/1400/f5843852864027.591f18d2ba0f8.jpg", "Тыныстанова 55", "16:15","https://go.2gis.com/1146su", workTimeList, "0777895756", testBranchInfo, neobisFacebook, neobisInstagram)
    )




}