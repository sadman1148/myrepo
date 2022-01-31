package com.redenvy.justdoit.ui.Activity

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.IntentFilter
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.databinding.ActivityMainBinding
import com.redenvy.justdoit.utils.services.MyBroadcastReceiver
import com.redenvy.justdoit.utils.services.SyncJob
import com.redenvy.justdoit.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener
    private lateinit var sharedPreferences: SharedPreferences

    private val viewModel: TodoListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val broadcastReceiver by lazy {
        MyBroadcastReceiver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Registering the broadcast receiver
        registerReceiver(broadcastReceiver, IntentFilter(Constants.DATA_UPDATE_INTENT))

        // initializing the setting change handler
        handleSharedPreference()

        // initializing the theme handler
        themeHandler()

        // Initiate a sync with cloud when App is launched for the very first time
        firstInit()
    }

    /**
     * This handles changes in the settings
     */
    fun handleSharedPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences?, key: String? ->
                when (key) {
                    Constants.SYNC_KEY -> {
                        handleSyncJob()
                    }
                    Constants.SYNC_INTERVAL_KEY -> {
                        scheduleSync()
                    }
                    Constants.THEME_KEY -> {
                        themeHandler()
                    }
                }
            }
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener)
    }

    /**
     * Handles any changes made to the theme setting in the settings menu.
     */
    fun themeHandler() {
        when (sharedPreferences.getString("theme", Constants.FOLLOW_SYSTEM)) {
            Constants.DARK_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            Constants.LIGHT_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            Constants.FOLLOW_SYSTEM -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    /**
     * Handles any changes made to the Sync settings in the settings menu.
     */
    private fun handleSyncJob() {
        val syncStatus = sharedPreferences.getBoolean(getString(R.string.sync_header), false)

        // this if block checks if the sync is enabled or not
        if (!syncStatus) {
            val jobScheduler = application.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

            // if sync is turned off by the user, then previous sync job will be cancelled
            jobScheduler.cancel(Constants.SYNC_JOB_ID)
        } else {

            // otherwise, sync will be scheduled
            scheduleSync()
        }
    }

    /**
     * this function performs an immediate sync and schedules for future syncs
     */
    fun scheduleSync() {
        val jobScheduler = application.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        // cancelling any previous jobs
        jobScheduler.cancel(Constants.SYNC_JOB_ID)

        // getting user's selected time from settings
        val userSelectedTime = sharedPreferences.getString(getString(R.string.syncInterval_Key), getString(R.string.default_sync_interval))!!.toLong()
        val componentName = ComponentName(applicationContext, SyncJob::class.java)
        val jobInfo = JobInfo.Builder(Constants.SYNC_JOB_ID, componentName)
            .setPeriodic(Constants.ONE_HOUR_DELAY_TIME * userSelectedTime)
            .setPersisted(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .build()
        val result = jobScheduler.schedule(jobInfo)
        if (result == JobScheduler.RESULT_SUCCESS) {
            Timber.e("Sync will be performed in $userSelectedTime hour intervals")
        }
    }

    /**
     * this function executes a database sync if the App is being run for the very first time
     */
    private fun firstInit() {
        if (sharedPreferences.getBoolean(Constants.FIRST_LAUNCH_KEY, true)) {
            sharedPreferences.edit().putBoolean(Constants.FIRST_LAUNCH_KEY, false).apply()
            scheduleSync()
            Timber.e("App is being run the very first time")
        }
    }
}