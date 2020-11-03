package com.example.tabactivity.database

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder
import java.util.*

object RegionQuery {
    fun getReferenceRegion(phoneNum: String?): String? {
        val phoneUtil = PhoneNumberUtil.getInstance()
        val geocoder: PhoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance()
        val language = "CN"
        var referencePhonenumber: Phonenumber.PhoneNumber? = null
        var referenceRegion = ""
        try {
            referencePhonenumber = phoneUtil.parse(phoneNum, language)
            // 手机号码归属城市 referenceRegion
            referenceRegion = geocoder.getDescriptionForNumber(referencePhonenumber, Locale.CHINA)
        } catch (e: NumberParseException) {
            e.printStackTrace()
        }
        return referenceRegion
    }
}