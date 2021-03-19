package com.hr.calculationtest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    lateinit var controller: NavController;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        if(controller.currentDestination!!.id == R.id.questionFragment) {
            //答题界面
            var builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.quit_dialog_title)
            builder.setPositiveButton(R.string.dialog_positive_message) { p0, p1 ->
                controller.navigateUp()
            }
            builder.setNegativeButton(R.string.dialog_negative_message, DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder.create()
            builder.show()
        }
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if(controller.currentDestination!!.id == R.id.questionFragment) {
            onSupportNavigateUp()
        }else {
            super.onBackPressed()
        }

    }

}