package vnu.uet.tuan.myuet.KiemTraKetNoiInternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mtuan on 10/10/2016.
 */

public class Kiemtra  {
    private Context _context;

    //Hàm dựng khởi tạo đối tượng
    public Kiemtra(Context context) {
        this._context = context;
    }

    public boolean checkMobileInternetConn() {

        return isConected();

    }

    private boolean isConected() {
        //Tạo đối tương ConnectivityManager để trả về thông tin mạng
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //Nếu đối tượng khác null
        if (connectivity != null) {
            //Nhận thông tin mạng
            NetworkInfo info_wifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo info_data = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


            if (info_data != null || info_wifi != null) {
                //Tìm kiếm thiết bị đã kết nối được internet chưa
                if (info_data.isConnected()||info_wifi.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
}
