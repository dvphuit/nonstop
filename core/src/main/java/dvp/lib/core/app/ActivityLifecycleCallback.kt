package dvp.lib.core.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dvp.lib.common.extension.classTag
import dvp.lib.common.logger.logD

class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        logD(activity.classTag, "onCreate")
    }

    override fun onActivityStarted(activity: Activity) {
        logD(activity.classTag, "onStart")
    }

    override fun onActivityResumed(activity: Activity) {
        logD(activity.classTag, "onResume")
    }

    override fun onActivityPaused(activity: Activity) {
        logD(activity.classTag, "onPause")
    }

    override fun onActivityStopped(activity: Activity) {
        logD(activity.classTag, "onRonStopesume")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        logD(activity.classTag, "onSaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        logD(activity.classTag, "onDestroy")
    }
}
