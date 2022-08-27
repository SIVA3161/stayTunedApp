package com.sivag.staytuned.extension

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sivag.staytuned.R

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */

fun Activity.navigateTo(to: Class<*>, animatedUp: Boolean = false) {
    startActivity(Intent(this, to))
    if (animatedUp) overridePendingTransition(R.anim.enter_from_bottom, R.anim.exit_to_up)
    else overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
}

fun Activity.navigateTo(intent: Intent, animatedUp: Boolean = false) {
    startActivity(intent)
    if (animatedUp) overridePendingTransition(R.anim.enter_from_bottom, R.anim.exit_to_up)
    else overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
}

fun Activity.backTo(intent: Intent, animatedUp: Boolean = false) {
    startActivity(intent)
    if (animatedUp) overridePendingTransition(R.anim.enter_from_bottom, R.anim.exit_to_up)
    else overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
}

fun Activity.navigateForResultTo(requestCode: Int, intent: Intent, animatedUp: Boolean = false) {
    startActivityForResult(intent, requestCode)
    if (animatedUp) overridePendingTransition(R.anim.enter_from_bottom, R.anim.exit_to_up)
    else overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
}

fun Activity.navigateForResultTo(requestCode: Int, to: Class<*>, animatedUp: Boolean = false) {
    startActivityForResult(Intent(this, to), requestCode)
    if (animatedUp) overridePendingTransition(R.anim.enter_from_bottom, R.anim.exit_to_up)
    else overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
}

/**
 * The `fragment` is stack to the container view with id `frameId` and `tag`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.stackFragmentInActivity(fragment: Fragment, frameId: Int, tag: String?) {
    supportFragmentManager.transact {
        setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        replace(frameId, fragment)
        addToBackStack(tag)
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}