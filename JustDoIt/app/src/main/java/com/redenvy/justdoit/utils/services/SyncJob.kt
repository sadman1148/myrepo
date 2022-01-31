package com.redenvy.justdoit.utils.services


import android.app.job.JobParameters
import android.app.job.JobService
import com.redenvy.justdoit.data.repository.Repository
import com.redenvy.justdoit.utils.BroadCaster
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SyncJob : JobService() {
    @Inject
    lateinit var repo: Repository
    override fun onStartJob(p0: JobParameters?): Boolean {
        CoroutineScope(Dispatchers.Default).launch { repo.syncToLocalDbFromAPI() }
            .invokeOnCompletion {
                BroadCaster.sendNewBroadcast(
                    applicationContext
                )
            }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }
}