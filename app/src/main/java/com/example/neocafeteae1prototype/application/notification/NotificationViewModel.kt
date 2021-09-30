package com.example.neocafeteae1prototype.application.notification

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class NotificationViewModel: ViewModel() {
    val list = listOf(
        AllModels.Notification("Ваш заказ готов", "Капучинно x2, Латте х2, Коффее х2", "18:13"),
        AllModels.Notification("Ваш заказ оформлен", "Капучинно x2, Латте х2, Коффее х2", "18:13"),
        AllModels.Notification("Вы закрыли счет", "Капучинно x2, Латте х2, Коффее х2", "18:13"),
    )
}