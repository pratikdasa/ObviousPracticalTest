package com.obvioustest.ui

import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.obviouspracticaltest.R
import com.obviouspracticaltest.models.GalleryModel
import com.obvioustest.room.GalleryRoomDatabase
import kotlinx.android.synthetic.main.activity_preview.*



class PreviewActivity : AppCompatActivity() {

    var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_preview)
        position = intent.getIntExtra("pos", -1)
        SelectRecord().execute()
    }

    inner class SelectRecord : AsyncTask<Void, Void, List<GalleryModel>>() {
        override fun doInBackground(vararg p0: Void?): List<GalleryModel>? {
            return GalleryRoomDatabase.getDatabase(this@PreviewActivity).galleryDao().getGalleryList()
        }

        override fun onPostExecute(list: List<GalleryModel>?) {
            super.onPostExecute(list)
            val myCustomPagerAdapter = MyCustomPagerAdapter(this@PreviewActivity, list!!)
            viewPager.adapter = myCustomPagerAdapter
            viewPager.currentItem = position
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //To support reverse transitions when user clicks the device back button
        supportFinishAfterTransition()
    }

}