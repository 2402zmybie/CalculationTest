package com.hr.calculationtest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.blankj.utilcode.util.SPUtils
import java.util.*
import java.util.logging.Level

/**
 *  //第一个数字 第二个数字 答案 当前得分, 最高纪录需要保存
 */
class MyViewModel(application: Application,val handle: SavedStateHandle) : AndroidViewModel(application) {

    private val KEY_HIGH_SCORE = "key_high_score"
    private val KEY_LEFT_NUMBER = "key_left_number"
    private val KEY_RIGHT_NUMBER = "key_right_number"
    private val KEY_OPERATOR = "key_operator"
    private val KEY_ANSWER = "key_answer"
    private val SAVE_SHP_DATA_NAME = "save_shp_data_name"
    private val KEY_CURRENT_SCORE = "key_current_score"
    public var win_flag = false

    init {
        if(!handle.contains(KEY_HIGH_SCORE)) {
            val highScore = SPUtils.getInstance(SAVE_SHP_DATA_NAME).getInt(KEY_HIGH_SCORE, 0)
            handle.set(KEY_HIGH_SCORE, highScore)
            handle.set(KEY_LEFT_NUMBER, 0)
            handle.set(KEY_RIGHT_NUMBER, 0)
            handle.set(KEY_OPERATOR, "+")
            handle.set(KEY_ANSWER, 0)
            handle.set(KEY_CURRENT_SCORE, 0)
        }
    }

    fun getLeftNumber():MutableLiveData<Int> {
        return handle.getLiveData(KEY_LEFT_NUMBER)
    }

    fun getRightNumber():MutableLiveData<Int> {
        return handle.getLiveData(KEY_RIGHT_NUMBER)
    }

    fun getOperator():MutableLiveData<String> {
        return handle.getLiveData(KEY_OPERATOR)
    }

    fun getHighScore():MutableLiveData<Int> {
        return handle.getLiveData(KEY_HIGH_SCORE)
    }

    fun getCurrentScore():MutableLiveData<Int> {
        return handle.getLiveData(KEY_CURRENT_SCORE)
    }

    fun getAnswer():MutableLiveData<Int> {
        return handle.getLiveData(KEY_ANSWER)
    }

    fun generator() {
        val LEVEL = 20
        val random = Random()
        var x=0
        var y=0
        x = random.nextInt(LEVEL) + 1
        y = random.nextInt(LEVEL) + 1
        if(x%2 == 0) {
            getOperator().value = "+"
            if(x > y) {
                // x = 10, y = 8
                getAnswer().value = x
                getLeftNumber().value = y
                getRightNumber().value = x -y
            }else {
                // x = 6 y = 8
                getAnswer().value = y
                getLeftNumber().value = x
                getRightNumber().value = y-x
            }
        }else {
            getOperator().value = "-"
            if (x>y) {
                getAnswer().value = x - y
                getLeftNumber().value = x
                getRightNumber().value = y
            } else {
                getAnswer().value = y - x
                getLeftNumber().value = y
                getRightNumber().value = x
            }
        }

    }


    fun save() {
        SPUtils.getInstance(SAVE_SHP_DATA_NAME).put(KEY_HIGH_SCORE, getHighScore().value!!)
    }

    fun answerCorrent() {
        getCurrentScore().value = getCurrentScore().value!! + 1
        if(getCurrentScore().value!! > getHighScore().value!!) {
            getHighScore().value = getCurrentScore().value
            win_flag = true
        }
        generator()
    }


}