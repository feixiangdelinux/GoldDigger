package com.ccg.golddigger.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2021/9/27 9:38
 */
open class BaseViewModelC(application: Application) : AndroidViewModel(application), LifecycleObserver {
    /**
     * 请求成功的code
     */
    val SUCCESS_REQUEST_CODE = 200



    /**
     * 加载动画,true是开启,false是关闭
     */
    var loadingState = mutableStateOf(value = true)

    /**
     * 报错信息
     */
    val errMsg: MutableLiveData<String> = MutableLiveData()

    /**
     * 标识,从哪个页面来的
     *
     */
    var tag = 0

    /**
     * 如果联网请求出现异常,这个数据中就会有异常信息
     */
    val mException = MutableLiveData<Exception>()
    private val compositeDisposable by lazy { CompositeDisposable() }
    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(tryBlock, {
                Log.e("error","报错信息:  $it")
            }, {}, true)
        }
    }
    private suspend fun tryCatch(tryBlock: suspend CoroutineScope.() -> Unit,
                                 catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
                                 finallyBlock: suspend CoroutineScope.() -> Unit,
                                 handleCancellationExceptionManually: Boolean = false) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Exception) {
                if (e !is CancellationException || handleCancellationExceptionManually) {
                    mException.value = e
                    catchBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }

    /**
     * 添加订阅
     * @param disposable Disposable
     */
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}