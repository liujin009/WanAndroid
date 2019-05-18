package per.goweii.wanandroid.module.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import per.goweii.basic.core.base.BaseActivity;
import per.goweii.basic.core.mvp.MvpPresenter;
import per.goweii.basic.utils.AppInfoUtils;
import per.goweii.wanandroid.R;
import per.goweii.wanandroid.module.main.activity.WebActivity;

/**
 * @author CuiZhen
 * @date 2019/5/17
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class AboutWanAndroidActivity extends BaseActivity {

    @BindView(R.id.tv_version_name)
    TextView tv_version_name;
    @BindView(R.id.tv_web)
    TextView tv_web;
    @BindView(R.id.tv_about)
    TextView tv_about;
    @BindView(R.id.ll_web)
    LinearLayout ll_web;
    @BindView(R.id.ll_about)
    LinearLayout ll_about;

    public static void start(Context context){
        Intent intent = new Intent(context, AboutWanAndroidActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_wan_android;
    }

    @Nullable
    @Override
    protected MvpPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        tv_version_name.setText(AppInfoUtils.getVersionName());
    }

    @Override
    protected void loadData() {
    }

    @OnClick({
            R.id.ll_web, R.id.ll_about
    })
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected void onClick2(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_web:
                WebActivity.start(getContext(), tv_web.getText().toString(), "https://www.wanandroid.com");
                break;
            case R.id.ll_about:
                WebActivity.start(getContext(), tv_about.getText().toString(), "https://www.wanandroid.com/about");
                break;
        }
    }
}
