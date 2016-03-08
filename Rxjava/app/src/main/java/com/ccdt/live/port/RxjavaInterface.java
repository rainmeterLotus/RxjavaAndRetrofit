package com.ccdt.live.port;

import android.os.Bundle;

/**
 * Created by rain on 2016/1/4.
 */
public interface RxjavaInterface {

    void onRxjavaComplete(int requestCode);

    void onRxjavaError(Throwable exception);

    void onRxjavaSuccess(int requestCode,Bundle bundle);

}
