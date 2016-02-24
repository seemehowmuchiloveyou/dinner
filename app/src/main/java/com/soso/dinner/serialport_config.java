package com.soso.dinner;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;

import com.soso.dinner.serialport.SerialPort;
import com.soso.dinner.serialport.SerialPortFinder;


public class serialport_config extends PreferenceActivity {

    private SerialPort mSerialPort;
    private Application mApplication;
    private SerialPortFinder mSerialPortFinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mApplication = (Application) getApplication();
        mSerialPortFinder = mApplication.mSerialPortFinder;
//
        addPreferencesFromResource(R.xml.serial_port_preferences);
        setContentView(R.layout.activity_serialport_config);

        // Devices
        final ListPreference devices = (ListPreference)findPreference("DEVICE");
        String[] entries = mSerialPortFinder.getAllDevices();
        String[] entryValues = mSerialPortFinder.getAllDevicesPath();
        devices.setEntries(entries);
        devices.setEntryValues(entryValues);
        devices.setSummary(devices.getValue());
        devices.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary((String)newValue);
                return true;
            }
        });

        // Baud rates
        final ListPreference baudrates = (ListPreference)findPreference("BAUDRATE");
        baudrates.setSummary(baudrates.getValue());
        baudrates.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary((String)newValue);
                return true;
            }
        });
    }


}
