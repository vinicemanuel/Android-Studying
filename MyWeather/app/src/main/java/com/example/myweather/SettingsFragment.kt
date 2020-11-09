package com.example.myweather

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SettingsFragment : Fragment() {

    private val TAG = "SettingsFragment"

    private lateinit var saveButton: Button
    private lateinit var rgTemperature: RadioGroup
    private lateinit var rgLanguageRange: RadioGroup
    private lateinit var rbTempC: RadioButton
    private lateinit var rbTempF: RadioButton
    private lateinit var rbLangPT: RadioButton
    private lateinit var rbLangEN: RadioButton

    private var temperatureUnit = "C º"
    private var language = "PT"

    private var prefs: SharedPreferences? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.configPrefs()
        this.configTemperature(view)
        this.configLanguage(view)
        this.configSaveButton(view)
        this.configData(view)
    }

    private fun configPrefs() {
        this.prefs = context?.getSharedPreferences("my_weather_prefs", Context.MODE_PRIVATE)
    }

    private fun configData(view: View) {
        this.rbTempC = view.findViewById(R.id.rb_temperature_c)
        this.rbTempF = view.findViewById(R.id.rb_temperature_f)

        this.rbLangEN = view.findViewById(R.id.rb_language_en)
        this.rbLangPT = view.findViewById(R.id.rb_language_pt)

        val language = this.prefs?.getString("LANGUAGE","PT")
        val temperatureUnit = this.prefs?.getString("TEMPERATURE_UNIT", "C º")

        when (language) {
            "EN" -> rbLangEN.isChecked = true
            "PT" -> rbLangPT.isChecked = true
        }

        when (temperatureUnit) {
            "C º" -> rbTempC.isChecked = true
            "F º" -> rbTempF.isChecked = true
        }
    }

    private fun configTemperature(view: View) {
        this.rgTemperature = view.findViewById(R.id.rg_temperature_unit)
        this.rgTemperature.setOnCheckedChangeListener { view, id ->
            val radioButton = view.findViewById<RadioButton>(id)

            if (radioButton.isChecked) {
                when (radioButton.id) {
                    R.id.rb_temperature_c -> this.temperatureUnit = "C º"
                    R.id.rb_temperature_f -> this.temperatureUnit = "F º"
                }
            }
        }
    }

    private fun configLanguage(view: View) {
        this.rgLanguageRange = view.findViewById(R.id.rg_language)
        this.rgLanguageRange.setOnCheckedChangeListener { view, id ->
            val radioButton = view.findViewById<RadioButton>(id)

            if (radioButton.isChecked) {
                when (radioButton.id) {
                    R.id.rb_language_en -> this.language = "EN"
                    R.id.rb_language_pt -> this.language = "PT"
                }
            }
        }
    }

    private fun configSaveButton(view: View) {
        this.saveButton = view.findViewById(R.id.btn_save)
        this.saveButton.setOnClickListener {
            val editor = this.prefs?.edit()

            editor?.putString("TEMPERATURE_UNIT", temperatureUnit)
            editor?.putString("LANGUAGE", language)
            editor?.apply()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}