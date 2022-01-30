package com.redenvy.justdoit.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.redenvy.justdoit.data.repository.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SyncJobService : JobService() {
    @Inject
    lateinit var repo: Repository
    private lateinit var intent: Intent
    override fun onStartJob(params: JobParameters): Boolean {
        CoroutineScope(Dispatchers.Default).launch {
            repo.syncToLocalDbFromAPI()
        }
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        stopService(intent)
        return false
    }
}